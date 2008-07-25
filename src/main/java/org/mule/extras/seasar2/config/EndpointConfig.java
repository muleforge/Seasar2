package org.mule.extras.seasar2.config;

import javax.transaction.TransactionManager;

import org.mule.api.MuleContext;
import org.mule.api.endpoint.EndpointBuilder;
import org.mule.api.endpoint.ImmutableEndpoint;
import org.mule.api.endpoint.InboundEndpoint;
import org.mule.api.endpoint.OutboundEndpoint;

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
}
