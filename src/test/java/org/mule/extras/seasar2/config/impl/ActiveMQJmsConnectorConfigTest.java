package org.mule.extras.seasar2.config.impl;

import org.mule.extras.seasar2.config.impl.ActiveMQJmsConnectorConfig;
import org.seasar.extension.unit.S2TestCase;

public class ActiveMQJmsConnectorConfigTest extends S2TestCase {
	
	private ActiveMQJmsConnectorConfig config_;
	
	public ActiveMQJmsConnectorConfigTest(String name) {
		super(name);
	}
	
	public void setUp() throws Exception {
		include("ActiveMQJmsConnectorConfigTest.dicon");
	}

	public void testGetConnector() throws Exception {
		
		String brokerURL = config_.getBrokerURL();
		assertEquals(brokerURL, "tcp://localhost:61616");
		
		int maxRedelivery = (Integer)config_.getProperty("maxRedelivery");
		assertEquals(maxRedelivery, 5);
	}
}
