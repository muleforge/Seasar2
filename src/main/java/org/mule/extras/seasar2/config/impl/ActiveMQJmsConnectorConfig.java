package org.mule.extras.seasar2.config.impl;

import org.mule.extras.seasar2.config.ComponentConfig;
import org.mule.extras.seasar2.exception.S2MuleConfigurationException;
import org.mule.transport.jms.activemq.ActiveMQJmsConnector;


/**
 * ActiveMQJmsConnectorの構成情報を保持するクラスです。
 * 
 * @author Saito_Shinya@ogis-ri.co.jp
 *
 */
public class ActiveMQJmsConnectorConfig extends AbstractConfig implements
		ComponentConfig {
	
	/** MQのブローカーURL */
	protected String brokerURL;

	/**
	 * インスタンスを生成する
	 */
	public ActiveMQJmsConnectorConfig() {
	}
	
	/**
	 * @see org.mule.extras.seasar2.config.ConnectorConfig#getConnector()
	 */
	public Object buildComponent() {
		ActiveMQJmsConnector connector = new ActiveMQJmsConnector();
		if(brokerURL != null) {
			properties.put("brokerURL", brokerURL);
		} else {
			throw new S2MuleConfigurationException("ESML0002",new Object[]{"brokerUrl"});
		}
		//nameとvalueのMap型であるpropertiesをbeanに反映させる
		populate(connector, properties);
		
		return connector;
	}

	public String getBrokerURL() {
		return brokerURL;
	}

	public void setBrokerURL(String brokerURL) {
		this.brokerURL = brokerURL;
	}
	
	
}
