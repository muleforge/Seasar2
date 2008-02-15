package org.mule.extras.seasar2.receiver.object;

import org.mule.umo.endpoint.UMOEndpoint;

public class SimpleMuleDesciptor {
	
	/**
	 * inbound の Endpoint
	 * 暫定的
	 * 将来的には inboundEndpoints になるかも
	 */
	private UMOEndpoint inboundEndpoit;
	
	/**
	 * outbound の Endpoiint
	 * 暫定的
	 * 将来的にはoutboundEndpointsになるかも
	 */
	private UMOEndpoint outboundEndpoint;

	/**
	 * UMO の実装クラス
	 * POJO
	 * 
	 */
	private Object umoImpl;
	
	public SimpleMuleDesciptor(UMOEndpoint inboundEndpoint, UMOEndpoint outboundEndpoint
			,Object umoImpl){
		
	}
	
	
}
