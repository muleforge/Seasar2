/*
 * Copyright (c) Osaka Gas Information System Research Institute Co., Ltd.
 * All rights reserved.  http://www.ogis-ri.co.jp/
 * 
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.extras.seasar2.util;

import java.util.List;

import org.mule.extras.seasar2.connector.ConnectorConfig;
import org.mule.extras.seasar2.connector.impl.ActiveMQJmsConnector;
import org.mule.extras.seasar2.connector.impl.FileConnector;
import org.mule.extras.seasar2.receiver.impl.S2MuleConfiguration;
import org.seasar.extension.unit.S2TestCase;

public class S2MuleComponentUtilTest extends S2TestCase
{

    public S2MuleComponentUtilTest(String name)
    {
        super(name);
    }

    public void setUpGetS2MuleConfigs() throws Exception
    {
        include("S2MuleComponentUtilTest.dicon");
    }

    public void testGetS2MuleConfigs() throws Exception
    {
        List configs = S2MuleComponentUtil.getS2MuleConfigs(getContainer());

        S2MuleConfiguration config1 = (S2MuleConfiguration) configs.get(0);
        assertEquals("HelloUMO", config1.getName());

        S2MuleConfiguration config2 = (S2MuleConfiguration) configs.get(1);
        assertEquals("EchoUMO", config2.getName());

        List connectorConfigs = S2MuleComponentUtil
                .getConnectorConfigs(getContainer());

        ConnectorConfig config3 = (ConnectorConfig) connectorConfigs.get(0);
        assertTrue(config3 instanceof ActiveMQJmsConnector);

        ConnectorConfig config4 = (ConnectorConfig) connectorConfigs.get(1);
        assertTrue(config4 instanceof FileConnector);

    }

}
