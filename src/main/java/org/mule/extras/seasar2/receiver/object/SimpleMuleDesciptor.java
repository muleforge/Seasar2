package org.mule.extras.seasar2.receiver.object;

import org.mule.endpoint.InboundEndpoint;
import org.mule.endpoint.OutboundEndpoint;

public class SimpleMuleDesciptor {
	
	/**
	 * inbound �� Endpoint
	 * �b��I
	 * �����I�ɂ� inboundEndpoints �ɂȂ邩��
	 */
	private InboundEndpoint inboundEndpoit;
	
	/**
	 * outbound �� Endpoiint
	 * �b��I
	 * �����I�ɂ�outboundEndpoints�ɂȂ邩��
	 */
	private OutboundEndpoint outboundEndpoint;

	/**
	 * UMO �̎����N���X
	 * POJO
	 * 
	 */
	private Object umoImpl;
	
	public SimpleMuleDesciptor(InboundEndpoint inboundEndpoint, OutboundEndpoint outboundEndpoint
			,Object umoImpl){
		
	}
	
	
}
