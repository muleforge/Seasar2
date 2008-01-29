package org.mule.extras.seasar2.config.impl;

import org.mule.extras.seasar2.config.ComponentConfig;
import org.mule.util.object.SimpleObjectFactory;

/**
 * Jms��ConnectionFactory�̍\������ێ�����N���X
 * 
 * @author Shinya_Saito@ogis-ri.co.jp
 */
public class ConnectionFactoryConfig  extends AbstractConfig implements ComponentConfig {
	
	/** ConnectionFactory�̃N���X��*/
	private String factoryClassName;
	
	public String getFactoryClassName() {
		return factoryClassName;
	}

	public void setFactoryClassName(String factoryClassName) {
		this.factoryClassName = factoryClassName;
	}
	
	/**
	 * ConnectionFactoryConfig�N���X��ݒ肵��SimpleObjectFactory���\�z����
	 * 
	 * 
	 * @return SimpleObjectFactory
	 */
	public Object builtComponent() {
		return new SimpleObjectFactory(factoryClassName,properties);
	}
}
