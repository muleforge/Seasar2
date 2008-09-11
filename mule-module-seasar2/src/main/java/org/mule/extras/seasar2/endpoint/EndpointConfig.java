package org.mule.extras.seasar2.endpoint;

import java.util.Map;

import javax.transaction.TransactionManager;

import org.mule.api.MuleContext;
import org.mule.api.endpoint.EndpointBuilder;
import org.mule.api.endpoint.ImmutableEndpoint;
import org.mule.api.endpoint.InboundEndpoint;
import org.mule.api.endpoint.OutboundEndpoint;
import org.mule.extras.seasar2.connector.ConnectorConfig;

/**
 * Mule Endpointの構成情報を保持するクラスのインターフェース
 * 
 * @author Saito_Shinya@ogis-ri.co.jp
 *
 */
public interface EndpointConfig {

	/**
	 * EndpointBuilderを作成する
	 * 
	 * @return EndpointBuider EndpointBuilder
	 */
	EndpointBuilder buildEndpointBuilder(MuleContext muleContext);
	
	/**
	 * EndpointUriを取得する
	 * 
	 * @return String EndpointUri
	 */
	String getUri();
	
	/**
	 * ConnectorConfigを取得する
	 * 
	 * @return connectorConfig connectorConfig
	 */
	ConnectorConfig getConnectorConfig();
	
	/**
	 * Propertiesを取得する
	 * 
	 * @return properties properties
	 */
	Map getProperties();
	
	/**
	 * EndpointUriのスキームを取得する
	 */
	String getUriScheme();
	
	void setProperty(String key, Object value); 
	
}
