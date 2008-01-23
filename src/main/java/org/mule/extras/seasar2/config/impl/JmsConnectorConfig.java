package org.mule.extras.seasar2.config.impl;

import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.mule.extras.seasar2.config.ComponentConfig;
import org.mule.extras.seasar2.config.ConnectorConfig;
import org.mule.extras.seasar2.exception.ProprtyNotFoundException;
import org.mule.extras.seasar2.exception.SMuleConfigurationException;
import org.mule.extras.seasar2.exception.SMuleRuntimeException;
import org.mule.providers.jms.JmsConnector;
import org.mule.umo.provider.UMOConnector;
import org.mule.util.object.SimpleObjectFactory;
import org.seasar.framework.beans.PropertyNotFoundRuntimeException;

/**
 * JmsConnector�̍\������ێ�����N���X�ł��B
 * 
 * @author Saito_Shinya@ogis-ri.co.jp
 *
 */
public class JmsConnectorConfig extends AbstractConfig implements ComponentConfig {
	
	private final String JMS_CONNECTOR_CLASS_NAME = "org.mule.providers.jms.JmsConnector";
	
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
			throw new SMuleConfigurationException("ESML0002",new Object[]{"connectionFactoryConfig"});
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
