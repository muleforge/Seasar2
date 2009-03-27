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
import org.mule.api.transport.Connector;
import org.mule.extras.seasar2.connector.MessageDispatcher;
import org.mule.extras.seasar2.endpoint.EndpointConfig;
import org.mule.extras.seasar2.exception.S2MuleRuntimeException;
import org.mule.module.client.MuleClient;



public class JMSMessageDispatcherImpl implements MessageDispatcher 
{    
    
    /**
     * @see org.mule.extras.seasar2.connector.MessageDispatcher#dispache(EndpointConfig, Object, Map, MuleClient)
     */
    public void dispache(EndpointConfig outboundEndpoint,
                         Object payload,
                         MuleClient muleClient) throws MuleException
    {
        //メッセージの送信
        muleClient.dispatch(outboundEndpoint.getUri(), payload, outboundEndpoint.getProperties());
    }
    
    /** 
     * @see org.mule.extras.seasar2.connector.MessageDispatcher#send(EndpointConfig, Object, Map, MuleClient)
     */
    public Object send(EndpointConfig outboundEndpoint, 
                       Object payload, 
                       MuleClient muleClient)
    {
        try
        {
            //replyTo を有効にするためにconnectorを起動する
            String connectorName = outboundEndpoint.getConnectorConfig().getName();
            Connector connector = muleClient.getMuleContext()
                .getRegistry().lookupConnector(connectorName);
            connector.start();
        
            Object response = muleClient
                .send(outboundEndpoint.getUri(), payload, outboundEndpoint.getProperties());
        
            connector.stop();
        
            return response;
        }
        catch (Exception e)
        {
            throw new S2MuleRuntimeException("ESML0000", new Object[]{e}, e);
        }
    }
}
