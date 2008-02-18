package org.mule.extras.seasar2.config.impl;

import java.util.Map;

import org.mule.extras.seasar2.config.ComponentConfig;
import org.mule.extras.seasar2.exception.S2MuleConfigurationException;
import org.mule.transport.jms.JmsConnector;
import org.mule.util.object.SimpleObjectFactory;

/**
 * JmsConnector�̍\������ێ�����N���X�ł��B
 * 
 * @author Saito_Shinya@ogis-ri.co.jp
 *
 */
public class JmsConnectorConfig extends AbstractConfig implements ComponentConfig {
	
	/** ConnectionFactory�̃N���X�� */
	private ConnectionFactoryConfig connectionFactoryConfig;

	/**
	 * �C���X�^���X�𐶐�����
	 */
	public JmsConnectorConfig() {
	}
	
	/**
	 * @see org.mule.extras.seasar2.config.ConnectorConfig#getConnector()
	 */
	public Object builtComponent() {
		JmsConnector connector = new JmsConnector();
		if(connectionFactoryConfig != null) {
			String factoryClassName = connectionFactoryConfig.getFactoryClassName();
			Map factoryProperties = connectionFactoryConfig.getProperties();
			properties.put("connectionFactory", 
					new SimpleObjectFactory(factoryClassName,factoryProperties));
		} else {
			throw new S2MuleConfigurationException("ESML0002",new Object[]{"connectionFactoryConfig"});
		}
		//name��value��Map�^�ł���properties��bean�ɔ��f������
		populate(connector, properties);
		
		return connector;
		
	}

	public ConnectionFactoryConfig getConnectionFactoryConfig() {
		return connectionFactoryConfig;
	}

	public void setConnectionFactoryConfig(
			ConnectionFactoryConfig connectionFactoryConfig) {
		this.connectionFactoryConfig = connectionFactoryConfig;
	}
	
}