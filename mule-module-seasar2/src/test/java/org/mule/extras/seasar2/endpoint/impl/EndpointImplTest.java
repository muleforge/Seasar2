package org.mule.extras.seasar2.endpoint.impl;

import org.mule.api.MuleContext;
import org.mule.api.endpoint.EndpointBuilder;
import org.mule.api.endpoint.InboundEndpoint;
import org.mule.context.DefaultMuleContextFactory;
import org.mule.extras.seasar2.endpoint.AbstractEndpoint;
import org.mule.transport.jms.filters.JmsPropertyFilter;
import org.mule.transport.jms.transformers.JMSMessageToObject;
import org.seasar.extension.unit.S2TestCase;

public class EndpointImplTest extends S2TestCase {
	
	private DefaultMuleContextFactory factory_;
	private AbstractEndpoint endpointConfig_;
	
	public EndpointImplTest(String name) 
	{
		super(name);
	}
	
	public void setUp() throws Exception
	{
		include("EndpointImplTest.dicon");
	}
	
	public void testBuildEndpointBuilder() throws Exception 
	{
		MuleContext muleContext = factory_.createMuleContext();
		EndpointBuilder builder = endpointConfig_.buildEndpointBuilder(muleContext);
		InboundEndpoint inboundEndpoint = builder.buildInboundEndpoint();
		
		assertEquals("EndpointURI isn't correct"
				,"test"
				,inboundEndpoint.getEndpointURI().getAddress());
		
		assertEquals("UriScheme isn't correct"
				,"file"
				,endpointConfig_.getUriScheme());
		
		assertEquals("Connector isn't correct"
					,org.mule.transport.file.FileConnector.class
					,inboundEndpoint.getConnector().getClass());
		
		assertEquals("Filter isn't correct"
				,JmsPropertyFilter.class
				,inboundEndpoint.getFilter().getClass());
		
		assertEquals("Transfomer isn't correct"
				,JMSMessageToObject.class
				,inboundEndpoint.getTransformers().get(0).getClass());
		
		assertEquals("RemoteSync ins't correct"
				,true
				,inboundEndpoint.isRemoteSync());
		
		assertEquals("RemoteSyncTimeout isn't correct"
				,1000
				,inboundEndpoint.getRemoteSyncTimeout());
		
		String value 
			= (String)inboundEndpoint.getProperties().get("testProperty");
		
		assertEquals("Property isn't correct"
				,"test"
				,value);
		
	}

}
