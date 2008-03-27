package org.mule.extras.seasar2.config;

import java.util.Map;

/**
 * Mule Componentの構成情報を保持するクラスのインタフェースの構成情報を保持するクラスのインタフェース
 *  
 * @author Saito_Shinya@ogis-ri.co.jp
 *
 */
public interface ComponentConfig 
{
    
    /**
     * Mule Componentを返す
     * 
     * @return Object Mule Component
     */
    Object buildComponent();
    
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
