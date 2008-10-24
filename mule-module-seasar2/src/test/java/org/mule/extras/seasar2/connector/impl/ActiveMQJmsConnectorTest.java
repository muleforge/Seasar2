/*
 * Copyright (c) Osaka Gas Information System Research Institute Co., Ltd.
 * All rights reserved.  http://www.ogis-ri.co.jp/
 * 
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.extras.seasar2.connector.impl;

import org.mule.extras.seasar2.connector.impl.ActiveMQJmsConnector;
import org.mule.extras.seasar2.exception.S2MuleConfigurationException;
import org.seasar.extension.unit.S2TestCase;

public class ActiveMQJmsConnectorTest extends S2TestCase 
{
    
    private ActiveMQJmsConnector config_;
    
    public ActiveMQJmsConnectorTest(String name) 
    {
        super(name);
    }
    
    public void setUp() throws Exception 
    {
        include("ActiveMQJmsConnectorTest.dicon");
    }

    public void testBuildConnector() throws Exception 
    {
        
        Object connector = config_.buildConnector();
        assertTrue("connector isn't ActiveMQJmsConnector", connector 
        			instanceof org.mule.transport.jms.activemq.ActiveMQJmsConnector);
        
        String brokerURL = 
        	((org.mule.transport.jms.activemq.ActiveMQJmsConnector) connector).getBrokerURL();
        assertEquals("Property borkerURL isn't correct", "tcp://localhost:61616", brokerURL);
        
        int maxRedelivery = ((org.mule.transport.jms.activemq.ActiveMQJmsConnector) connector).getMaxRedelivery();
        assertEquals("Property borkerURL isn't correct", 5, maxRedelivery);
        
        config_.setBrokerURL(null);
        try
        {
        	config_.buildConnector();
        	fail("brokerURL is null");
        }
        catch(S2MuleConfigurationException ex)
        {
        	assertNotNull(ex.getMessage());
        }
        
    }
}
