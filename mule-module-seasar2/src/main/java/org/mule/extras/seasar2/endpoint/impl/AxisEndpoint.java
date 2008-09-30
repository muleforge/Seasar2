/*
 * Copyright (c) Osaka Gas Information System Research Institute Co., Ltd.
 * All rights reserved.  http://www.ogis-ri.co.jp/
 * 
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.extras.seasar2.endpoint.impl;

import org.mule.extras.seasar2.endpoint.AbstractEndpoint;
import org.mule.transport.soap.axis.AxisConnector;

/**
 * AxisEndpointの構成情報を保持するクラスです
 * 
 * @author Saito_Shinya@ogis-ri.co.jp
 *
 */
public class AxisEndpoint extends AbstractEndpoint 
{
    /** AxisEndpointのスキーム*/
    public static final String SCHEME = "axis";
    
    public AxisEndpoint()
    {
        
    }
    
    public AxisEndpoint(String uri)
    {
        super(uri);
    }
    
    public String getUriScheme() 
    {
        return SCHEME;
    }
    
    public void setStyle(String style)
    {
        setProperty(AxisConnector.STYLE, style);
    }
    
    public void setUse(String use)
    {
        setProperty(AxisConnector.USE, use);
    }
}
