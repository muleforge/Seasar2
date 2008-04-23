package org.mule.extras.seasar2.config.impl;

import org.mule.api.transport.Connector;
import org.mule.extras.seasar2.config.TransactionConnector;
import org.mule.extras.seasar2.exception.S2MuleConfigurationException;
import org.mule.transport.jms.activemq.ActiveMQXAJmsConnector;

public class ActiveMQXAJmsConnectorConfigImpl 
    extends ActiveMQJmsConnectorConfigImpl implements TransactionConnector {
    
    /**
     * @see org.mule.extras.seasar2.config.ConnectorConfig#getConnector()
     */
    public Connector buildConnector()
    {
        ActiveMQXAJmsConnector connector = new ActiveMQXAJmsConnector();
        if (brokerURL != null)
        {
            properties.put("brokerURL", brokerURL);
        }
        else
        {
            throw new S2MuleConfigurationException("ESML0002", new Object[]{"brokerUrl"});
        }
        
        //nameとvalueのMap型であるpropertiesをbeanに反映させる
        populate(connector, properties);
        
        return connector;
    }
    
    public boolean isTransaction()
    {
        return true;
    }
}
