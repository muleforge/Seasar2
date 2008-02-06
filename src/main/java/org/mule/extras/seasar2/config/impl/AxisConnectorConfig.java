package org.mule.extras.seasar2.config.impl;

import org.mule.extras.seasar2.config.ComponentConfig;
import org.mule.providers.soap.axis.AxisConnector;

/**
 * AxisConnectorの構成情報を保持するクラスです。
 * 
 * @author Administrator
 *
 */
public class AxisConnectorConfig extends AbstractConfig implements
		ComponentConfig {
	
	/**
	 * インスタンスを生成する
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
