package org.mule.extras.seasar2.config;

import java.util.Map;

/**
 * Mule Componentの構成情報を保持するクラスのインタフェースの構成情報を保持するクラスのインタフェース
 *  
 * @author Saito_Shinya@ogis-ri.co.jp
 *
 */
public interface ComponentConfig {
	
	/**
	 * Mule Componenを返す
	 * 
	 * @return Object Mule Component
	 */
	Object buildComponent();
	
	/**
	 * 全てのプロパティを取得する
	 * 
	 * @return
	 */
	Map getProperties();

	/**
	 * プロパティを取得する
	 * 
	 * @param key
	 * @return
	 */
	Object getProperty(String key);
	
	/**
	 * プロパティを設定する
	 * 
	 * @param key
	 * @param value
	 */
	void setProperty(String key, Object value);
}
