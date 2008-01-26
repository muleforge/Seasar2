package org.mule.extras.seasar2.config.impl;

import org.mule.extras.seasar2.config.impl.ConnectionFactoryConfig;
import org.mule.extras.seasar2.config.impl.JmsConnectorConfig;
import org.seasar.extension.unit.S2TestCase;

public class JmsConnectorConfigTest extends S2TestCase {
	
	private JmsConnectorConfig config_;
	
	public JmsConnectorConfigTest(String name) {
		super(name);
	}
	
	public void setUp() throws Exception {
		include("org/mule/extras/seasar2/config/Impl/JmsConnectorConfigTest.dicon");
	}

	public void testGetConnector() throws Exception {
		
		ConnectionFactoryConfig cfConfig = config_.getConnectionFactoryConfig();
		String connectionFactoryName = cfConfig.getFactoryClassName();
		assertEquals(connectionFactoryName, 
				"org.apache.activemq.ActiveMQConnectionFactory");
		
		String brokerURL = (String)cfConfig.getProperty("brokerURL");
		assertEquals(brokerURL, "tcp://localhost:61616");
		
	}
}
