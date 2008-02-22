package org.mule.extras.seasar2.receiver.impl;

import java.util.ArrayList;
import java.util.List;

import org.mule.endpoint.InboundEndpoint;
import org.mule.endpoint.OutboundEndpoint;
import org.mule.endpoint.URIBuilder;
import org.mule.endpoint.MuleEndpoint;
import org.mule.api.MuleException;

/**
 * 
 * @author Saito_Shinya@ogis-ri.co.jp
 *
 */
public class S2MuleConfiguration {
	
	/**
	 * ������inbound��Endpoint
	 * 
	 */
	private List inboundEndpoints = new ArrayList();
			
	/**
	 * Message���󂯎��N���X
	 * 
	 */
	private Object umoImpl;
	
	/**
	 * MuleDesciptor�̖��O�ɂȂ�
	 */
	private String name;
	
	private boolean isInitalize = false;
	
	/**
	 * �f�t�H���g�R���X�g���N�^
	 * @throws UMOException
	 */
	public S2MuleConfiguration() throws MuleException {
		
	}
		
	/**
	 * InboundEndpoint��ǉ�����
	 */
	public void addInboundEndpointUri(String uri) {
		inboundEndpoints.add(uri);
	}
	
	public Object getUmoImpl() {
		return umoImpl;
	}

	public void setUmoImpl(Object umoImpl) {
		this.umoImpl = umoImpl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List getInboundEndpoints() {
		return inboundEndpoints;
	}
}
