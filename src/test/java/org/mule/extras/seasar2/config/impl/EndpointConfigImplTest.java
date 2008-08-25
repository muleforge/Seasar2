package org.mule.extras.seasar2.config.impl;

import org.mule.api.MuleContext;
import org.mule.api.endpoint.EndpointBuilder;
import org.mule.api.endpoint.InboundEndpoint;
import org.mule.context.DefaultMuleContextFactory;
import org.mule.transport.jms.activemq.ActiveMQJmsConnector;
import org.seasar.extension.unit.S2TestCase;

public class EndpointConfigImplTest extends S2TestCase {
	
	private DefaultMuleContextFactory factory_;
	private Endpoint endpointConfig_;
	
	public EndpointConfigImplTest(String name) 
	{
		super(name);
	}
	
	public void setUp() throws Exception
	{
		include("EndpointConfigImplTest.dicon");
	}
	
	public void testBuildEndpointBuilder() throws Exception 
	{
		MuleContext muleContext = factory_.createMuleContext();
		EndpointBuilder builder = endpointConfig_.buildEndpointBuilder(muleContext);
		InboundEndpoint inboundEndpoint = builder.buildInboundEndpoint();
		
		assertEquals("test", inboundEndpoint.getEndpointURI().getAddress());
		assertEquals(ActiveMQJmsConnector.class, 
					inboundEndpoint.getConnector().getClass());
	}

}
