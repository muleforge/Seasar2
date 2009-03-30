/*
 * Copyright (c) Osaka Gas Information System Research Institute Co., Ltd.
 * All rights reserved.  http://www.ogis-ri.co.jp/
 * 
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.extras.seasar2.connector.impl;

import java.lang.reflect.Method;
import java.util.Map;

import org.mule.api.MuleException;
import org.mule.api.MuleMessage;
import org.mule.api.endpoint.ImmutableEndpoint;
import org.mule.extras.seasar2.connector.MessageDispatcher;
import org.mule.extras.seasar2.endpoint.EndpointConfig;
import org.mule.extras.seasar2.exception.S2MuleRuntimeException;
import org.mule.extras.seasar2.sender.interceptor.MethodInvocationInterceptor;
import org.mule.module.client.MuleClient;

/**
 * Axisを使ったSOAPメッセージのディスパッチャ
 * 
 * @auther Saito_Shinya@ogis-ri.co.jp
 */
public class AxisMessageDispatcherImpl implements MessageDispatcher
{
    public void dispache(EndpointConfig outboundEndpoint,
                         Object payload,
                         MuleClient muleClient) throws MuleException
    {
        //非同期メッセージ送信はサポートしていません
        throw new S2MuleRuntimeException(new UnsupportedOperationException());
    }

    public Object send(EndpointConfig outboundEndpoint, 
                       Object payload, 
                       MuleClient muleClient)
    {
        try
        {
            ImmutableEndpoint endpoint = muleClient.getMuleContext()
                .getRegistry().lookupEndpointFactory().getOutboundEndpoint(outboundEndpoint.getUri());
        
            if (endpoint.getProperties().containsKey("method"))
            {
                //前回送信したmethodプロパティを削除する
                //TODO "method"を使わないようにする
                endpoint.getProperties().remove("method");
            }
            
            Map properties = outboundEndpoint.getProperties();
            
            //methodの設定
            Method method = (Method) properties.get(MethodInvocationInterceptor.ORIGINAL_METHOD);
            
            //TODO "method"を使わないようにする
            properties.put("method", method.getName());
            
            MuleMessage responseMessage = muleClient
                .send(outboundEndpoint.getUri(), payload, properties);
            return responseMessage.getPayload();
        }
        catch (Exception e)
        {
            throw new S2MuleRuntimeException("ESML0000", new Object[]{e}, e);
        }
    }
}


