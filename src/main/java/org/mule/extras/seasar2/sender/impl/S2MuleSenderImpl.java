package org.mule.extras.seasar2.sender.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.TransactionManager;

import org.mule.endpoint.EndpointURIEndpointBuilder;
import org.mule.endpoint.DefaultOutboundEndpoint;
import org.mule.endpoint.URIBuilder;
import org.mule.module.client.MuleClient;
import org.mule.extras.seasar2.config.ComponentConfig;
import org.mule.extras.seasar2.config.TransactionConnector;
import org.mule.extras.seasar2.exception.S2MuleConfigurationException;
import org.mule.extras.seasar2.exception.S2MuleRuntimeException;
import org.mule.extras.seasar2.sender.S2MuleSender;
import org.mule.transaction.TransactionCoordination;
import org.mule.transaction.XaTransaction;
import org.mule.transformer.AbstractTransformer;
import org.mule.util.ObjectNameHelper;

import org.mule.api.MuleException;
import org.mule.api.MuleMessage;
import org.mule.api.endpoint.EndpointBuilder;
import org.mule.api.transport.Connector;
import org.mule.extras.seasar2.config.impl.AxisConnectorConfig;
import org.mule.extras.seasar2.config.impl.TransactionConfig;
import org.mule.api.transformer.Transformer;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.impl.S2ContainerImpl;
import org.seasar.framework.exception.SRuntimeException;
import org.seasar.framework.log.Logger;

/**
 * {@link S2MuleSender} の実装クラスです
 * @author Saito_Shinya@ogis-ri.co.jp
 */
public class S2MuleSenderImpl implements S2MuleSender {
	
	 private static final Logger logger = Logger
     	.getLogger(S2MuleSenderImpl.class);
	
	/** Connector の構成情報 */
	private ComponentConfig connectorConfig;
	
	/** Transformer */
	private List transformers;
	
	/** 送信先の Endpoint URI */
	private String outboundUri;
	
	/** 送信先 Endpointのプロパティ */
	private Map properties = new HashMap();
	
	/** トランザクションマネージャ */
	private TransactionManager transactionManager;
	
	/** MuleClient */
	private MuleClient muleClient;
	
	/** S2コンテナ */
	private S2Container container;

	/**
	 * インスタンスを生成します
	 */
	public S2MuleSenderImpl(final ComponentConfig connectorConfig) {
		this.connectorConfig = connectorConfig;
	}
	
	/**
	 * 送信の準備を行います
	 */
	public void init() {
		try {
			
			muleClient = (MuleClient)container.getComponent(MuleClient.class);
			
			EndpointBuilder endpointBuilder = null;
			if(outboundUri!=null) {
				URIBuilder uriBuilder = new URIBuilder(outboundUri);
				endpointBuilder = new EndpointURIEndpointBuilder(uriBuilder,muleClient.getMuleContext());
				muleClient.getMuleContext().getRegistry().registerEndpointBuilder(outboundUri, endpointBuilder);
			} else {
				throw new S2MuleConfigurationException("ESML0002",new Object[]{"outboundUri"});
			}
			
			if(connectorConfig != null) {
				//Connector の設定
				Connector connector = (Connector)connectorConfig.buildComponent();
				connector.setName(ObjectNameHelper.getConnectorName(connector));
				muleClient.getMuleContext().getRegistry().registerConnector(connector);
				endpointBuilder.setConnector(connector);
				if(connectorConfig instanceof AxisConnectorConfig){
					properties.putAll(connectorConfig.getProperties());
				}
				logger.debug("Connectorを作成しました:" + connector);
			}
			
			//Transactionの設定
			if(connectorConfig instanceof TransactionConnector) {
				transactionManager = (TransactionManager)container.getRoot().getComponent(TransactionManager.class);
				muleClient.getMuleContext().setTransactionManager(transactionManager);
			}
			
			if(transformers != null) {
				endpointBuilder.setTransformers(transformers);
			}
		} catch(SRuntimeException e) {
			throw e;
		} catch(Exception e) {
			throw new S2MuleRuntimeException("ESML0000", new Object[]{e},e);
		}
	}
	
	/**
	 * @see org.mule.extras.seasar2.sender.S2MuleSender#dispatch(Object)
	 */
	public void dispatch(Object payload) {
		if(outboundUri != null) {
			try {
				logger.debug("メッセージを" + outboundUri + "へ送信します");
				if(transactionManager!=null && transactionManager.getTransaction()!=null) {
					logger.debug("トランザクション:" + transactionManager.getTransaction() );
					if(TransactionCoordination.getInstance().getTransaction()==null) {
						XaTransaction xat = new XaTransaction(transactionManager);
						TransactionCoordination.getInstance().bindTransaction(xat);
					} 
				}
				muleClient.sendNoReceive(outboundUri, payload, properties);
				logger.debug("メッセージを" + outboundUri + "へ送信しました");
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
	
	public void dispose() {
		muleClient.dispose();
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
	
	public S2Container getContainer() {
		return container;
	}

	public void setContainer(S2Container container) {
		this.container = container;
	}
	
}
