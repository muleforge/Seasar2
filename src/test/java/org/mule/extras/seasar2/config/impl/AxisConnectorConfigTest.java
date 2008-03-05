package org.mule.extras.seasar2.config.impl;

import org.mule.transport.jms.activemq.ActiveMQXAJmsConnector;
import org.mule.transport.soap.axis.AxisConnector;
import org.seasar.extension.unit.S2TestCase;

public class AxisConnectorConfigTest extends S2TestCase {
	
	private AxisConnectorConfig config_;
	
	public AxisConnectorConfigTest(String name) {
		super(name);
	}
	
	public void setUp() throws Exception {
		include("AxisConnectorConfigTest.dicon");
	}
	
	public void testbuildComponent() throws Exception {
		Object connector = config_.buildComponent();
		assertTrue("Connector isn't AxisConnector",
				connector instanceof AxisConnector);
		
		String beanType = (String)(((AxisConnector)connector).getBeanTypes().get(0));
		assertEquals("Property beanType isn't correct",
					"org.mule.extras.seasar2.config.impl.MyClass", beanType);
	}
}
