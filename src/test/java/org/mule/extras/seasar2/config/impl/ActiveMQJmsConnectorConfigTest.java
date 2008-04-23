package org.mule.extras.seasar2.config.impl;

import org.mule.transport.jms.activemq.ActiveMQJmsConnector;
import org.seasar.extension.unit.S2TestCase;

public class ActiveMQJmsConnectorConfigTest extends S2TestCase 
{
    
    private ActiveMQJmsConnectorConfigImpl config_;
    
    public ActiveMQJmsConnectorConfigTest(String name) 
    {
        super(name);
    }
    
    public void setUp() throws Exception 
    {
        include("ActiveMQJmsConnectorConfigTest.dicon");
    }

    public void testGetConnector() throws Exception 
    {
        
        Object connector = config_.buildConnector();
        assertTrue("connector isn't ActiveMQJmsConnector", connector instanceof ActiveMQJmsConnector);
        
        String brokerURL = ((ActiveMQJmsConnector) connector).getBrokerURL();
        assertEquals("Property borkerURL isn't correct", brokerURL, "tcp://localhost:61616");
        
        int maxRedelivery = ((ActiveMQJmsConnector) connector).getMaxRedelivery();
        assertEquals("Property borkerURL isn't correct", maxRedelivery, 5);
    }
}
