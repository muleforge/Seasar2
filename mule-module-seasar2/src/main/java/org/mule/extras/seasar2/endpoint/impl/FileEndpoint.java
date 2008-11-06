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

/**
 * 
 * @author Saito_Shinya@ogis-ri.co.jp
 *
 */
public class FileEndpoint extends AbstractEndpoint 
{
    /** FileEndpointのスキーム */
    public static final String SCHEME = "file";
    
    public FileEndpoint()
    {
        
    }
    
    public FileEndpoint(String uri)
    {
        super(uri);
    }
    
    public String getUriScheme() 
    {
        return SCHEME;
    }

}
