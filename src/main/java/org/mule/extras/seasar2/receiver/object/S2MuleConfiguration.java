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
	
	/**
	 * 複数のinboundのEndpoint
	 * 
	 */
	private List inboundEndpoints = new ArrayList();
			
	/**
	 * Messageを受け取るクラス
	 * 
	 */
	private Object umoImpl;
	
	/**
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
	 * 初期化処理
	 */
//	public void initialize() throws MuleException {
//		//inboundがnullのときはException処理が必要
//		if ( inboundEndpoint == null && inboundEndpointUri == null ) {
//			//TODO Exception処理
//			//throw Exceptin();
//		} else if( inboundEndpoint == null && inboundEndpointUri != null ) {
//			//inboundUriからinboundを生成
//			URIBuilder uriBuilder = new URIBuilder(inboundEndpointUri);
//			inboundEndpoint = new InboundEndpoint();
//			inboundEndpoint.setEndpointURI(uriBuilder.getEndpoint());
//		}
//		if(inboundEndpoint != null) {
//			inboundEndpoints.add(inboundEndpoint);
//		}
		
		//outbondUriからoutboundを生成
//		if ( outboundEndpoint == null && outboundEndpointUri != null ) {
//			URIBuilder uriBuilder = new URIBuilder(outboundEndpointUri);
//			outboundEndpoint = new OutboundEndpoint();
//			outboundEndpoint.setEndpointURI(uriBuilder.getEndpoint());
//		}
		
//		isInitalize = true;
//	}
	
	/**
	 * InboundEndpointを追加する
	 */
	public void addInboundEndpointUri(String uri) {
		inboundEndpoints.add(uri);
	}
	
	public boolean isInitalize() {
		return isInitalize;
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
