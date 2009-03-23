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
import org.mule.module.client.MuleClient;

/**
 * デフォルトのMessageDisptcher
 * 
 * @author Saito_Shinya@ogis-ri.co.jp
 */
public class DefaultMessageDispatcherImpl implements MessageDispatcher 
{
    /**
     * @see org.mule.extras.seasar2.connector.MessageDispatcher#dispache(org.mule.extras.seasar2.endpoint.EndpointConfig, java.lang.Object, java.util.Map, org.mule.module.client.MuleClient)
     */
    public void dispache(EndpointConfig outboundEndpoint,
                         Object payload,
                         Map properties,
                         MuleClient muleClient) throws MuleException
    {
        muleClient.dispatch(outboundEndpoint.getUri(),payload,properties);
    }

    /**
     * @see org.mule.extras.seasar2.connector.MessageDispatcher#send(org.mule.extras.seasar2.endpoint.EndpointConfig, java.lang.Object, java.util.Map, org.mule.module.client.MuleClient)
     */
    public Object send(EndpointConfig outboundEndpoint, Object payload, Map properties, MuleClient muleClient)
        throws MuleException
    {
        return muleClient.send(outboundEndpoint.getUri(), payload, properties);
    }

}
