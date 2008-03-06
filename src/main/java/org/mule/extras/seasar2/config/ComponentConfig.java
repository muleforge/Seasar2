package org.mule.extras.seasar2.config;

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

}
