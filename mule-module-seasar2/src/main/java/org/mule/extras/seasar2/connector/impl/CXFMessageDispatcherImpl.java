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
import org.mule.transport.cxf.CxfConstants;

public class CXFMessageDispatcherImpl implements MessageDispatcher
{

    /**
     * @see org.mule.extras.seasar2.connector.MessageDispatcher#dispache(EndpointConfig, Object, Map, MuleClient)
     */
    public void dispache(EndpointConfig outboundEndpoint,
                         Object payload,
                         MuleClient muleClient) throws MuleException
    {
        muleClient.dispatch(outboundEndpoint.getUri(),payload,outboundEndpoint.getProperties());
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
            Map s2muleProperties = outboundEndpoint.getProperties();
            
            ImmutableEndpoint muleEndpoint = (ImmutableEndpoint) muleClient.getMuleContext()
                .getRegistry().lookupEndpointFactory().getOutboundEndpoint(outboundEndpoint.getUri());
            Map muleProperties = muleEndpoint.getProperties();
            
            //MuleのEndpoint PropertiesにOperationが設定されていたら削除する。
            if (muleProperties.containsValue(CxfConstants.OPERATION))
            {
                muleProperties.remove(CxfConstants.OPERATION);
            }
            
            //Operationの設定
            Method originalMethod = (Method) s2muleProperties.get(MethodInvocationInterceptor.ORIGINAL_METHOD);
            muleProperties.put(CxfConstants.OPERATION, originalMethod.getName());
            
            MuleMessage response = 
                muleClient.send(outboundEndpoint.getUri(), payload, s2muleProperties);
            
            return response.getPayload();
        }
        catch (MuleException e)
        {
            throw new S2MuleRuntimeException("ESML0000", new Object[]{e}, e);
        }
    }

}


