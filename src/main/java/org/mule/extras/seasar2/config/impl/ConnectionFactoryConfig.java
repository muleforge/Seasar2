package org.mule.extras.seasar2.config.impl;

import org.mule.extras.seasar2.config.ComponentConfig;
import org.mule.util.object.SimpleObjectFactory;

/**
 * JmsのConnectionFactoryの構成情報を保持するクラス
 * 
 * @author Shinya_Saito@ogis-ri.co.jp
 */
public class ConnectionFactoryConfig  extends AbstractConfig implements ComponentConfig {
	
	/** ConnectionFactoryのクラス名*/
	private String factoryClassName;
	
	public String getFactoryClassName() {
		return factoryClassName;
	}

	public void setFactoryClassName(String factoryClassName) {
		this.factoryClassName = factoryClassName;
	}
	
	/**
	 * ConnectionFactoryConfigクラスを設定したSimpleObjectFactoryを構築する
	 * 
	 * 
	 * @return SimpleObjectFactory
	 */
	public Object builtComponent() {
		return new SimpleObjectFactory(factoryClassName,properties);
	}
}
