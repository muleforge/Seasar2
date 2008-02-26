package org.mule.extras.seasar2.sender.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.TransactionManager;

import org.mule.endpoint.EndpointURIEndpointBuilder;
import org.mule.endpoint.URIBuilder;
import org.mule.extras.client.MuleClient;
import org.mule.extras.seasar2.config.ComponentConfig;
import org.mule.extras.seasar2.config.TransactionConnector;
import org.mule.extras.seasar2.exception.S2MuleConfigurationException;
import org.mule.extras.seasar2.exception.S2MuleRuntimeException;
import org.mule.extras.seasar2.sender.S2MuleSender;
import org.mule.transformer.AbstractTransformer;

import org.mule.api.MuleException;
import org.mule.api.MuleMessage;
import org.mule.api.config.MuleProperties;
import org.mule.api.endpoint.EndpointBuilder;
import org.mule.api.transport.Connector;
import org.mule.api.transformer.Transformer;

/**
 * {@link S2MuleSender} の実装クラスです。
 * @author Saito_Shinya@ogis-ri.co.jp
 */
public class S2MuleSenderImpl implements S2MuleSender {
	
	/** Connector の構成情報*/
	private ComponentConfig connectorConfig;
	
	/** Transformer */
	private List transformers;
	
	/** 送信先の Endpoint URI*/
	private String outboundUri;
	
	/** 送信先 Endpointのプロパティ */
	private Map properties = new HashMap();
	
	/** トランザクション*/
	private boolean deliveryTransacted = false;
	
	private TransactionManager transactionManager;
	
	/** MuleClient*/
	private MuleClient muleClient;
	
	/**
	 * インスタンスを生成します
	 */
	public S2MuleSenderImpl(final ComponentConfig connectorConfig,
			final TransactionManager transactionManager) {
		this.connectorConfig = connectorConfig;
		this.transactionManager = transactionManager;
	}
	
	/**
	 * 送信の準備を行います
	 */
	public void init() {
		try {
			muleClient = new MuleClient(true);
			EndpointURIEndpointBuilder endpointBuilder 
				= new EndpointURIEndpointBuilder(new URIBuilder(outboundUri),muleClient.getMuleContext());
			
			if (connectorConfig != null) {
				// Connector の設定
				muleClient.getMuleContext().getRegistry().registerConnector((Connector)connectorConfig.buildComponent());
			}
			if(transformers != null) {
				// Transformer の設定
				//setProperty("transformer", transformers.get(0));
				endpointBuilder.setTransformers(transformers);
			}
			if(deliveryTransacted) {
				if (connectorConfig != null && 
						(connectorConfig instanceof TransactionConnector)) {
					//TODO エラーコード
					throw new S2MuleConfigurationException("");
				} else {
					//TODO トランザクション設定
				}
			}
//			muleClient.getMuleContext().getRegistry().registerEndpointBuilder(outboundUri, endpointBuilder);
		} catch (MuleException e) {
			throw new S2MuleRuntimeException("ESML0000", new Object[]{e},e);
		}
	}
	
	/**
	 * @see org.mule.extras.seasar2.sender.S2MuleSender#dispatch(Object)
	 */
	public void dispatch(Object payload) {
		if(outboundUri != null) {
			try {
				muleClient.dispatch(outboundUri, payload, properties);
			} catch ( MuleException e ) {
				throw new S2MuleRuntimeException("ESML0000", new Object[]{e}, e);
			} catch ( Exception e ){
				throw new S2MuleRuntimeException("ESML0000", new Object[]{e}, e);
			}
		} else {
			throw new S2MuleConfigurationException("ESML0002", new Object[]{"outboundUri"});
		}
	}
	
	/**
	 * @see org.mule.extras.seasar2.sender.S2MuleSender#send(Object)
	 */
	public Object send(Object payload) {
		Object responseMessage = null;
		if(outboundUri != null) {
			try {
				MuleMessage umoResponseMessage 
					= muleClient.send(outboundUri, payload, properties);
				responseMessage = umoResponseMessage.getPayload();
			} catch ( MuleException e ) {
				throw new S2MuleRuntimeException("ESML0000", new Object[]{e}, e);
			} catch ( Exception e ){
				throw new S2MuleRuntimeException("ESML0000", new Object[]{e}, e);
			}
			return responseMessage;
		} else {
			throw new S2MuleConfigurationException("ESML0002", new Object[]{"outboundUri"});
		}
	}
	
	/**
	 * プロパティを追加する
	 */
	public void setProperty(String key, Object value) {
		properties.put(key, value);
	}
	
	/**
	 * トランスフォーマを追加する
	 * 
	 * @param newTransformer
	 */
	public void addTransformer(Transformer newTransformer) {
		if(transformers == null) {
			transformers = new ArrayList();
			transformers.add(newTransformer);
		} else {
			int index = transformers.size()-1;
			AbstractTransformer currentTransformer 
				= (AbstractTransformer)transformers.get(index);
			//currentTransformer.(newTransformer);
			transformers.add(newTransformer);
		}
	}
	
	public void setConnectorConfig(ComponentConfig connectorConfig) {
		this.connectorConfig = connectorConfig;
	}

	public String getOutboundUri() {
		return outboundUri;
	}

	public void setOutboundUri(String outboundUri) {
		this.outboundUri = outboundUri;
	}

	public boolean isDeliveryTransacted() {
		return deliveryTransacted;
	}

	public void setDeliveryTransacted(boolean deliveryTransacted) {
		this.deliveryTransacted = deliveryTransacted;
	}
}
