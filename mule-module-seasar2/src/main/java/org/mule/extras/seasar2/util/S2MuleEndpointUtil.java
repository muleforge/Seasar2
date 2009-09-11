/*
 * Copyright (c) Osaka Gas Information System Research Institute Co., Ltd.
 * All rights reserved.  http://www.ogis-ri.co.jp/
 * 
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.extras.seasar2.util;

import org.mule.extras.seasar2.exception.S2MuleConfigurationException;

public class S2MuleEndpointUtil
{
    public static String getUriScheme(String uri)
    {
        int index = uri.indexOf(":");
        if (index == -1 )
        {
            throw new S2MuleConfigurationException("EMSL0006" , new Object[]{uri});
        }
        return uri.substring(0, index);
    }
}


