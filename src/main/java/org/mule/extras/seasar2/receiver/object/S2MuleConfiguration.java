package org.mule.extras.seasar2.receiver.object;

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
	
	private final boolean INBOUND = true;
	private final boolean OUTBOUND = false;
	
	/**
	 * inbound��Endpoint
	 * TODO �b��I
	 * �����I�ɂ�inboundEndpoints�ɂȂ邩��
	 */
	private InboundEndpoint inboundEndpoint;
	
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
	private OutboundEndpoint outboundEndpoint;

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
	public S2MuleConfiguration() throws MuleException {
		
	}
	
	/**
	 * Endpoint���N���X�Ŏw�肳�ꂽ�ꍇ�̃R���X�g���N�^
	 * 
	 * @param inboundEndpoint
	 * @param outboundEndpoint
	 * @param umoImpl
	 */
	public S2MuleConfiguration(InboundEndpoint inboundEndpoint, OutboundEndpoint outboundEndpoint
			,Object umoImpl) throws MuleException {
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
			,Object umoImpl) throws MuleException {
		this.inboundEndpointUri = inboundEndpointUri;
		this.outboundEndpointUri = outboundEndpointUri;
		this.umoImpl = umoImpl;
		initialize();
	}
	
	/**
	 * ����������
	 */
	public void initialize() throws MuleException {
		//inbound��null�̂Ƃ���Exception�������K�v
		if ( inboundEndpoint == null && inboundEndpointUri == null ) {
			//TODO Exception����
			//throw Exceptin();
		} else if( inboundEndpoint == null && inboundEndpointUri != null ) {
			//inboundUri����inbound�𐶐�
			URIBuilder uriBuilder = new URIBuilder(inboundEndpointUri);
			inboundEndpoint = new InboundEndpoint();
			inboundEndpoint.setEndpointURI(uriBuilder.getEndpoint());
		}
		if(inboundEndpoint != null) {
			inboundEndpoints.add(inboundEndpoint);
		}
		
		//outbondUri����outbound�𐶐�
		if ( outboundEndpoint == null && outboundEndpointUri != null ) {
			URIBuilder uriBuilder = new URIBuilder(outboundEndpointUri);
			outboundEndpoint = new OutboundEndpoint();
			outboundEndpoint.setEndpointURI(uriBuilder.getEndpoint());
		}
		
		isInitalize = true;
	}
	
	/**
	 *TODO ���� �s�v���� 
	 */
	public void addEndpoint(MuleEndpoint endpoint) {
//		//Inbound�̏ꍇ
//		if(endpoint.getType().equals(UMOEndpoint.ENDPOINT_TYPE_RECEIVER)){
//			inboundEndpoints.add(endpoint);
//		} else {
//			
//		}
	}
	
	public boolean isInitalize() {
		return isInitalize;
	}
	
	public InboundEndpoint getInboundEndpoint() {
		return inboundEndpoint;
	}

	public void setInboundEndpoint(InboundEndpoint inboundEndpoint) {
		this.inboundEndpoint = inboundEndpoint;
	}

	public String getInboundEndpointUri() {
		return inboundEndpointUri;
	}

	public void setInboundEndpointUri(String inboundEndpointUri) {
		this.inboundEndpointUri = inboundEndpointUri;
	}

	public OutboundEndpoint getOutboundEndpoint() {
		return outboundEndpoint;
	}

	public void setOutboundEndpoint(OutboundEndpoint outboundEndpoint) {
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
