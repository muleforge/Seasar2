/*
 * Copyright (c) Osaka Gas Information System Research Institute Co., Ltd.
 * All rights reserved.  http://www.ogis-ri.co.jp/
 * 
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.extras.seasar2.connector;

import java.util.Map;

import org.mule.api.MuleException;
import org.mule.extras.seasar2.endpoint.EndpointConfig;
import org.mule.module.client.MuleClient;

/**
 * メッセージを送信するためのインタフェース
 * 
 * @author Saito_Shinya@ogis-ri.co.jp
 */
public interface MessageDispatcher 
{

	 /**
     * 非同期メッセージを送信します。
     * 
     * @param uri メッセージの宛先のエンドポイント
     * @param payload メッセージの本文
     * @param properties　プロパティ
     * @param muleClient MuleClient
     * 
     * @exception Muleの例外
     */
    void dispache(EndpointConfig outboundEndpoint,
    			Object payload, 
    			Map properties, 
    			MuleClient muleClient) throws MuleException;
    
 
    /**
     * 同期メッセージを送信します。
     * 
     * @param outboundEndpoint メッセージの宛先のエンドポイント
     * @param payload メッセージの本文
     * @param properties　プロパティ
     * @param muleClient MuleClient
     * 
     * @exception Muleの例外
     * 
     * @return レスポンスメッセージ
     */
    Object send(EndpointConfig outboundEndpoint,
    		Object payload,
    		Map properties,
    		MuleClient muleClient) throws MuleException;
	
}
