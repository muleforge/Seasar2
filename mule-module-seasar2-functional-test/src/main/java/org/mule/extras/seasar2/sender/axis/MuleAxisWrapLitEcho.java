/*
 * Copyright (c) Osaka Gas Information System Research Institute Co., Ltd.
 * All rights reserved.  http://www.ogis-ri.co.jp/
 * 
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.extras.seasar2.sender.axis;

import org.mule.tck.FunctionalTestCase;

/**
* Mule-Customer-Axis-Serviceを立ち上げる
* 
* @author Saito_Shinya@ogis-ri.co.jp
*
*/
public class MuleAxisWrapLitEcho extends FunctionalTestCase 
{

    @Override
    protected String getConfigResources() 
    {
        
        return "org/mule/config/axis-wrap-lit-mule-config.xml";
    }
    
    public void testMule() 
    {
        while (true){
            
        }
    }

}
