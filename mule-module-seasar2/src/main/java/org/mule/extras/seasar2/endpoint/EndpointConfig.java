/*
 * Copyright (c) Osaka Gas Information System Research Institute Co., Ltd.
 * All rights reserved.  http://www.ogis-ri.co.jp/
 * 
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.extras.seasar2.endpoint;

import java.util.Map;

import org.mule.api.MuleContext;
import org.mule.api.endpoint.EndpointBuilder;
import org.mule.extras.seasar2.connector.ConnectorConfig;
import org.mule.extras.seasar2.connector.MessageDispatcher;

/**
 * Mule Endpointの構成情報を保持するクラスのインターフェース
 * builderの働きをする
 * 
 * @author Saito_Shinya@ogis-ri.co.jp
 *
 */
public interface EndpointConfig 
{
    /** InboundEndpointを表す定数 */
    static int INBOUND_ENDPOINT = 1;
    
    /** OutboundEndpointを表す定数 */
    static int OUTBOUND_ENDPOINT = 2;
    
    /**
     * 初期化処理
     * 
     * @param muleContext
     * @param endpointType Endpointの種類
     */
    void init(MuleContext muleContext, int endpointType);

    /**
     * EndpointBuilderを取得する
     * 
     * @return EndpointBuider EndpointBuilder
     */
    EndpointBuilder getEndpointBuilder();
    
    /**
     * EndpointUriを取得する
     * 
     * @return String EndpointUri
     */
    String getUri();
    
    /**
     * ConnectorConfigを取得する
     * 
     * @return connectorConfig connectorConfig
     */
    ConnectorConfig getConnectorConfig();
    
    /**
     * Propertiesを取得する
     * 
     * @return properties properties
     */
    Map getProperties();
    
    /**
     * EndpointUriのスキームを取得する
     */
    String getUriScheme();
    
    /**
     * MessageDispatcherを取得する
     * 
     * @return
     */
    MessageDispatcher getMessageDispatcher();
    
    /**
     * Propertyを設定する
     * 
     * @param key
     * @param value
     */
    void setProperty(String key, Object value); 
    
    /**
     * ConnectorConfigを設定する
     * @param connectorConfig
     */
    void setConnectorConfig(ConnectorConfig connectorConfig);
}
