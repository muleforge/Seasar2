package org.mule.extras.seasar2.sender.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.TransactionManager;

import org.mule.endpoint.EndpointURIEndpointBuilder;
import org.mule.endpoint.InboundEndpoint;
import org.mule.endpoint.OutboundEndpoint;
import org.mule.endpoint.URIBuilder;
import org.mule.extras.client.MuleClient;
import org.mule.extras.seasar2.config.ComponentConfig;
import org.mule.extras.seasar2.config.TransactionConnector;
import org.mule.extras.seasar2.exception.S2MuleConfigurationException;
import org.mule.extras.seasar2.exception.S2MuleRuntimeException;
import org.mule.extras.seasar2.receiver.impl.S2MuleConfiguration;
import org.mule.extras.seasar2.receiver.object.S2MuleObjectFactory;
import org.mule.extras.seasar2.sender.S2MuleSender;
import org.mule.model.seda.SedaModel;
import org.mule.model.seda.SedaService;
import org.mule.routing.inbound.DefaultInboundRouterCollection;
import org.mule.routing.outbound.DefaultOutboundRouterCollection;
import org.mule.routing.outbound.OutboundPassThroughRouter;
import org.mule.transaction.MuleTransactionConfig;
import org.mule.transaction.TransactionCoordination;
import org.mule.transaction.XaTransaction;
import org.mule.transaction.XaTransactionFactory;
import org.mule.transformer.AbstractTransformer;
import org.mule.transport.vm.VMConnector;
import org.mule.util.object.ObjectFactory;
import org.mule.util.object.SingletonObjectFactory;

import org.mule.api.MuleContext;
import org.mule.api.MuleException;
import org.mule.api.MuleMessage;
import org.mule.api.config.MuleProperties;
import org.mule.api.endpoint.EndpointBuilder;
import org.mule.api.model.Model;
import org.mule.api.routing.InboundRouterCollection;
import org.mule.api.routing.OutboundRouter;
import org.mule.api.routing.OutboundRouterCollection;
import org.mule.api.service.Service;
import org.mule.api.transport.Connector;
import org.mule.extras.seasar2.config.impl.TransactionConfig;
import org.mule.api.transformer.Transformer;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.exception.SRuntimeException;

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
	private TransactionConfig transactionConfig;
	
	/** �g�����U�N�V�����}�l�[�W��*/
	private TransactionManager transactionManager;
	
	/** MuleClient*/
	private MuleClient muleClient;
	
	/** �f�t�H���g��InboundEndpointURI*/
	private final String DEFAULT_INBUNDENDPOINT_URI
		= "vm://s2mule-sender";
	
	/** �Œ�̃T�[�r�X�� */
	public static final String SERVICE_NAME = "bridge";
	
	private S2Container container;

	/**
	 * �C���X�^���X�𐶐����܂�
	 */
	public S2MuleSenderImpl(final ComponentConfig connectorConfig
			/*,final TransactionManager transactionManager*/) {
		this.connectorConfig = connectorConfig;
		//this.transactionManager = transactionManager;
	}
	
	/**
	 * ���M�̏������s���܂�
	 */
//	public void init() {
//		try {
//			muleClient = new MuleClient(false);
//			EndpointURIEndpointBuilder endpointBuilder 
//				= new EndpointURIEndpointBuilder(new URIBuilder(outboundUri),muleClient.getMuleContext());
//			
//			if (connectorConfig != null) {
//				// Connector �̐ݒ�
//				muleClient.getMuleContext().getRegistry().registerConnector((Connector)connectorConfig.buildComponent());
//				
//				//TODO �g�����U�N�V�����ݒ�
//				if(deliveryTransacted) {
//					if ((connectorConfig instanceof TransactionConnector)) {
//						muleClient.getMuleContext().setTransactionManager(transactionManager);
//						TransactionConfig transactionConfig = new MuleTransactionConfig();
//						transactionConfig.setFactory(new XaTransactionFactory());
//						//�e�X�g�R�[�h
//						transactionConfig.setAction(TransactionConfig.ACTION_ALWAYS_BEGIN);
//						endpointBuilder.setTransactionConfig(transactionConfig);
//					} else {
//						//TODO �G���[�R�[�h
//						throw new S2MuleConfigurationException("");
//					}
//				}
//			}
//			if(transformers != null) {
//				// Transformer �̐ݒ�
//				endpointBuilder.setTransformers(transformers);
//			}
//			muleClient.getMuleContext().getRegistry().registerEndpointBuilder(outboundUri, endpointBuilder);
//		//TODO ���� Exception�ł悢�̂�
//		} catch (Exception e) {
//			throw new S2MuleRuntimeException("ESML0000", new Object[]{e},e);
//		}
//	}
	
	public void init() {
		try {
			transactionManager = (TransactionManager)container.getComponent(TransactionManager.class);
			muleClient = (MuleClient)container.getComponent(MuleClient.class);
			muleClient.getMuleContext().getRegistry().
				registerService(createService(muleClient.getMuleContext()));
			//muleClient.getMuleContext().start();
		} catch(SRuntimeException e) {
			throw e;
		} catch(Exception e) {
			throw new S2MuleRuntimeException("ESML0000", new Object[]{e},e);
		}
	}
	
	/**
	 * Service���쐬����
	 * @return
	 */
	private Service createService(MuleContext context) throws Exception {
		
		//Mule��Default�ł���SedaService���쐬
		Service service = new SedaService();
		
		Model model = new SedaModel();
		model.initialise();
		service.setModel(model);
		
		//Connector �̐ݒ�
		//TODO dicon�ɋL�q
		VMConnector vmConnector = new VMConnector();
		vmConnector.setQueueEvents(true);
		muleClient.getMuleContext().getRegistry().registerConnector(vmConnector);
		
		//InboundEndpoint�̍쐬
		InboundRouterCollection iRouterCollection = new DefaultInboundRouterCollection();
		URIBuilder iUriBuilder= new URIBuilder(DEFAULT_INBUNDENDPOINT_URI);
		EndpointBuilder iEndpointBuilder = new EndpointURIEndpointBuilder(iUriBuilder,context);
		InboundEndpoint inboundEndpoint = (InboundEndpoint)iEndpointBuilder.buildInboundEndpoint();
		if(transactionConfig != null) {
			if ((connectorConfig instanceof TransactionConnector)) {
				//�g�����U�N�V�����}�l�[�W���[�̐ݒ�
				muleClient.getMuleContext().setTransactionManager(transactionManager);
				
				org.mule.api.transaction.TransactionConfig transactionConfig = new MuleTransactionConfig();
				transactionConfig.setFactory(new XaTransactionFactory());
				transactionConfig.setAction(this.transactionConfig.getAction());
				inboundEndpoint.setTransactionConfig(transactionConfig);
			} else {
				//TODO �G���[�R�[�h
				throw new S2MuleConfigurationException("");
			}
		}
		iRouterCollection.addEndpoint(inboundEndpoint);
		service.setInboundRouter(iRouterCollection);
		
		//ServiceName�̍쐬
		service.setName(SERVICE_NAME);
		
		//Connector �̐ݒ�
		muleClient.getMuleContext().getRegistry().registerConnector((Connector)connectorConfig.buildComponent());
		
		//OutboundEndpooint�̍쐬
		OutboundRouterCollection oRouterCollection = new DefaultOutboundRouterCollection();
		OutboundRouter router = new OutboundPassThroughRouter();
		
		URIBuilder oUriBuilder = new URIBuilder(outboundUri);
		EndpointBuilder oEndpointBuilder = new EndpointURIEndpointBuilder(oUriBuilder,context);
		OutboundEndpoint outboundEndpoint = (OutboundEndpoint)oEndpointBuilder.buildOutboundEndpoint();
		router.addEndpoint(outboundEndpoint);
		oRouterCollection.addRouter(router);
		muleClient.getMuleContext().getRegistry().registerObject("oRouterCollection",oRouterCollection,"META");
		service.setOutboundRouter(oRouterCollection);
		
		
		return service;
	}
	
	/**
	 * @see org.mule.extras.seasar2.sender.S2MuleSender#dispatch(Object)
	 */
	public void dispatch(Object payload) {
		if(outboundUri != null) {
			try {
				if(transactionConfig!=null && 
						transactionManager.getTransaction()!=null) {
					//test
					System.out.println("\n\n Transaction:" + transactionManager.getTransaction() + "\n\n");
					XaTransaction xat = new XaTransaction(transactionManager);
					System.out.println("\n\n Transaction:" + xat.isBegun() + "\n\n");
					TransactionCoordination.getInstance().bindTransaction(xat);
				}
				//muleClient.dispatch(outboundUri, payload, properties);
				//muleClient.dispatchDirect(SERVICE_NAME, payload,properties);
				muleClient.sendNoReceive(outboundUri, payload, properties);
				//Thread.sleep(2000);
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

	public TransactionConfig getTransactionConfig() {
		return transactionConfig;
	}

	public void setTransactionConfig(TransactionConfig transactionConfig) {
		this.transactionConfig = transactionConfig;
	}
	
	public S2Container getContainer() {
		return container;
	}

	public void setContainer(S2Container container) {
		this.container = container;
	}
	
}
