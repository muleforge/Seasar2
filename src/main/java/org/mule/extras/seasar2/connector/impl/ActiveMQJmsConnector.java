package org.mule.extras.seasar2.connector.impl;

import org.mule.api.transport.Connector;
import org.mule.extras.seasar2.connector.ConnectorConfig;
import org.mule.extras.seasar2.exception.S2MuleConfigurationException;
import org.mule.transport.jms.JmsConstants;
import org.mule.util.ObjectNameHelper;
import org.seasar.framework.beans.util.BeanUtil;


/**
 * ActiveMQJmsConnectorの構成情報を保持するクラスです。
 * 
 * @author Saito_Shinya@ogis-ri.co.jp
 *
 */
public class ActiveMQJmsConnector extends JMSConnectorConfig 
{
    
    private String brokerURL;
    
    /**
     * インスタンスを生成する
     */
    public ActiveMQJmsConnector() 
    {
    }
    
    /**
     * @see org.mule.extras.seasar2.connector.ConnectorConfig#getConnector()
     */
    public Connector buildConnector() 
    {
    	
    	Connector connector;
    	 if (brokerURL != null) 
         {
             properties.put("brokerURL", brokerURL);
         } 
         else
         {
             throw new S2MuleConfigurationException("ESML0002", new Object[]{"brokerUrl"});
         }
    	 
    	 if(transacted)
    	 {
    		 connector = new org.mule.transport.jms.activemq.ActiveMQXAJmsConnector(); 
    		 this.setSpecification(JmsConstants.JMS_SPECIFICATION_11);
    	 } 
    	 else
    	 {
    		 connector = new org.mule.transport.jms.activemq.ActiveMQJmsConnector();
    	 }
    	
    	 setName(ObjectNameHelper.getConnectorName(connector));
        //nameとvalueのMap型であるpropertiesをbeanに反映させる
        //populate(connector, properties);
        BeanUtil.copyProperties(this, connector);
        return connector;
    }

    public String getBrokerURL()
    {
        return brokerURL;
    }

    public void setBrokerURL(String brokerURL)
    {
        this.brokerURL = brokerURL;
    }
    
}
