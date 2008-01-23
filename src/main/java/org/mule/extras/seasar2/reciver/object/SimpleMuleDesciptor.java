package org.mule.extras.seasar2.reciver.object;

import org.mule.umo.endpoint.UMOEndpoint;

public class SimpleMuleDesciptor {
	
	/**
	 * inboundのEndpoint
	 * 暫定的
	 * 将来的にはinboundEndpointsになるかも
	 */
	private UMOEndpoint inboundEndpoit;
	
	/**
	 * outboundのEndpoiint
	 * 暫定的
	 * 将来的にはoutboundEndpointsになるかも
	 */
	private UMOEndpoint outboundEndpoint;

	/**
	 * UMOの実装クラス
	 * POJO
	 * 
	 */
	private Object umoImpl;
	
	public SimpleMuleDesciptor(UMOEndpoint inboundEndpoint, UMOEndpoint outboundEndpoint
			,Object umoImpl){
		
	}
	
	
}
