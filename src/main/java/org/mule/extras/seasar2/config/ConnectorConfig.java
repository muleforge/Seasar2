package org.mule.extras.seasar2.config;

import java.util.Map;

import org.mule.api.transport.Connector;

/**
 * Mule Connectorの構成情報を保持するクラスのインタフェース
 *  
 * @author Saito_Shinya@ogis-ri.co.jp
 *
 */
public interface ConnectorConfig 
{
    
    /**
     * Connectorを返す
     * 
     * @return Object MuleConnector
     */
    Connector buildConnector();
    
    /**
     * 全てのプロパティを取得する
     * 
     * @return 全てのプロパティ
     */
    Map getProperties();

    /**
     * プロパティを取得する
     * 
     * @param key キー
     * @return プロパティ
     */
    Object getProperty(String key);
    
    /**
     * プロパティを設定する
     * 
     * @param key キー
     * @param value 値
     */
    void setProperty(String key, Object value);
}
