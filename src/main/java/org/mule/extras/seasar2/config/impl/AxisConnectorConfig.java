package org.mule.extras.seasar2.config.impl;

import java.util.ArrayList;
import java.util.List;

import org.mule.extras.seasar2.config.ComponentConfig;
import org.mule.transport.soap.axis.AxisConnector;

/**
 * AxisConnector�̍\������ێ�����N���X�ł��B
 * 
 * @author Administrator
 *
 */
public class AxisConnectorConfig extends AbstractConfig implements
		ComponentConfig {
	
	private List beanTypes;
	
	/**
	 * �C���X�^���X�𐶐�����
	 */
	public AxisConnectorConfig() {
		
	}
	

	/**
	 * @see org.mule.extras.seasar2.config.ConnectorConfig#getConnector()
	 */
	public Object buildComponent() {
		AxisConnector connector = new AxisConnector();
		if(beanTypes != null) {
			setProperty("beanTypes", beanTypes);
		}
		populate(connector, properties);
		return connector;
	}
	
	/**
	 * Axis TypeMappingRegistry�ɓo�^����
	 * 
	 * @param beanType �o�^����N���X��
	 */
	public void addBeanType(String beanTypeName) {
		if(beanTypes == null ) {
			beanTypes = new ArrayList();
		}
		beanTypes.add(beanTypeName);
	}
}
