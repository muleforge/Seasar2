package org.mule.extras.seasar2.config.impl;

import org.mule.api.transport.Connector;
import org.mule.extras.seasar2.config.ConnectorConfig;
import org.mule.transport.file.FileConnector;

/**
 * FileConnectorの構成情報を保持するクラスです。
 * 
 * @author Saito_Shinya@ogis-ri.co.jp
 *
 */
public class FileConnectorConfigImpl extends AbstractConfig implements ConnectorConfig
{
    
    /**
     * インスタンスを生成する
     */
    public FileConnectorConfigImpl() 
    {
        
    }
    
    /**
     * @see org.mule.extras.seasar2.config.ConnectorConfig#getConnector()
     */
    public Connector buildConnector() 
    {
        FileConnector connector = new FileConnector();
        populate(connector, properties); 
        return connector;
    }

}
