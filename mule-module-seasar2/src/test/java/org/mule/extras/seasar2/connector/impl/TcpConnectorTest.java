/*
 * Copyright (c) Osaka Gas Information System Research Institute Co., Ltd.
 * All rights reserved.  http://www.ogis-ri.co.jp/
 * 
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.extras.seasar2.connector.impl;

import org.mule.extras.seasar2.connector.impl.TcpConnector;
import org.seasar.extension.unit.S2TestCase;

public class TcpConnectorTest extends S2TestCase 
{
    
    private TcpConnector config_;
    
    public TcpConnectorTest(String name) 
    {
        super(name);
    }
    
    public void setUp() throws Exception 
    {
        include("TcpConnectorTest.dicon");
    }
    
    public void testGetConnector() throws Exception 
    {
        
        Object connector = config_.buildConnector();
        assertTrue("Connector isn't TcpConnector"
                , connector instanceof org.mule.transport.tcp.TcpConnector);
        
        int clientSoTimeout = ((org.mule.transport.tcp.TcpConnector) connector)
            .getClientSoTimeout();
        assertEquals("Property clientSoTimeout isn't correct", clientSoTimeout, 10);
        
        int serverSoTimeout = ((org.mule.transport.tcp.TcpConnector) connector)
            .getServerSoTimeout();
        assertEquals("Property serverSoTimeout isn't correct", serverSoTimeout , 10);

        int sendBufferSize = ((org.mule.transport.tcp.TcpConnector) connector)
            .getSendBufferSize();
        assertEquals("Property OutputPattern isn't correct", sendBufferSize, 10);
        
        boolean keepAlive = ((org.mule.transport.tcp.TcpConnector) connector).isKeepAlive();
        assertTrue("Property Streaming isn't correct",keepAlive);
        
        int keepAliveTimeout  
        	= ((org.mule.transport.tcp.TcpConnector) connector).getKeepAliveTimeout();
        assertEquals("keepAliveTimeout isn't correct",
        			keepAliveTimeout , 10);
    }
}
