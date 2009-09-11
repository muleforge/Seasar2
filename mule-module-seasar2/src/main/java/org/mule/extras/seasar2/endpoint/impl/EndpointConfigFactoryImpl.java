/*
 * Copyright (c) Osaka Gas Information System Research Institute Co., Ltd.
 * All rights reserved.  http://www.ogis-ri.co.jp/
 * 
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.extras.seasar2.endpoint.impl;

import org.mule.extras.seasar2.endpoint.EndpointConfig;
import org.mule.extras.seasar2.endpoint.EndpointConfigFactory;
import org.mule.extras.seasar2.util.S2MuleEndpointUtil;

public class EndpointConfigFactoryImpl implements EndpointConfigFactory 
{
    /** EndpointのURI*/
    private String uri;

    public EndpointConfigFactoryImpl(String uri)
    {
        this.uri=uri;
    }
    
    /**
     * @see org.mule.extras.seasar2.endpoint.EndpointConfigFactory#createEndpoint()
     */
    public EndpointConfig createEndpoint()
    {
        String scheme = S2MuleEndpointUtil.getUriScheme(uri);
        
        if (scheme.equals(FileEndpoint.SCHEME))
        {
            return new FileEndpoint(uri);
        }
        else if (scheme.equals(JMSEndpoint.SCHEME))
        {
            return new JMSEndpoint(uri);
        }
        else if (scheme.equals(AxisEndpoint.SCHEME))
        {
            return new AxisEndpoint(uri);
        }
        else if (scheme.equals(CxfEndpoint.SCHEME))
        {
            return new CxfEndpoint(uri);
        }
        return null;
    }

}
