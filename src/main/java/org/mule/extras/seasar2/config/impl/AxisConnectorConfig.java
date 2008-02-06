package org.mule.extras.seasar2.config.impl;

import org.mule.extras.seasar2.config.ComponentConfig;
import org.mule.providers.soap.axis.AxisConnector;

/**
 * AxisConnector�̍\������ێ�����N���X�ł��B
 * 
 * @author Administrator
 *
 */
public class AxisConnectorConfig extends AbstractConfig implements
		ComponentConfig {
	
	/**
	 * �C���X�^���X�𐶐�����
	 */
	public AxisConnectorConfig() {
		
	}
	

	/**
	 * @see org.mule.extras.seasar2.config.ConnectorConfig#getConnector()
	 */
	public Object builtComponent() {
		AxisConnector connector = new AxisConnector();
		populate(connector, properties);
		return connector;
	}

}
