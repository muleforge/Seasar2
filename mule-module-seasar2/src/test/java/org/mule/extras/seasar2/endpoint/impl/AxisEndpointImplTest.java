package org.mule.extras.seasar2.endpoint.impl;

import org.mule.api.MuleContext;
import org.mule.api.endpoint.EndpointBuilder;
import org.mule.api.endpoint.InboundEndpoint;
import org.mule.context.DefaultMuleContextFactory;
import org.mule.extras.seasar2.endpoint.AbstractEndpoint;
import org.mule.transport.jms.filters.JmsPropertyFilter;
import org.mule.transport.jms.transformers.JMSMessageToObject;
import org.seasar.extension.unit.S2TestCase;

public class AxisEndpointImplTest extends S2TestCase {
	
	private DefaultMuleContextFactory factory_;
	private AbstractEndpoint endpointConfig_;
	
	public AxisEndpointImplTest(String name) 
	{
		super(name);
	}
	
	public void setUp() throws Exception
	{
		include("AxisEndpointImplTest.dicon");
	}
	
	public void testBuildEndpointBuilder() throws Exception 
	{
		MuleContext muleContext = factory_.createMuleContext();
		EndpointBuilder builder = endpointConfig_.buildEndpointBuilder(muleContext);
		InboundEndpoint inboundEndpoint = builder.buildInboundEndpoint();
		
		assertEquals("EndpointURI isn't correct"
				,"http://test.com"
				,inboundEndpoint.getEndpointURI().getAddress());
		
		assertEquals("UriScheme isn't correct"
				,"axis"
				,endpointConfig_.getUriScheme());
		
		String style 
			= (String)inboundEndpoint.getProperties().get("style");
		
		assertEquals("Property isn't correct"
				,"doc"
				,style);
		
		String value
			= (String)inboundEndpoint.getProperties().get("use");
		
		assertEquals("Property isn't correct"
				,"use"
				,value);
		
	}

}
