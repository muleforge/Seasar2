package org.mule.extras.seasar2.config;

import org.mule.api.transport.Connector;
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
	Connector getConnector();
}
