/*
 * Copyright (c) Osaka Gas Information System Research Institute Co., Ltd.
 * All rights reserved.  http://www.ogis-ri.co.jp/
 * 
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.extras.seasar2.connector.impl;

import java.util.Map;

import org.mule.api.MuleException;
import org.mule.extras.seasar2.connector.MessageDispatcher;
import org.mule.extras.seasar2.endpoint.EndpointConfig;
import org.mule.extras.seasar2.exception.S2MuleRuntimeException;
import org.mule.module.client.MuleClient;

/**
 * Fileにメッセージを書き出すディスパッチャ
 * 
 * @auther Saito_Shinya@ogis-ri.co.jp
 */
public class FileMessageDispatcherImpl implements MessageDispatcher 
{
    
    /** 
     * @see org.mule.extras.seasar2.connector.MessageDispatcher#dispache(EndpointConfig, Object, Map, MuleClient)
     */
    public void dispatche(EndpointConfig outboundEndpoint,
                         Object payload,
                         MuleClient muleClient) throws MuleException
    {
        muleClient.sendAsync(outboundEndpoint.getUri(), payload, outboundEndpoint.getProperties());  
    }
    
	/**
	 * @see org.mule.extras.seasar2.connector.MessageDispatcher#send(EndpointConfig, Object, Map, MuleClient)
	 */
	public Object send(EndpointConfig outboundEndpoint,
	                   Object payload,
	                   MuleClient muleClient)
	{
	  //同期メッセージ送信はサポートしていません。
        throw new S2MuleRuntimeException(new UnsupportedOperationException());
	}

}
