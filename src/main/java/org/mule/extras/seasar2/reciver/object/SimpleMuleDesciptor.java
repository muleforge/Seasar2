package org.mule.extras.seasar2.reciver.object;

import org.mule.umo.endpoint.UMOEndpoint;

public class SimpleMuleDesciptor {
	
	/**
	 * inbound��Endpoint
	 * �b��I
	 * �����I�ɂ�inboundEndpoints�ɂȂ邩��
	 */
	private UMOEndpoint inboundEndpoit;
	
	/**
	 * outbound��Endpoiint
	 * �b��I
	 * �����I�ɂ�outboundEndpoints�ɂȂ邩��
	 */
	private UMOEndpoint outboundEndpoint;

	/**
	 * UMO�̎����N���X
	 * POJO
	 * 
	 */
	private Object umoImpl;
	
	public SimpleMuleDesciptor(UMOEndpoint inboundEndpoint, UMOEndpoint outboundEndpoint
			,Object umoImpl){
		
	}
	
	
}
