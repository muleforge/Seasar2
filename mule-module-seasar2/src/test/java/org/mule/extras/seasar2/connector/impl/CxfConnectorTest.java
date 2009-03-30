/*
 * Copyright (c) Osaka Gas Information System Research Institute Co., Ltd.
 * All rights reserved.  http://www.ogis-ri.co.jp/
 * 
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.extras.seasar2.connector.impl;

import org.seasar.extension.unit.S2TestCase;

public class CxfConnectorTest extends S2TestCase 
{
    
    private CxfConnector config_;
    
    public CxfConnectorTest(String name) 
    {
        super(name);
    }
    
    @Override
    public void setUp() throws Exception 
    {
        include("CxfConnectorTest.dicon");
    }
    
    public void testbuildComponent() throws Exception 
    {
        Object connector = config_.buildConnector();
        assertTrue("Connector isn't CxfConnector",
                connector instanceof org.mule.transport.cxf.CxfConnector);        
    }
}
