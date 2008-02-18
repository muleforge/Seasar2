package org.mule.extras.seasar2.config.impl;

import org.mule.extras.seasar2.config.ComponentConfig;
import org.mule.transport.file.FileConnector;

/**
 * FileConnector�̍\������ێ�����N���X�ł��B
 * 
 * @author Saito_Shinya@ogis-ri.co.jp
 *
 */
public class FileConnectorConfig extends AbstractConfig implements
		ComponentConfig {
	
	/**
	 * �C���X�^���X�𐶐�����
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
