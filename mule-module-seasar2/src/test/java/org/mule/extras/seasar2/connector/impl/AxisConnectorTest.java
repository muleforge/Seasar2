/*
 * Copyright (c) Osaka Gas Information System Research Institute Co., Ltd.
 * All rights reserved.  http://www.ogis-ri.co.jp/
 * 
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.extras.seasar2.connector.impl;

import org.mule.extras.seasar2.connector.impl.AxisConnector;
import org.seasar.extension.unit.S2TestCase;

public class AxisConnectorTest extends S2TestCase 
{
    
    private AxisConnector config_;
    
    public AxisConnectorTest(String name) 
    {
        super(name);
    }
    
    public void setUp() throws Exception 
    {
        include("AxisConnectorTest.dicon");
    }
    
    public void testbuildComponent() throws Exception 
    {
        Object connector = config_.buildConnector();
        assertTrue("Connector isn't AxisConnector",
                connector instanceof org.mule.transport.soap.axis.AxisConnector);
        
        String beanType = 
        		(String) ((org.mule.transport.soap.axis.AxisConnector) connector)
        			.getBeanTypes().get(0);
        assertEquals("Property beanType isn't correct", 
                    beanType,
                    "org.mule.extras.seasar2.config.impl.MyClass");
        
    }
}
