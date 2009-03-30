/*
 * Copyright (c) Osaka Gas Information System Research Institute Co., Ltd.
 * All rights reserved.  http://www.ogis-ri.co.jp/
 * 
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.extras.seasar2.sender.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Status;
import javax.transaction.TransactionManager;

import org.mule.module.client.MuleClient;
import org.mule.extras.seasar2.endpoint.EndpointConfig;
import org.mule.extras.seasar2.endpoint.EndpointConfigFactory;
import org.mule.extras.seasar2.endpoint.impl.EndpointConfigFactoryImpl;
import org.mule.extras.seasar2.exception.S2MuleConfigurationException;
import org.mule.extras.seasar2.exception.S2MuleRuntimeException;
import org.mule.extras.seasar2.sender.S2MuleSender;
import org.mule.extras.seasar2.util.S2MuleComponentUtil;
import org.mule.transaction.TransactionCoordination;
import org.mule.transaction.XaTransaction;

import org.mule.api.endpoint.EndpointBuilder;
import org.mule.extras.seasar2.connector.ConnectorConfig;
import org.mule.extras.seasar2.connector.MessageDispatcher;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.exception.SRuntimeException;
import org.seasar.framework.log.Logger;

/**
 * {@link S2MuleSender} の実装クラスです
 * @author Saito_Shinya@ogis-ri.co.jp
 */
public class S2MuleSenderImpl implements S2MuleSender 
{
    /** logger*/
     private static final Logger logger = Logger
         .getLogger(S2MuleSenderImpl.class);
    
    /** Connector の構成情報 */
    private ConnectorConfig connectorConfig;
    
    /** 複数のoutboundのEndpoint */
    private List outboundEndpoints = new ArrayList();
    
    /** トランザクションマネージャ */
    private TransactionManager transactionManager;
    
    /** MuleClient */
    private MuleClient muleClient;
    
    /** S2コンテナ */
    private S2Container container;

    /** 初期化処理*/
    private boolean intialized = false;
        
    /**
     * インスタンスを生成します
     */
    public S2MuleSenderImpl(final ConnectorConfig connectorConfig) 
    {
        this.connectorConfig = connectorConfig;
    }
    
    /**
     * 送信の準備を行います
     */
    public void init() 
    {
        try 
        {
            List connectorConfigs = S2MuleComponentUtil.getConnectorConfigs(container);
            if (connectorConfigs != null)
            {
                for ( int i = 0; i < connectorConfigs.size(); i++)
                {
                    ConnectorConfig connectorConfig
                        = (ConnectorConfig) connectorConfigs.get(i);
                    if (muleClient.getMuleContext()
                            .getRegistry().lookupConnector(connectorConfig.getName()) == null)
                    {
                        muleClient.getMuleContext().getRegistry()
                            .registerConnector(connectorConfig.buildConnector());
                    }
                }
            }
            
            
            if (outboundEndpoints != null)
            {
                for (int i = 0; i < outboundEndpoints.size(); i++)
                {
                    //SenderのプロパティにセットされたEndpointを作成する         
                    EndpointConfig outboundEndpoint 
                        = (EndpointConfig) outboundEndpoints.get(i);
                    if (outboundEndpoint.getConnectorConfig() == null)
                    {
                        outboundEndpoint.setConnectorConfig(connectorConfig);
                    }
                    outboundEndpoint.init(muleClient.getMuleContext(),EndpointConfig.OUTBOUND_ENDPOINT);
                    EndpointBuilder endpointBuilder = outboundEndpoint.getEndpointBuilder();
                    muleClient.getMuleContext().getRegistry()
                        .registerEndpointBuilder(outboundEndpoint.getUri(), endpointBuilder);
                    
                    //TransactionManagerの設定
                    ConnectorConfig connectorConfig = outboundEndpoint.getConnectorConfig();
                    if ( connectorConfig != null 
                            && connectorConfig.isTransacted()) 
                    {
                        muleClient.getMuleContext().setTransactionManager(transactionManager);
                    }
                    
                }
            }
            else
            {
                throw new S2MuleConfigurationException("ESML0002", new Object[]{"outboundEndpoint"});
            }
            
            
            intialized = true;
        } 
        catch (SRuntimeException e) 
        {
            throw e;
        }
        catch (Exception e) 
        {
            throw new S2MuleRuntimeException("ESML0000", new Object[]{e}, e);
        }
    }
    
    /**
     * @see org.mule.extras.seasar2.sender.S2MuleSender#dispatch(Object)
     */
    public void dispatch(Object payload) 
    {
        if (!intialized)
        {
            init();
        }
        
        boolean localTransacted = false;
        try
        {    
            for (int i = 0; i < outboundEndpoints.size(); i++) 
            {
                EndpointConfig outboundEndpoint = (EndpointConfig) outboundEndpoints.get(i);
                ConnectorConfig connectorConfig = outboundEndpoint.getConnectorConfig();
                MessageDispatcher messageDispacher = outboundEndpoint.getMessageDispatcher();
                
                logger.debug("メッセージを" + outboundEndpoint.getUri() + "へ送信します");
                if (transactionManager != null && connectorConfig != null 
                           && connectorConfig.isTransacted()) 
                {
                    if (transactionManager.getTransaction() == null)
                    {
                        transactionManager.begin();
                        localTransacted = true;
                    }
                    
                    logger.debug("トランザクション:" + transactionManager.getTransaction() );
                    if (TransactionCoordination.getInstance().getTransaction() == null) 
                    {
                        XaTransaction xat = new XaTransaction(transactionManager);
                        TransactionCoordination.getInstance().bindTransaction(xat);
                    } 
                }
                messageDispacher.dispache(outboundEndpoint, payload, muleClient);
                logger.debug("メッセージを" + outboundEndpoint.getUri() + "へ送信しました");
            }
            if (transactionManager != null 
                    && transactionManager.getStatus() == Status.STATUS_ACTIVE)
            {
                transactionManager.commit();
            }
        }
        catch (Exception e)
        {
            if (localTransacted)
            {
                try
                {
                    transactionManager.rollback();
                }
                catch (Exception ex)
                {
                    throw new S2MuleRuntimeException("ESML0007", new Object[]{ex}, ex);
                }
            }
            throw new S2MuleRuntimeException("ESML0000", new Object[]{e}, e);
        }
    }
    
    /**
     * @see org.mule.extras.seasar2.sender.S2MuleSender#send(Object)
     */
    public Object send(Object payload) 
    {
        if (!intialized)
        {
            init();
        }
        
        Object responseMessage = null;
        
        if (outboundEndpoints.size() < 2) 
        {
            EndpointConfig outboundEndpoint = (EndpointConfig) outboundEndpoints.get(0);
            MessageDispatcher messageDispatcher = outboundEndpoint.getMessageDispatcher();
            
            logger.debug("メッセージを" + outboundEndpoint.getUri() + "へ送信します");
            responseMessage = messageDispatcher.send(outboundEndpoint, payload, muleClient);
            logger.debug("メッセージを" + outboundEndpoint.getUri() + "へ送信しました");
            
            return responseMessage;
        }
        else
        {
            throw new S2MuleConfigurationException("ESML0005");
        }
    }
    
    /**
     * @see org.mule.extras.seasar2.sender.S2MuleSender#send(Object,Map)
     */
    public Object send(Object payload, Map properties) 
    {
        EndpointConfig outboundEndpoint
            = (EndpointConfig) outboundEndpoints.get(0);
        if (properties != null)
        {
            outboundEndpoint.getProperties().putAll(properties);
        }
        return send(payload);
    }
    
    /**
     * OutboundEndpointを追加する
     * @param endpoint
     */
    public void addOutboundEndpoint (EndpointConfig endpoint)
    {
        outboundEndpoints.add(endpoint);
    }
    
    /**
     * OutboundEndpointを追加する
     * @param endpointUri
     */
    public void addOutboundEndpoint (String endpointUri)
    {
        EndpointConfigFactory factory
            = new EndpointConfigFactoryImpl(endpointUri);
        outboundEndpoints.add(factory.createEndpoint());
    }
    
    /** 
     * 終了処理
     */
    public void dispose() 
    {
        muleClient.dispose();
    }
    
    
    public void setConnectorConfig(ConnectorConfig connectorConfig) 
    {
        this.connectorConfig = connectorConfig;
    }
    
    public S2Container getContainer() 
    {
        return container;
    }

    public void setContainer(S2Container container) 
    {
        this.container = container;
    }

    public void setTransactionManager(TransactionManager transactionManager)
    {
        this.transactionManager = transactionManager;
    }

    public void setMuleClient(MuleClient muleClient)
    {
        this.muleClient = muleClient;
    }
    
}
