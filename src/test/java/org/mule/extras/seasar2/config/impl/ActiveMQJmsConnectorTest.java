package org.mule.extras.seasar2.config.impl;

import org.mule.extras.seasar2.config.impl.ActiveMQJmsConnector;
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

    public void testGetConnector() throws Exception 
    {
        
        Object connector = config_.buildConnector();
        assertTrue("connector isn't ActiveMQJmsConnector", connector 
        			instanceof org.mule.transport.jms.activemq.ActiveMQJmsConnector);
        
        String brokerURL = 
        	((org.mule.transport.jms.activemq.ActiveMQJmsConnector) connector).getBrokerURL();
        assertEquals("Property borkerURL isn't correct", brokerURL, "tcp://localhost:61616");
        
        int maxRedelivery = ((org.mule.transport.jms.activemq.ActiveMQJmsConnector) connector).getMaxRedelivery();
        assertEquals("Property borkerURL isn't correct", maxRedelivery, 5);
    }
}
