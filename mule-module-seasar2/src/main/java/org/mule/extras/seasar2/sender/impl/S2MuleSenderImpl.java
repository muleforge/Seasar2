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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Status;
import javax.transaction.TransactionManager;

import org.apache.axis.transport.jms.JMSConnector;
import org.mule.endpoint.EndpointURIEndpointBuilder;
import org.mule.endpoint.URIBuilder;
import org.mule.module.client.MuleClient;
import org.mule.extras.seasar2.endpoint.AbstractEndpoint;
import org.mule.extras.seasar2.endpoint.EndpointConfig;
import org.mule.extras.seasar2.endpoint.EndpointConfigBuilder;
import org.mule.extras.seasar2.endpoint.impl.EndpointConfigBuilderImpl;
import org.mule.extras.seasar2.exception.S2MuleConfigurationException;
import org.mule.extras.seasar2.exception.S2MuleRuntimeException;
import org.mule.extras.seasar2.sender.S2MuleSender;
import org.mule.transaction.TransactionCoordination;
import org.mule.transaction.XaTransaction;
import org.mule.transport.jms.JmsConnector;
import org.mule.util.ObjectNameHelper;

import org.mule.api.MuleException;
import org.mule.api.MuleMessage;
import org.mule.api.endpoint.EndpointBuilder;
import org.mule.api.endpoint.OutboundEndpoint;
import org.mule.api.transport.Connector;
import org.mule.extras.seasar2.connector.ConnectorConfig;
import org.mule.extras.seasar2.connector.impl.AxisConnector;
import org.mule.api.transformer.Transformer;
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
    
    /** Transformer */
    //private List transformers;
    
    /** 送信先の Endpoint URI */
    //private String outboundUri;
    
    /** 送信先 Endpointのプロパティ */
    //private Map properties = new HashMap();
    
    /**
     * 複数のoutboundのEndpoint
     */
    private List outboundEndpoints = new ArrayList();
    
    
    /** トランザクションマネージャ */
    private TransactionManager transactionManager;
    
    /** MuleClient */
    private MuleClient muleClient;
    
    /** S2コンテナ */
    private S2Container container;

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
        	muleClient = (MuleClient)container.getComponent(MuleClient.class);
                        
            //SenderのプロパティにセットされたEndpointを作成する
//            if (outboundUri!=null) 
//            {
//                URIBuilder uriBuilder = new URIBuilder(outboundUri);
//                endpointBuilder = new EndpointURIEndpointBuilder(uriBuilder,muleClient.getMuleContext());
//                muleClient.getMuleContext().getRegistry().registerEndpointBuilder(outboundUri, endpointBuilder);
// 
//                Endpoint endpoint = new Endpoint();
//            	endpoint.setUri(outboundUri);
//                if (connectorConfig != null) 
//                {
//                    //Connector の設定
//                    Connector connector = (Connector)connectorConfig.buildConnector();
//                    connector.setName(ObjectNameHelper.getConnectorName(connector));
//                    muleClient.getMuleContext().getRegistry().registerConnector(connector);
//                    endpointBuilder.setConnector(connector);
//                    if (connectorConfig instanceof AxisConnector)
//                    {
//                        properties.putAll(connectorConfig.getProperties());
//                    }
//                    logger.debug("Connectorを作成しました:" + connector);
//                 
//                	endpoint.setConnectorConfig(connectorConfig);                	
//                }
//                if (transformers != null) 
//                {
//                    //endpointBuilder.setTransformers(transformers);
//                	endpoint.setTransformers(transformers);
//                }
//                addOutboundEndpoint(endpoint);
//            }
            
            if(outboundEndpoints != null)
            {
            	for(int i=0;i<outboundEndpoints.size();i++)
            	{
            		AbstractEndpoint outboundEndpoint 
            			= (AbstractEndpoint)outboundEndpoints.get(i);
            		if(outboundEndpoint.getConnectorConfig() == null)
            		{
            			outboundEndpoint.setConnectorConfig(connectorConfig);
            		}
            		EndpointBuilder endpointBuilder 
            			= outboundEndpoint.buildEndpointBuilder(muleClient.getMuleContext());
            		muleClient.getMuleContext().getRegistry()
            			.registerEndpointBuilder(outboundEndpoint.getUri(), endpointBuilder);
                    //TransactionManagerの設定
            		ConnectorConfig connectorConfig = outboundEndpoint.getConnectorConfig();
                    if ( connectorConfig !=null 
                    		&& connectorConfig.isTransacted()) 
                    {
                        transactionManager = (TransactionManager)container.getRoot().getComponent(TransactionManager.class);
                        muleClient.getMuleContext().setTransactionManager(transactionManager);
                    }
            		
            	}
            }
            else
            {
                throw new S2MuleConfigurationException("ESML0002",new Object[]{"outboundEndpoint"});
            }
            
            
            intialized = true;
        } 
        catch(SRuntimeException e) 
        {
            throw e;
        }
        catch(Exception e) 
        {
            throw new S2MuleRuntimeException("ESML0000", new Object[]{e},e);
        }
    }
    
    /**
     * @see org.mule.extras.seasar2.sender.S2MuleSender#dispatch(Object)
     */
    public void dispatch(Object payload) 
    {
    	if(!intialized)
    	{
    		init();
    	}
    	
    	boolean localTransacted = false;
    	try
    	{	
	        for(int i=0; i<outboundEndpoints.size();i++) 
	        {
	        	EndpointConfig outboundEndpoint = (EndpointConfig)outboundEndpoints.get(i);
            	
                logger.debug("メッセージを" + outboundEndpoint.getUri() + "へ送信します");
                if (transactionManager!=null) 
                {
                	if (transactionManager.getTransaction()== null)
                	{
                		transactionManager.begin();
                		localTransacted = true;
                	}
                	
                    logger.debug("トランザクション:" + transactionManager.getTransaction() );
                    if (TransactionCoordination.getInstance().getTransaction()==null) 
                    {
                        XaTransaction xat = new XaTransaction(transactionManager);
                        TransactionCoordination.getInstance().bindTransaction(xat);
                    } 
                }
                muleClient.sendNoReceive(outboundEndpoint.getUri(), payload, outboundEndpoint.getProperties());
                logger.debug("メッセージを" + outboundEndpoint.getUri() + "へ送信しました");
            }
	        if(transactionManager.getStatus() == Status.STATUS_ACTIVE)
			{
				transactionManager.commit();
			}
    	}
        catch ( Exception e )
        {
        	if ( localTransacted )
            {
        		try
        		{
        			transactionManager.rollback();
        		}
        		catch( Exception ex )
        		{
        			throw new S2MuleRuntimeException("ESML0007", new Object[]{ex}, ex);
        		}
            }
            throw new S2MuleRuntimeException("ESML0000", new Object[]{e}, e);
        }
//        finally
//        {
//        	if (localTransacted)
//	        {
//        		try
//        		{
//        			if(transactionManager.getStatus() == Status.STATUS_ACTIVE)
//        			{
//        				transactionManager.commit();
//        			}
//        			else
//        			{
//        				transactionManager.rollback();
//        			}
//        		}
//        		catch( Exception e )
//        		{
//        			throw new S2MuleRuntimeException("ESML0007", new Object[]{e}, e);
//        		}
//	        }
//        }
    }
    
    /**
     * @see org.mule.extras.seasar2.sender.S2MuleSender#send(Object)
     */
    public Object send(Object payload) 
    {
    	if(!intialized)
    	{
    		init();
    	}
    	
        Object responseMessage = null;
        
        if(outboundEndpoints.size() < 2) 
        {
            try 
            {
            	EndpointConfig outboundEndpoint = (EndpointConfig)outboundEndpoints.get(0);
            	Connector connector = null;
            	if(outboundEndpoint.getUriScheme().equals(JmsConnector.JMS))
            	{
            		//MULE-3654
            		connector = (Connector)muleClient.getMuleContext().getRegistry()
            			.lookupConnector(outboundEndpoint.getConnectorConfig().getName());
            		connector.start();
            	}
            	
            	MuleMessage umoResponseMessage 
                   	= muleClient.send(outboundEndpoint.getUri(), payload, outboundEndpoint.getProperties());
            	responseMessage = umoResponseMessage.getPayload();
            	
            	if(outboundEndpoint.getUriScheme().equals(JmsConnector.JMS))
            	{
            		//MULE-3654
            		connector.stop();
            	}
            	
            }
            catch ( MuleException e ) 
            {
                throw new S2MuleRuntimeException("ESML0000", new Object[]{e}, e);
            }
            catch ( Exception e )
            {
                throw new S2MuleRuntimeException("ESML0000", new Object[]{e}, e);
            }
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
    		= (EndpointConfig)outboundEndpoints.get(0);
    	if(properties != null)
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
    	EndpointConfigBuilder builder 
		= new EndpointConfigBuilderImpl(endpointUri);
    	outboundEndpoints.add(builder.build());
    }
    
    /**
     * プロパティを追加する
     */
//    public void setProperty(String key, Object value) 
//    {
//        properties.put(key, value);
//    }
//    
    /**
     * トランスフォーマを追加する
     * 
     * @param newTransformer
     */
//    public void addTransformer(Transformer newTransformer) 
//    {
//        if(transformers == null) 
//        {
//            transformers = new ArrayList();
//            transformers.add(newTransformer);
//        }
//        else
//        {
//            transformers.add(newTransformer);
//        }
//    }
    
    public void dispose() 
    {
        muleClient.dispose();
    }
    
    
    public void setConnectorConfig(ConnectorConfig connectorConfig) 
    {
        this.connectorConfig = connectorConfig;
    }

//    public String getOutboundUri() 
//    {
//        return outboundUri;
//    }
//
//    public void setOutboundUri(String outboundUri) 
//    {
//        this.outboundUri = outboundUri;
//    }
    
    public S2Container getContainer() 
    {
        return container;
    }

    public void setContainer(S2Container container) 
    {
        this.container = container;
    }
    
}
