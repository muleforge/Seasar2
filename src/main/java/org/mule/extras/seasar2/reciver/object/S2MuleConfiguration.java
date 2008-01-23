package org.mule.extras.seasar2.reciver.object;

import java.util.ArrayList;
import java.util.List;

import org.mule.impl.endpoint.MuleEndpoint;
import org.mule.impl.endpoint.MuleEndpointURI;
import org.mule.umo.UMODescriptor;
import org.mule.umo.UMOException;
import org.mule.umo.endpoint.UMOEndpoint;
import org.mule.util.object.ObjectFactory;
import org.mule.util.object.SimpleObjectFactory;

/**
 * 
 * @author Saito_Shinya@ogis-ri.co.jp
 *
 */
public class S2MuleConfiguration {
	
	private final boolean INBOUND = true;
	private final boolean OUTBOUND = false;
	
	/**
	 * inbound��Endpoint
	 * TODO �b��I
	 * �����I�ɂ�inboundEndpoints�ɂȂ邩��
	 */
	private UMOEndpoint inboundEndpoint;
	
	/**
	 * ������inbound��Endpoint
	 * TODO �b��I
	 */
	private List inboundEndpoints = new ArrayList();
	
	/**
	 * outbound��Endpoiint
	 * TODO �b��I �����I�ɂ�outboundEndpoints�ɂȂ邩��
	 * 
	 */
	private UMOEndpoint outboundEndpoint;

	/**
	 * inbound��EndpointUri
	 * TODO �b��I �����I�ɂ�inboundEndpointUris�ɂȂ邩��
	 * 
	 */
	private String inboundEndpointUri;
	
	
	/**
	 * outbound��EndpoiintUri
	 * TODO �b��I �����I�ɂ�outboundEndpointUris�ɂȂ邩��
	 */
	private String outboundEndpointUri;
	
	/**
	 * UMO�̎����N���X
	 * POJO
	 * 
	 */
	private Object umoImpl;
	
	/**
	 * Name
	 * MuleDesciptor�̖��O�ɂȂ�
	 */
	private String name;
	
	private boolean isInitalize = false;
	
	/**
	 * �f�t�H���g�R���X�g���N�^
	 * @throws UMOException
	 */
	public S2MuleConfiguration() throws UMOException { }
	
	/**
	 * Endpoint���N���X�Ŏw�肳�ꂽ�ꍇ�̃R���X�g���N�^
	 * 
	 * @param inboundEndpoint
	 * @param outboundEndpoint
	 * @param umoImpl
	 */
	public S2MuleConfiguration(UMOEndpoint inboundEndpoint, UMOEndpoint outboundEndpoint
			,Object umoImpl) throws UMOException {
		this.inboundEndpoint = inboundEndpoint;
		this.outboundEndpoint = outboundEndpoint;
		this.umoImpl = umoImpl;
		initialize();
	}
	
	/**
	 * Endpoint��url�Ŏw�肳�ꂽ�ꍇ�̃R���X�g���N�^
	 * 
	 * @param inboundEndpointUri
	 * @param outboundEndpointUri
	 * @param umoImpl
	 * @throws UMOException
	 */
	public S2MuleConfiguration(String inboundEndpointUri, String outboundEndpointUri
			,Object umoImpl) throws UMOException {
		this.inboundEndpointUri = inboundEndpointUri;
		this.outboundEndpointUri = outboundEndpointUri;
		this.umoImpl = umoImpl;
		initialize();
	}
	
	/**
	 * ����������
	 */
	public void initialize() throws UMOException {
		//inbound��null�̂Ƃ���Exception�������K�v
		if ( inboundEndpoint == null && inboundEndpointUri == null ) {
			//TODO Exception����
			//throw Exceptin();
		} else if( inboundEndpoint == null && inboundEndpointUri != null ) {
			//inboundUri����inbound�𐶐�
			inboundEndpoint = new MuleEndpoint(inboundEndpointUri,INBOUND);
		}
		if(inboundEndpoint != null) {
			inboundEndpoints.add(inboundEndpoint);
		}
		
		//outbondUri����outbound�𐶐�
		if ( outboundEndpoint == null && outboundEndpointUri != null ) {
			outboundEndpoint = new MuleEndpoint(outboundEndpointUri,OUTBOUND);
		}
		
		isInitalize = true;
	}
	
	/**
	 * 
	 */
	public void addEndpoint(UMOEndpoint endpoint) {
		//Inbound�̏ꍇ
		if(endpoint.getType().equals(UMOEndpoint.ENDPOINT_TYPE_RECEIVER)){
			inboundEndpoints.add(endpoint);
		} else {
			//TODO outbound�̏ꍇ�̏���
		}
	}
	
	public boolean isInitalize() {
		return isInitalize;
	}
	
	public UMOEndpoint getInboundEndpoint() {
		return inboundEndpoint;
	}

	public void setInboundEndpoint(UMOEndpoint inboundEndpoint) {
		this.inboundEndpoint = inboundEndpoint;
	}

	public String getInboundEndpointUri() {
		return inboundEndpointUri;
	}

	public void setInboundEndpointUri(String inboundEndpointUri) {
		this.inboundEndpointUri = inboundEndpointUri;
	}

	public UMOEndpoint getOutboundEndpoint() {
		return outboundEndpoint;
	}

	public void setOutboundEndpoint(UMOEndpoint outboundEndpoint) {
		this.outboundEndpoint = outboundEndpoint;
	}

	public String getOutboundEndpointUri() {
		return outboundEndpointUri;
	}

	public void setOutboundEndpointUri(String outboundEndpointUri) {
		this.outboundEndpointUri = outboundEndpointUri;
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
