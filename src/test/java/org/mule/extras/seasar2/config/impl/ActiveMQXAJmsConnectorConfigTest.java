package org.mule.extras.seasar2.config.impl;

import org.mule.transport.jms.activemq.ActiveMQXAJmsConnector;
import org.seasar.extension.unit.S2TestCase;

public class ActiveMQXAJmsConnectorConfigTest extends S2TestCase {
	
	private ActiveMQXAJmsConnectorConfig config_;

	public ActiveMQXAJmsConnectorConfigTest(String name) {
		super(name);
	}

	public void setUp() throws Exception {
		include("ActiveMQXAJmsConnectorConfig.dicon");
	}

	public void testGetConnector() throws Exception {

		Object connector = config_.buildComponent();
		assertTrue("Connector isn't ActiveMQXAJmsConnector",
				connector instanceof ActiveMQXAJmsConnector);

		String brokerURL = ((ActiveMQXAJmsConnector) connector).getBrokerURL();
		assertEquals("Property borkerURL isn't correct", brokerURL,
				"tcp://localhost:61616");

		int maxRedelivery = ((ActiveMQXAJmsConnector) connector)
				.getMaxRedelivery();
		assertEquals("Property borkerURL isn't correct", maxRedelivery, 5);
	}
}
