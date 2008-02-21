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
	
//	private final boolean INBOUND = true;
//	private final boolean OUTBOUND = false;
	
	/**
	 * inboundのEndpoint
	 * TODO 暫定的
	 * 将来的にはinboundEndpointsになるかも
	 */
	private InboundEndpoint inboundEndpoint;
	
	/**
	 * 複数のinboundのEndpoint
	 * TODO 暫定的
	 */
	private List inboundEndpoints = new ArrayList();
	
	/**
	 * outboundのEndpoiint
	 * TODO 暫定的 将来的にはoutboundEndpointsになるかも
	 * 
	 */
	//private OutboundEndpoint outboundEndpoint;

	/**
	 * inboundのEndpointUri
	 * TODO 暫定的 将来的にはinboundEndpointUrisになるかも
	 * 
	 */
	private String inboundEndpointUri;
	
	
	/**
	 * outboundのEndpoiintUri
	 * TODO 暫定的 将来的にはoutboundEndpointUrisになるかも
	 */
	//private String outboundEndpointUri;
	
	/**
	 * UMOの実装クラス
	 * POJO
	 * 
	 */
	private Object umoImpl;
	
	/**
	 * Name
	 * MuleDesciptorの名前になる
	 */
	private String name;
	
	private boolean isInitalize = false;
	
	/**
	 * デフォルトコンストラクタ
	 * @throws UMOException
	 */
	public S2MuleConfiguration() throws MuleException {
		
	}
	
	/**
	 * Endpointをクラスで指定された場合のコンストラクタ
	 * 
	 * @param inboundEndpoint
	 * @param outboundEndpoint
	 * @param umoImpl
	 */
	public S2MuleConfiguration(InboundEndpoint inboundEndpoint, OutboundEndpoint outboundEndpoint
			,Object umoImpl) throws MuleException {
		this.inboundEndpoint = inboundEndpoint;
		//this.outboundEndpoint = outboundEndpoint;
		this.umoImpl = umoImpl;
		initialize();
	}
	
	/**
	 * Endpointをurlで指定された場合のコンストラクタ
	 * 
	 * @param inboundEndpointUri
	 * @param outboundEndpointUri
	 * @param umoImpl
	 * @throws UMOException
	 */
	public S2MuleConfiguration(String inboundEndpointUri, String outboundEndpointUri
			,Object umoImpl) throws MuleException {
		this.inboundEndpointUri = inboundEndpointUri;
		//this.outboundEndpointUri = outboundEndpointUri;
		this.umoImpl = umoImpl;
		initialize();
	}
	
	/**
	 * 初期化処理
	 */
	public void initialize() throws MuleException {
		//inboundがnullのときはException処理が必要
		if ( inboundEndpoint == null && inboundEndpointUri == null ) {
			//TODO Exception処理
			//throw Exceptin();
		} else if( inboundEndpoint == null && inboundEndpointUri != null ) {
			//inboundUriからinboundを生成
			URIBuilder uriBuilder = new URIBuilder(inboundEndpointUri);
			inboundEndpoint = new InboundEndpoint();
			inboundEndpoint.setEndpointURI(uriBuilder.getEndpoint());
		}
		if(inboundEndpoint != null) {
			inboundEndpoints.add(inboundEndpoint);
		}
		
		//outbondUriからoutboundを生成
//		if ( outboundEndpoint == null && outboundEndpointUri != null ) {
//			URIBuilder uriBuilder = new URIBuilder(outboundEndpointUri);
//			outboundEndpoint = new OutboundEndpoint();
//			outboundEndpoint.setEndpointURI(uriBuilder.getEndpoint());
//		}
		
		isInitalize = true;
	}
	
	/**
	 *TODO 検討 不要かも 
	 */
	public void addEndpoint(MuleEndpoint endpoint) {
//		//Inboundの場合
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

//	public OutboundEndpoint getOutboundEndpoint() {
//		return outboundEndpoint;
//	}
//
//	public void setOutboundEndpoint(OutboundEndpoint outboundEndpoint) {
//		this.outboundEndpoint = outboundEndpoint;
//	}
//
//	public String getOutboundEndpointUri() {
//		return outboundEndpointUri;
//	}
//
//	public void setOutboundEndpointUri(String outboundEndpointUri) {
//		this.outboundEndpointUri = outboundEndpointUri;
//	}

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
