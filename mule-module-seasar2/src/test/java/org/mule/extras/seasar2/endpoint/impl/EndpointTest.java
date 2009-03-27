/*
 * Copyright (c) Osaka Gas Information System Research Institute Co., Ltd.
 * All rights reserved.  http://www.ogis-ri.co.jp/
 * 
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.extras.seasar2.endpoint.impl;

import org.mule.api.MuleContext;
import org.mule.api.endpoint.EndpointBuilder;
import org.mule.api.endpoint.InboundEndpoint;
import org.mule.context.DefaultMuleContextFactory;
import org.mule.extras.seasar2.endpoint.EndpointConfig;
import org.mule.transport.jms.filters.JmsPropertyFilter;
import org.mule.transport.jms.transformers.JMSMessageToObject;
import org.seasar.extension.unit.S2TestCase;

public class EndpointTest extends S2TestCase 
{
	
	private DefaultMuleContextFactory factory_;
	private EndpointConfig endpoint_;
	
	public EndpointTest(String name) 
	{
		super(name);
	}
	
	public void setUp() throws Exception
	{
		include("EndpointTest.dicon");
	}
	
	public void testBuildInboundEndpointBuilder() throws Exception 
	{
		MuleContext muleContext = factory_.createMuleContext();
		endpoint_.init(muleContext, EndpointConfig.INBOUND_ENDPOINT);
		
		EndpointBuilder builder = endpoint_.getEndpointBuilder();
		InboundEndpoint inboundEndpoint = builder.buildInboundEndpoint();
		
		assertEquals("EndpointURI isn't correct"
				,"test"
				,inboundEndpoint.getEndpointURI().getAddress());
		
		assertEquals("UriScheme isn't correct"
				,"file"
				,endpoint_.getUriScheme());
		
		assertEquals("Connector isn't correct"
					,org.mule.transport.file.FileConnector.class
					,inboundEndpoint.getConnector().getClass());
		
		assertEquals("Filter isn't correct"
				,JmsPropertyFilter.class
				,inboundEndpoint.getFilter().getClass());
		
		assertEquals("Transfomer isn't correct"
				,JMSMessageToObject.class
				,inboundEndpoint.getTransformers().get(0).getClass());
		
//		assertEquals("RemoteSync ins't correct"
//				,true
//				,inboundEndpoint.isRemoteSync());
//		
//		assertEquals("RemoteSyncTimeout isn't correct"
//				,1000
//				,inboundEndpoint.getRemoteSyncTimeout());
		
		String value 
			= (String)inboundEndpoint.getProperties().get("testProperty");
		
		assertEquals("Property isn't correct"
				,"test"
				,value);
		muleContext.dispose();
		
	}

}
