/*
 * Copyright (c) Osaka Gas Information System Research Institute Co., Ltd.
 * All rights reserved.  http://www.ogis-ri.co.jp/
 * 
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.extras.seasar2.connector.impl;

import org.mule.extras.seasar2.connector.impl.HttpConnector;
import org.seasar.extension.unit.S2TestCase;

public class HttpConnectorTest extends S2TestCase 
{
    
    private HttpConnector config_;
    
    public HttpConnectorTest(String name) 
    {
        super(name);
    }
    
    @Override
    public void setUp() throws Exception 
    {
        include("HttpConnectorTest.dicon");
    }
    
    public void testGetConnector() throws Exception 
    {
        
        Object connector = config_.buildConnector();
        assertTrue("Connector isn't HttpConnector"
                , connector instanceof org.mule.transport.http.HttpConnector);
        
        String proxyHostname = ((org.mule.transport.http.HttpConnector) connector)
            .getProxyHostname();
        assertEquals("Property proxyHostname isn't correct", proxyHostname, "proxy.test.org");
        
        int proxyPort = ((org.mule.transport.http.HttpConnector) connector)
            .getProxyPort();
        assertEquals("Property proxyPort isn't correct", proxyPort , 8080);

        String proxyUsername = ((org.mule.transport.http.HttpConnector) connector)
            .getProxyUsername();
        assertEquals("Property proxyUsername isn't correct", proxyUsername, "user");
        
        String proxyPassword = ((org.mule.transport.http.HttpConnector) connector)
                .getProxyPassword();
        assertEquals("Property proxyPassword isn't correct",proxyPassword,"password");
        
        String cookieSpec  
        	= ((org.mule.transport.http.HttpConnector) connector)
        	    .getCookieSpec();
        assertEquals("Property cookieSpec isn't correct",
        			cookieSpec , "netscape");
        boolean enableCookies
            = ((org.mule.transport.http.HttpConnector) connector)
                .isEnableCookies();
        assertTrue("Property enableCookies isn't correct", enableCookies);
        
    }
}
