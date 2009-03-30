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
import org.mule.api.endpoint.OutboundEndpoint;
import org.mule.context.DefaultMuleContextFactory;
import org.mule.extras.seasar2.connector.MessageDispatcher;
import org.mule.extras.seasar2.endpoint.EndpointConfig;
import org.mule.transport.cxf.CxfConstants;
import org.seasar.extension.unit.S2TestCase;

public class CxfEndpointTest extends S2TestCase
{
    private DefaultMuleContextFactory factory_;
    private CxfEndpoint endpoint_;
    
    public CxfEndpointTest(String name)
    {
        super(name);
    }
    
    @Override
    public void setUp() throws Exception
    {
        include("CxfEndpointTest.dicon");
    }
    
    public void testBuildInboundEndpointBuilder() throws Exception
    {
        MuleContext muleContext = factory_.createMuleContext();
        endpoint_.init(muleContext, EndpointConfig.INBOUND_ENDPOINT);
        
        EndpointBuilder builder = endpoint_.getEndpointBuilder();
        InboundEndpoint inboundEndpoint = builder.buildInboundEndpoint();
        
        assertEquals("EndpointURI isn't correct",
            "http://test.com",
            inboundEndpoint.getEndpointURI().getAddress());
        
        assertTrue("Inbound isn't correct",
            inboundEndpoint instanceof InboundEndpoint);
        muleContext.dispose();
    }
    
    public void testBuildOutboundendpointBuilder() throws Exception
    {
        MuleContext muleContext = factory_.createMuleContext();
        endpoint_.init(muleContext, EndpointConfig.OUTBOUND_ENDPOINT);
        
        EndpointBuilder builder = endpoint_.getEndpointBuilder();
        OutboundEndpoint outboundEndpoint = builder.buildOutboundEndpoint();
        
        assertTrue("Outbound isn't correct",
            outboundEndpoint instanceof OutboundEndpoint);
        
        MessageDispatcher messageDispatcher = endpoint_.getMessageDispatcher();
        
        assertTrue("MessageDispatcher isn't correct",
            messageDispatcher instanceof MessageDispatcher);
        
        String clientClass = (String) outboundEndpoint.getProperty(CxfConstants.CLIENT_CLASS);
        assertEquals("clientClass property isn't correct",
            "testClient",
            clientClass);
        
        String wsdlPort = (String) outboundEndpoint.getProperty(CxfConstants.CLIENT_PORT);
        assertEquals("wsdlPort property isn't correct",
            "testPort",
            wsdlPort);
        
        String wsdlLocation = (String) outboundEndpoint.getProperty(CxfConstants.WSDL_LOCATION);
        assertEquals("wsdlLocation property isn't correct",
            "http://test.wsdl",
            wsdlLocation);
        
        String operation = (String) outboundEndpoint.getProperty(CxfConstants.OPERATION);
        assertEquals("operation property isn't correct",
            "test",
            operation);
        muleContext.dispose();
   
    }

}


