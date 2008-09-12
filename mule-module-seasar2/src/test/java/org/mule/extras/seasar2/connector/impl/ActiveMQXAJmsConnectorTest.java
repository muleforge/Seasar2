package org.mule.extras.seasar2.connector.impl;

import org.mule.extras.seasar2.connector.impl.ActiveMQJmsConnector;
import org.seasar.extension.unit.S2TestCase;

public class ActiveMQXAJmsConnectorTest extends S2TestCase 
{
    
    private ActiveMQJmsConnector config_;

    public ActiveMQXAJmsConnectorTest(String name) 
    {
        super(name);
    }

    public void setUp() throws Exception 
    {
        include("ActiveMQXAJmsConnectorTest.dicon");
    }

    public void testGetConnector() throws Exception 
    {

        Object connector = config_.buildConnector();
        assertTrue("Connector isn't ActiveMQXAJmsConnector",
                connector instanceof org.mule.transport.jms.activemq.ActiveMQXAJmsConnector);

        String brokerURL = 
        	((org.mule.transport.jms.activemq.ActiveMQXAJmsConnector) connector)
        	.getBrokerURL();
        assertEquals("Property borkerURL isn't correct", brokerURL,
                "tcp://localhost:61616");

        int maxRedelivery = 
        	((org.mule.transport.jms.activemq.ActiveMQXAJmsConnector) connector)
                .getMaxRedelivery();
        assertEquals("Property maxRedelivery isn't correct", maxRedelivery, 5);
    }
}
