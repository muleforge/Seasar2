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
 * {@link S2MuleSender} �̎����N���X�ł��B
 * @author Saito_Shinya@ogis-ri.co.jp
 */
public class S2MuleSenderImpl implements S2MuleSender {
	
	/** Connector �̍\�����*/
	private ComponentConfig connectorConfig;
	
	/** Transformer */
	private List transformers;
	
	/** ���M��� Endpoint URI*/
	private String outboundUri;
	
	/** ���M�� Endpoint�̃v���p�e�B */
	private Map properties = new HashMap();
	
	/** �g�����U�N�V����*/
	private boolean deliveryTransacted = false;
	
	private TransactionManager transactionManager;
	
	/** MuleClient*/
	private MuleClient muleClient;
	
	/**
	 * �C���X�^���X�𐶐����܂�
	 */
	public S2MuleSenderImpl(final ComponentConfig connectorConfig,
			final TransactionManager transactionManager) {
		this.connectorConfig = connectorConfig;
		this.transactionManager = transactionManager;
	}
	
	/**
	 * ���M�̏������s���܂�
	 */
	public void init() {
		try {
			muleClient = new MuleClient(true);
			EndpointURIEndpointBuilder endpointBuilder 
				= new EndpointURIEndpointBuilder(new URIBuilder(outboundUri),muleClient.getMuleContext());
			
			if (connectorConfig != null) {
				// Connector �̐ݒ�
				muleClient.getMuleContext().getRegistry().registerConnector((Connector)connectorConfig.buildComponent());
			}
			if(transformers != null) {
				// Transformer �̐ݒ�
				//setProperty("transformer", transformers.get(0));
				endpointBuilder.setTransformers(transformers);
			}
			if(deliveryTransacted) {
				if (connectorConfig != null && 
						(connectorConfig instanceof TransactionConnector)) {
					//TODO �G���[�R�[�h
					throw new S2MuleConfigurationException("");
				} else {
					//TODO �g�����U�N�V�����ݒ�
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
	 * �v���p�e�B��ǉ�����
	 */
	public void setProperty(String key, Object value) {
		properties.put(key, value);
	}
	
	/**
	 * �g�����X�t�H�[�}��ǉ�����
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
