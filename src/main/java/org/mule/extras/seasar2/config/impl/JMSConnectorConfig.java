package org.mule.extras.seasar2.config.impl;

import org.mule.api.transport.Connector;
import org.mule.extras.seasar2.config.ConnectorConfig;

public abstract class JMSConnectorConfig extends AbstractConfig implements
		ConnectorConfig 
{
	 /** MQのブローカーURL */
    protected String brokerURL;

    public String getBrokerURL()
    {
        return brokerURL;
    }

    public void setBrokerURL(String brokerURL)
    {
        this.brokerURL = brokerURL;
    }
}
