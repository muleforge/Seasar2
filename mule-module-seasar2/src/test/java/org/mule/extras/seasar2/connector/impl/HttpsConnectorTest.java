/*
 * Copyright (c) Osaka Gas Information System Research Institute Co., Ltd.
 * All rights reserved.  http://www.ogis-ri.co.jp/
 * 
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.extras.seasar2.connector.impl;

import org.seasar.extension.unit.S2TestCase;

public class HttpsConnectorTest extends S2TestCase
{
    private HttpsConnector config_;
    
    public HttpsConnectorTest(String name)
    {
        super(name);
    }
    
    public void setUp() throws Exception
    {
        include("HttpsConnectorTest.dicon");
    }
    
    public void testGetConnector() throws Exception
    {
        Object connector = config_.buildConnector();
        assertTrue("Connector isn't HttpsConnector"
                , connector instanceof org.mule.transport.http.HttpsConnector);
        
        String clientKeyStore = ((org.mule.transport.http.HttpsConnector) connector)
            .getClientKeyStore();
        assertNotNull("Property clientKeySore isn't correct", clientKeyStore);
        
        String clientKeyStorePassword = ((org.mule.transport.http.HttpsConnector) connector)
            .getClientKeyStorePassword();
        assertEquals("Property clientKeySore isn't correct", "password", clientKeyStorePassword);
        
        String clientKeyStoreType = ((org.mule.transport.http.HttpsConnector) connector)
            .getClientKeyStoreType();
        assertEquals("Property clientKeyStoreType isn't correct", "jks", clientKeyStoreType);
        
        String keyManagerAlgorithm = ((org.mule.transport.http.HttpsConnector) connector)
            .getKeyManagerAlgorithm();
        assertEquals("Property keyManagerAlgorithm isn't correct", "SunX509", keyManagerAlgorithm);
        
        
    }
    
}
