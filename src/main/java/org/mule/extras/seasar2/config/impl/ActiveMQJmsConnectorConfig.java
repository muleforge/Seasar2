package org.mule.extras.seasar2.config.impl;

import org.mule.extras.seasar2.config.ComponentConfig;
import org.mule.extras.seasar2.exception.S2MuleConfigurationException;
import org.mule.transport.jms.activemq.ActiveMQJmsConnector;


/**
 * ActiveMQJmsConnector�̍\������ێ�����N���X�ł��B
 * 
 * @author Saito_Shinya@ogis-ri.co.jp
 *
 */
public class ActiveMQJmsConnectorConfig extends AbstractConfig implements
		ComponentConfig {
	
	protected String brokerURL;

	/**
	 * �C���X�^���X�𐶐�����
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
		//name��value��Map�^�ł���properties��bean�ɔ��f������
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
