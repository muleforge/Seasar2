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
import org.mule.api.endpoint.OutboundEndpoint;
import org.mule.context.DefaultMuleContextFactory;
import org.mule.extras.seasar2.connector.MessageDispatcher;
import org.mule.extras.seasar2.connector.impl.FileMessageDispatcherImpl;
import org.mule.extras.seasar2.endpoint.EndpointConfig;
import org.seasar.extension.unit.S2TestCase;

public class FileEndpointTest extends S2TestCase {
	
	private DefaultMuleContextFactory factory_;
	private FileEndpoint endpoint_;
	
	public FileEndpointTest(String name) 
	{
		super(name);
	}
	
	@Override
    public void setUp() throws Exception
	{
		include("FileEndpointTest.dicon");
	}
	
	public void testBuildOutboundEndpointBuilder() throws Exception 
	{
		MuleContext muleContext = factory_.createMuleContext();
		endpoint_.init(muleContext, EndpointConfig.OUTBOUND_ENDPOINT);
		EndpointBuilder builder = endpoint_.getEndpointBuilder();
		OutboundEndpoint outboundEndpoint = builder.buildOutboundEndpoint();
		
		assertEquals("EndpointURI isn't correct"
				,"/C:/test"
				,outboundEndpoint.getEndpointURI().getAddress());
		
		assertEquals("UriScheme isn't correct"
				,"file"
				,endpoint_.getUriScheme());
		
		assertTrue("Outbound isn't correct",
            outboundEndpoint instanceof OutboundEndpoint);
        
        MessageDispatcher messageDispatcher = endpoint_.getMessageDispatcher();
        
        assertTrue("MessageDispatcher isn't correct",
            messageDispatcher instanceof FileMessageDispatcherImpl);
		
		muleContext.dispose();
	}

}
