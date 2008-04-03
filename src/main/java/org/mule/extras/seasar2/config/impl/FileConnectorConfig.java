package org.mule.extras.seasar2.config.impl;

import org.mule.extras.seasar2.config.ComponentConfig;
import org.mule.transport.file.FileConnector;

/**
 * FileConnectorの構成情報を保持するクラスです。
 * 
 * @author Saito_Shinya@ogis-ri.co.jp
 *
 */
public class FileConnectorConfig extends AbstractConfig
{
    
    /**
     * インスタンスを生成する
     */
    public FileConnectorConfig() 
    {
        
    }
    
    /**
     * @see org.mule.extras.seasar2.config.ConnectorConfig#getConnector()
     */
    public Object buildComponent() 
    {
        FileConnector connector = new FileConnector();
        populate(connector, properties); 
        return connector;
    }

}
