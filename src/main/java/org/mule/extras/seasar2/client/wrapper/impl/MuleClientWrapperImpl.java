package org.mule.extras.seasar2.client.wrapper.impl;

import java.util.Map;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mule.RegistryContext;
import org.mule.config.MuleProperties;
import org.mule.config.i18n.CoreMessages;
import org.mule.extras.client.MuleClient;
import org.mule.extras.seasar2.client.wrapper.MuleClientWrapper;
import org.mule.extras.seasar2.exception.SMuleRuntimeException;
import org.mule.impl.MuleEvent;
import org.mule.impl.MuleSession;
import org.mule.impl.registry.TransientRegistry;
import org.mule.impl.security.MuleCredentials;
import org.mule.providers.AbstractConnector;
import org.mule.umo.UMOEvent;
import org.mule.umo.UMOException;
import org.mule.umo.UMOManagementContext;
import org.mule.umo.UMOMessage;
import org.mule.umo.endpoint.UMOEndpoint;
import org.mule.umo.provider.DispatchException;
import org.mule.umo.provider.UMOConnector;
import org.mule.umo.registry.RegistryFacade;
import org.mule.umo.transformer.UMOTransformer;
import org.seasar.framework.aop.Aspect;

/**
 * {@link MuleClientWrapper}の実装クラスです。
 * 
 * @author Saito_Shinya@ogis-ri.co.jp
 */
public class MuleClientWrapperImpl extends MuleClient implements
		MuleClientWrapper {

	/**  マネジメントコンテキスト */
	private UMOManagementContext managementContext;

	/** loggerの設定 */
	private static Log logger = LogFactory.getLog(MuleClientWrapperImpl.class);
	
	private MuleCredentials user;

	/** Ednpoint */
	private UMOEndpoint endpoint;
	
	/** コンストラクタ */
	public MuleClientWrapperImpl() throws UMOException {
		init(true);
	}

	/**
	 * MuleClient#init(boolean)をコピーしています。
	 * 
	 *  Initialises a default MuleManager for use
	 * by the client.
	 * 
	 * @param startManager
	 *            start the Mule Manager if it has not yet been initialised
	 * @throws UMOException
	 */
	private void init(boolean startManager) throws UMOException {
		// if we are creating a server for this client then set client mode
		// this will disable Admin connections by default;
		// If there is no local managementContext present create a default
		// managementContext
		if (managementContext != null) {
			if (logger.isInfoEnabled()) {
				logger.info("There is already a managementContext locally available to this client, no need to create a new one");
			}
		} else if (RegistryContext.getRegistry() != null) {
			managementContext = RegistryContext.getRegistry().getManagementContext();
		} else {
			if (logger.isInfoEnabled()) {
				logger.info("There is no managementContext instance locally available for this client, creating a new Manager");
			}
			RegistryFacade registry = TransientRegistry.createNew();
			managementContext = registry.getManagementContext();
		}

		//asyncExecutor = managementContext.getWorkManager();

		if (!managementContext.isInitialised() && startManager == true) {
			if (logger.isInfoEnabled()){
				logger.info("Starting Mule Manager for this client");
			}
			managementContext.start();
		}
	}
	
	/**
	 * @see org.mule.extras.seasar2.client.wrapper#dispatch(String, Object)
	 */
	public void dispatch(String url, Object payload) {
		dispatch(url, payload, null);
	}
	
	/**
	 * @see org.mule.extras.seasar2.client.wrapper#dispatch(String, Object, Map)
	 */
	public void dispatch(String url, Object payload, Map messageProperties) {
		try {
			super.dispatch(url, payload, messageProperties);
		} catch ( UMOException e ){
			throw new SMuleRuntimeException("ESML0000",new Object[]{e},e);
		} catch( Exception e ){
			throw new SMuleRuntimeException("ESML0000",new Object[]{e},e);
		}
	}
	
	/**
	 * @see org.mule.extras.seasar2.client.wrapper#send(String, Object)
	 */
	public UMOMessage send(String url, Object payload) {
		return send(url, payload, null);
	}
	
	/**
	 * @see org.mule.extras.seasar2.client.wrapper#send(String, Object, Map)
	 */
	public UMOMessage send(String url, Object payload, Map messageProperties) {
		try {
			return super.send(url, payload, messageProperties);
		} catch ( UMOException e ) {
			throw new SMuleRuntimeException("ESML0000",new Object[]{e},e);
		} catch ( Exception e ){
			throw new SMuleRuntimeException("ESML0000",new Object[]{e},e);
		} 
	}
	
	
	/**
	 * Muleイベントを構築する
	 * @Override
	 */
	public UMOEvent getEvent(UMOMessage message, String uri,
			boolean synchronous, boolean streaming) throws UMOException {
		if(this.endpoint == null) {
			//UMOEndpoint endpoint = getEndpoint(uri,UMOEndpoint.ENDPOINT_TYPE_SENDER);
			endpoint = getEndpoint(uri,UMOEndpoint.ENDPOINT_TYPE_SENDER);
			//set transfomer
			if (message.getProperty("transformer") != null) {
				UMOTransformer userTransformer = (UMOTransformer) message.getProperty("transformer");
				if(endpoint.getTransformer() != null) {
					UMOTransformer nextTransformer = userTransformer;
					while(nextTransformer.getNextTransformer() != null){
						nextTransformer = nextTransformer.getNextTransformer();
					}
					nextTransformer.setNextTransformer(endpoint.getTransformer());
				}
				endpoint.setTransformer(userTransformer);
			}
		}
		if (!endpoint.getConnector().isStarted() && managementContext.isStarted()) {
			endpoint.getConnector().start();
		}
		
		endpoint.setStreaming(streaming);
		try {
			MuleSession session = new MuleSession(message,
					((AbstractConnector) endpoint.getConnector())
							.getSessionHandler());
			if (user != null) {
				message.setProperty(MuleProperties.MULE_USER_PROPERTY,
						MuleCredentials.createHeader(user.getUsername(), user.getPassword()));
			}
			MuleEvent event = new MuleEvent(message, endpoint, session,synchronous);
			return event;
		} catch (Exception e) {
			throw new DispatchException(CoreMessages.failedToCreate("Client event"), message, endpoint, e);
		}
	}
	
	/**
	 * @see org.mule.extras.seasar2.client.wrapper#registerConnector(UMOConnector)
	 */
	public void registerConnector(UMOConnector connector) throws UMOException {
		getManagementContext().getRegistry().registerConnector(connector);
	}
	
	/**
	 * MuleServerを終了させる。
	 * このメソッドを呼ばないとMuleServerスレッドは残ったままになる。
	 * 登録されていたMule Conponentは全て破棄される。
	 *TODO MuleClientWrapper#destroy
	 */
	public void destroy() throws Exception {		
//		System.out.println("★stop mule");
		managementContext.getNotificationManager().dispose();
//		managementContext.getQueueManager().stop();
		managementContext.getWorkManager().dispose();
//		managementContext.getLifecycleManager().reset();;
//		managementContext.getRegistry().dispose();
//		managementContext.stop();
		managementContext.dispose();
//		System.exit(0);
	}
}
