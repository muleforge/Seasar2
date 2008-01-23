package org.mule.extras.seasar2.sender.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mule.extras.seasar2.client.wrapper.MuleClientWrapper;
import org.mule.extras.seasar2.client.wrapper.impl.MuleClientWrapperImpl;
import org.mule.extras.seasar2.config.ComponentConfig;
import org.mule.extras.seasar2.config.ConnectorConfig;
import org.mule.extras.seasar2.exception.SMuleConfigurationException;
import org.mule.extras.seasar2.exception.SMuleRuntimeException;
import org.mule.extras.seasar2.sender.S2MuleSender;
import org.mule.umo.UMOException;
import org.mule.umo.UMOMessage;
import org.mule.umo.provider.UMOConnector;
import org.mule.umo.transformer.UMOTransformer;

/**
 * {@link S2MuleSender}�̎����N���X�ł��B
 * @author Saito_Shinya@ogis-ri.co.jp
 */
public class S2MuleSenderImpl implements S2MuleSender {
	
	/** Connector�̍\�����*/
	private ComponentConfig connectorConfig;
	
	/** Transformer */
	private List transformers;
	
	/** ���M���Endpoint URI*/
	private String outboundUri;
	
	/** ���M��Endpoint�̃v���p�e�B */
	private Map properties = new HashMap();
	
	/** MuleClient*/
	private MuleClientWrapper muleClient;
	
	/**
	 * �C���X�^���X�𐶐����܂�
	 */
	public S2MuleSenderImpl() {
	}
	
	/**
	 * ���M�̏������s���܂�
	 */
	public void init() {
		try {
			muleClient = new MuleClientWrapperImpl();
			if (connectorConfig!=null) {
				//Connector�̐ݒ�
				muleClient.registerConnector((UMOConnector)connectorConfig.builtComponent());
			}
			if(transformers!=null) {
				//Transformer�̐ݒ�
				setProperty("transformer", transformers.get(0));
			}
		} catch (UMOException e) {
			// TODO exception���� 2007/12/11
			throw new SMuleRuntimeException("ESML0000",new Object[]{e},e);
		}
	}
	
	/**
	 * @see org.mule.extras.seasar2.sender.S2MuleSender#dispatch(Object)
	 */
	public void dispatch(Object payload) {
		//TODO muleClient��dispatch or send
		if(outboundUri != null) {
			//TODO properties��MULE_REMOTE_SYNC=false���Z�b�g����R�[�h���K�v?
			setProperty("MULE_REMOTE_SYNC", false);
			muleClient.send(outboundUri, payload,properties);
		} else {
			throw new SMuleConfigurationException("ESML0002",new Object[]{"outboundUri"});
		}
	}
	
	/**
	 * @see org.mule.extras.seasar2.sender.S2MuleSender#send(Object)
	 */
	public Object send(Object payload) {
		Object responseMessage = null;
		if(outboundUri != null) {
			UMOMessage umoResponseMessage 
				= muleClient.send(outboundUri,payload,properties);
			responseMessage = umoResponseMessage.getPayload();
			return responseMessage;
		} else {
			throw new SMuleConfigurationException("ESML0002",new Object[]{"outboundUri"});
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
	public void addTransformer(UMOTransformer newTransformer) {
		if(transformers == null) {
			transformers = new ArrayList();
			transformers.add(newTransformer);
		} else {
			int index = transformers.size()-1;
			UMOTransformer currentTransformer 
				= (UMOTransformer)transformers.get(index);
			currentTransformer.setNextTransformer(newTransformer);
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
}
