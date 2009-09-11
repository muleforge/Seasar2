/*
 * Copyright (c) Osaka Gas Information System Research Institute Co., Ltd.
 * All rights reserved.  http://www.ogis-ri.co.jp/
 * 
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.extras.seasar2.endpoint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mule.api.MuleContext;
import org.mule.api.endpoint.EndpointBuilder;
import org.mule.api.routing.filter.Filter;
import org.mule.api.transaction.TransactionConfig;
import org.mule.api.transformer.Transformer;
import org.mule.api.transport.Connector;
import org.mule.transaction.MuleTransactionConfig;
import org.mule.transaction.XaTransactionFactory;
import org.mule.endpoint.EndpointURIEndpointBuilder;
import org.mule.endpoint.URIBuilder;
import org.mule.extras.seasar2.connector.ConnectorConfig;
import org.mule.extras.seasar2.connector.MessageDispatcher;
import org.mule.extras.seasar2.exception.S2MuleConfigurationException;
import org.mule.extras.seasar2.util.S2MuleEndpointUtil;

import org.seasar.framework.log.Logger;


/**
 * Endpointの構成情報を保持するクラスです。
 * 
 * @author Saito_Shinya@ogis-ri.co.jp
 *
 */
public abstract class AbstractEndpoint implements EndpointConfig 
{
    /** MuleのEndpointBuilder */
    protected EndpointBuilder endpointBuilder;
    
    /**  エンドポイントのuri */
    protected String uri;

    /** トランスフォーマ */
    protected List<Transformer> transformers;
    
    /** コネクタ*/
    protected ConnectorConfig connectorConfig;
    
    /** プロパティ */
    protected Map properties = new HashMap();
    
    /** フィルタ */
    protected Filter filter;
    
    /** 非同期メッセージを同期的に扱う */
    protected Boolean remoteSync = false;
    
    /** 非同期メッセージを同期的に扱う場合のタイムアウト*/
    protected Integer remoteSyncTimeout;
    
    /** メッセージディスパッチャ */
    protected MessageDispatcher messageDispatcher;
    
    /** logger*/
    private static final Logger logger = Logger
        .getLogger(AbstractEndpoint.class);
    
    /**
     *  インスタンスを生成する
     */
    public AbstractEndpoint() 
    {
        //blank
    }
    
    public AbstractEndpoint(String uri)
    {
        this.uri = uri;
    }
    
    /**
     * 初期化処理
     */
    public void init(MuleContext muleContext, int endpointType)
    {
        endpointBuilder = createEndpointBuilder(muleContext);
        if (endpointType == EndpointConfig.OUTBOUND_ENDPOINT)
        {
            messageDispatcher = createMessageDispatcher();
        }
    }
    
    /**
     * MessageDispatcherを作成する
     * 
     * @return messageDispatcher
     */
    protected abstract MessageDispatcher createMessageDispatcher();

    /**
     * EndpointBuilderを作成する
     * 
     * @return endpointBuilder
     */
    private EndpointBuilder createEndpointBuilder(MuleContext muleContext) 
    {
         EndpointBuilder endpointBuilder = null;
         if (uri != null) 
         {
             URIBuilder uriBuilder = new URIBuilder(uri);
             endpointBuilder = new EndpointURIEndpointBuilder(uriBuilder, muleContext);
         } 
         else
         {
             throw new S2MuleConfigurationException("ESML0002", new Object[]{"uri"});
         }
         
         if (connectorConfig != null) 
         {             
             Connector connector = muleContext.getRegistry().lookupConnector(connectorConfig.getName());
             endpointBuilder.setConnector(connector);
             
             if (connectorConfig.isTransacted()) 
             {
                TransactionConfig transactionConfig = new MuleTransactionConfig();
                transactionConfig.setAction(TransactionConfig.ACTION_BEGIN_OR_JOIN);
                transactionConfig.setFactory(new XaTransactionFactory());
                endpointBuilder.setTransactionConfig(transactionConfig);
             }
             logger.debug("Connectorを設定しました:" + connector);
         }
        
         if (filter != null)
         {
             endpointBuilder.setFilter(filter);
         }
         
         if (transformers != null) 
         {
             endpointBuilder.setTransformers(transformers);
         }
         
         if (properties != null)
         {
             endpointBuilder.setProperties(properties);
         }
         
//         endpointBuilder.setRemoteSync(remoteSync);
//         
//         if (remoteSyncTimeout != null)
//         {
//             endpointBuilder.setRemoteSyncTimeout(remoteSyncTimeout);
//         }
         
        return endpointBuilder;
    }
    
    /**
     * トランスフォーマを追加する
     * 
     * @param newTransformer
     */
    public void addTransformer(Transformer newTransformer) 
    {
        if (transformers == null) 
        {
            transformers = new ArrayList<Transformer>();
        }
        transformers.add(newTransformer);
    }

    /**
     * プロパティを追加する
     */
    public void setProperty(String key, Object value) 
    {
        properties.put(key, value);
    }
    
    /**
     * メッセージディスパッチャを取得する
     * 
     */
    public MessageDispatcher getMessageDispatcher()
    {
        return messageDispatcher;
    }

    
    public EndpointBuilder getEndpointBuilder()
    {
        return endpointBuilder;
    }
    
    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    public void setProperties(Map properties)
    {
        this.properties = properties;
    }

    public Map getProperties() 
    {
        return properties;
    }

    public void setTransformers(List<Transformer> transformers) 
    {
        this.transformers = transformers;
    }

    public String getUri() 
    {
        return uri;
    }

    
    public void setUri(String uri) 
    {
        if (isValidUriScheme(uri))
        {
            this.uri = uri;
        } 
        else
        {
            throw new S2MuleConfigurationException("ESML0008", 
                new Object[]{S2MuleEndpointUtil.getUriScheme(uri),getUriScheme()});
        }
    }

    public ConnectorConfig getConnectorConfig() 
    {
        return connectorConfig;
    }

    public void setConnectorConfig(ConnectorConfig connectorConfig) 
    {
        this.connectorConfig = connectorConfig;
    }

    public void setRemoteSync(Boolean remoteSync) 
    {
        this.remoteSync = remoteSync;
    }

    public void setRemoteSyncTimeout(Integer remoteSyncTimeout) 
    {
        this.remoteSyncTimeout = remoteSyncTimeout;
    }
    
    /**
     * Uriスキームをチェックする
     * 
     * @param uri
     * @return
     */
    private boolean isValidUriScheme(String uri)
    {
        String uriScheme = S2MuleEndpointUtil.getUriScheme(uri);
        return this.getUriScheme().equals(uriScheme);
    }
}
