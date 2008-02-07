package org.mule.extras.seasar2.config.impl;

import java.util.ArrayList;
import java.util.List;

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
	
	private List beanTypes;
	
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
		if(beanTypes != null) {
			setProperty("beanTypes", beanTypes);
		}
		populate(connector, properties);
		return connector;
	}
	
	public void addBeanType(Object beanType) {
		if(beanTypes == null ) {
			beanTypes = new ArrayList();
		}
		beanTypes.add(beanType);
	}

}
