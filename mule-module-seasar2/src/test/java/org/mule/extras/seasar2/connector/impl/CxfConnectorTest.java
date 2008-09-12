package org.mule.extras.seasar2.connector.impl;

import org.seasar.extension.unit.S2TestCase;

public class CxfConnectorTest extends S2TestCase 
{
    
    private CxfConnector config_;
    
    public CxfConnectorTest(String name) 
    {
        super(name);
    }
    
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
