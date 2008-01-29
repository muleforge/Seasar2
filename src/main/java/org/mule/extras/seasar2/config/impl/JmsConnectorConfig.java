package org.mule.extras.seasar2.config.impl;

import java.util.Map;

import org.mule.extras.seasar2.config.ComponentConfig;
import org.mule.extras.seasar2.exception.SMuleConfigurationException;
import org.mule.providers.jms.JmsConnector;
import org.mule.util.object.SimpleObjectFactory;

/**
 * JmsConnectorの構成情報を保持するクラスです。
 * 
 * @author Saito_Shinya@ogis-ri.co.jp
 *
 */
public class JmsConnectorConfig extends AbstractConfig implements ComponentConfig {
	
	private final String JMS_CONNECTOR_CLASS_NAME = "org.mule.providers.jms.JmsConnector";
	
	/** ConnectionFactoryのクラス名 */
	private ConnectionFactoryConfig connectionFactoryConfig;

	/**
	 * インスタンスを生成する
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
		//nameとvalueのMap型であるpropertiesをbeanに反映させる
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
