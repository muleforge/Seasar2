package org.mule.extras.seasar2.config;

import org.mule.api.endpoint.EndpointBuilder;
import org.mule.api.endpoint.ImmutableEndpoint;
import org.mule.api.endpoint.InboundEndpoint;

/**
 * Mule Endpointの構成情報を保持するクラスのインターフェース
 * 
 * @author Saito_Shinya@ogis-ri.co.jp
 *
 */
public interface EndpointConfig {

	/**
	 * InboundEndpointを作成する
	 * 
	 * @return InboundEndpoint InboundEndpoint
	 */
	InboundEndpoint buildInboundEndpoint();
	
}
