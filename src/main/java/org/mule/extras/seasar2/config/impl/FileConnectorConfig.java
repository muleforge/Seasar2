package org.mule.extras.seasar2.config.impl;

import org.mule.extras.seasar2.config.ComponentConfig;
import org.mule.extras.seasar2.exception.S2MuleConfigurationException;
import org.mule.providers.file.FileConnector;
import org.mule.util.object.SimpleObjectFactory;

/**
 * FileConnectorの構成情報を保持するクラスです。
 * 
 * @author Saito_Shinya@ogis-ri.co.jp
 *
 */
public class FileConnectorConfig extends AbstractConfig implements
		ComponentConfig {
	
	/**
	 * インスタンスを生成する
	 */
	public FileConnectorConfig() {
		
	}
	
	
	/**
	 * @see org.mule.extras.seasar2.config.ConnectorConfig#getConnector()
	 */
	public Object builtComponent() {
		FileConnector connector = new FileConnector();
		populate(connector,properties); 
		return connector;
	}

}
