package org.mule.extras.seasar2.config;

import org.mule.umo.provider.UMOConnector;

/**
 * Connectorの構成情報を保持するクラスのインタフェース
 * 
 * @author Saito_Shinya@ogis-ri.co.jp
 *
 */
public interface ConnectorConfig {
	/**
	 * UMOConnectorを返す
	 * 
	 * @return UMOConnector
	 */
	UMOConnector getConnector();
}
