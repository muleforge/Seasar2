package org.mule.extras.seasar2.receiver.builder.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.mule.MuleServer;
import org.mule.api.MuleContext;
import org.mule.api.MuleException;
import org.mule.endpoint.InboundEndpoint;
import org.mule.extras.seasar2.receiver.builder.S2MuleComponentBuilder;
import org.mule.extras.seasar2.receiver.object.S2MuleConfiguration;
import org.mule.extras.seasar2.receiver.object.S2MuleObjectFactory;
import org.mule.api.service.Service;
import org.mule.api.routing.InboundRouterCollection;
import org.mule.model.seda.SedaService;
import org.mule.routing.inbound.DefaultInboundRouterCollection;
import org.mule.util.object.ObjectFactory;
import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.S2Container;



/**
 * {@link S2MuleComponentBuilder}�̎����N���X
 * 
 * @author Saito_Shinya@ogis-ri.co.jp
 *
 */
public class S2MuleComponentBuilderImpl implements S2MuleComponentBuilder {
	
	/**
	 * �f�t�H���g��modleName
	 * Mule-config��<modle name="">�^�O�ɑΉ�
	 */
	private final String DEFAULT_MODEL_NAME = "default-qbuilder";
	
	/**
	 * �f�t�H���g��descriptorName
	 * Mule-config��<mule-descriptor name="">�^�O�ɑΉ�
	 */
	private final String DEFAULT_DESCRIPTOR_NAME = "S2MuleUMO";
	
	/**
	 * �f�t�H���g��UMOName
	 */
	private final String DEFAULT_UMO_NAME 
		= "org.mule.components.simple.BridgeComponent";
	
	/**
	 * �I�[�g�o�C���f�B���O�ɂ����S2Container���ݒ肳���
	 */
	private S2Container container;
	
	/**
	 * TODO �����I�ɂ͕�����s2MuleConfig��������悤�ɂ������B
	 * dicon�ɋL�q����Ă���Mule�C���X�^���X�̏��
	 * �b��I
	 */
	private List s2MuleConfigs;
	
	/**
	 * Mule��quickConfigurationBuilder�𗘗p
	 */
	//private QuickConfigurationBuilder qcBuilder;
	private MuleContext context;
	
	/**
	 * dicon�t�@�C���ɋL�q����Ă���S�ẴR���|�[�l���g
	 */
	private List allDiconComponentDefs = new ArrayList();
	
	/**
	 * 
	 * @throws MuleException
	 */
	public S2MuleComponentBuilderImpl() throws MuleException {
		//QuickConfigurationBuilder�̍쐬
		//qcBuilder = new QuickConfigurationBuilder(false);
		
		//MuleContext�̍쐬
		context = MuleServer.getMuleContext();
	}

	/**
	 * MuleConfigurationFile���w�肵���ꍇ�AMule-config����Mule���N������
	 * @param muleConfigPass
	 */
	public S2MuleComponentBuilderImpl( String muleConfigPass) {
		
	}
	
	/**
	 * 
	 * @param smDeciptor
	 */
	public S2MuleComponentBuilderImpl( S2MuleConfiguration smDecriptor) {
		
	}
	
	/**
	 * Service��registry�ɓo�^����B
	 * TODO Connector��registry�ɓo�^����B
	 * 
	 * @return managementContext
	 * 
	 */
	public MuleContext configure()  throws MuleException {
		
		s2MuleConfigs = getS2MuleConfigs(container);
		
		if ( s2MuleConfigs != null ){
			for( int i = 0; i < s2MuleConfigs.size(); i++) {
				S2MuleConfiguration s2MuleConfig = 
					(S2MuleConfiguration) s2MuleConfigs.get(i);
				Service service = createService( s2MuleConfig );
				
				//Service��o�^����
				context.getRegistry().registerService(service);
			}
		} else {
			//TODO ��O����
		}
		// test configure�̒���muleServer���X�^�[�g�����Ă��܂�
		context.start();
		
		return context;
	}
	
	/**
	 * MuleDescriptor���쐬����
	 * @return
	 */
	private Service createService(S2MuleConfiguration s2MuleConfig) throws MuleException {
		
		if(!s2MuleConfig.isInitalize()){
			s2MuleConfig.initialize();
		}
		
		//Mule��Default�ł���SedaService���쐬
		Service service = new SedaService();
		
		//InboundRouterCollection�̍쐬
		InboundRouterCollection iRouterCollection = new DefaultInboundRouterCollection();
		
		//InboundEndpoits�̐������s��
		List inboundEndpoints = s2MuleConfig.getInboundEndpoints();
		for(int i =0;i<inboundEndpoints.size();i++) {
			InboundEndpoint endpoint = (InboundEndpoint)inboundEndpoints.get(i);
			iRouterCollection.addEndpoint(endpoint);
		}
		service.setInboundRouter(iRouterCollection);
		
//		//outboundRouterCollection�̍쐬
//		OutboundRouterCollection oRouterCollection = new OutboundRouterCollection();
//		//TODO dicon�Ŏw�肳�ꂽRouter��������悤�ɂ������B
//		//�f�t�H���g��PassThroughRouter
//		OutboundPassThroughRouter oPassThroughRouter = new OutboundPassThroughRouter();
//		MuleEndpoint outbound = (MuleEndpoint)s2MuleConfig.getOutboundEndpoint();
//		outbound.setManagementContext(qcBuilder.getManagementContext());
//		
//		oPassThroughRouter.addEndpoint(outbound);
//		oRouterCollection.addRouter(oPassThroughRouter);
//		
//		descriptor.setOutboundRouter(oRouterCollection);
		
//		// modelName�̍쐬
//		String modelName = DEFAULT_MODEL_NAME;
//		descriptor.setModelName(modelName);
		
		//descriptorName�̍쐬
//		String descriptorName;
//		if (s2MuleConfig.getName() != null) {
//			descriptorName = s2MuleConfig.getName();
//		} else {
//			descriptorName = DEFAULT_DESCRIPTOR_NAME;
//		}
//		descriptor.setName(descriptorName);
		
		//ObjectFactory�̍쐬
		ObjectFactory factory = null;
		//umo��dicon�ɐݒ肳��Ă����ꍇ�AS2MuleSimpleObjectFactory��ݒ肷��B
		if( s2MuleConfig.getUmoImpl() != null ) {
			factory = new S2MuleObjectFactory(container,s2MuleConfig.getUmoImpl()); 
		} else {
			//umo��dicon�ɐݒ肳��Ă��Ȃ��ꍇ�ABridgeComponent��ݒ肷��B
			factory = new S2MuleObjectFactory(container,s2MuleConfig.getUmoImpl());
		}
		service.setServiceFactory(factory);
		
		return service;
	}
	
	/**
	 * S2cotainer����S2MuleConfigration��T�����A���X�g�Ɋi�[����
	 * @param container
	 * @return
	 */
	private List getS2MuleConfigs( S2Container container ) {
		List configs = new ArrayList();
		createAllDiconComponents(container, null);
		for( int i = 0; i < allDiconComponentDefs.size(); i++ ) {
			ComponentDef cd = (ComponentDef)allDiconComponentDefs.get(i);
			
			//S2MuleConfiguration�N���X�̏ꍇ�A���X�g�ɒǉ�����
			if ( cd.getComponentClass().equals(S2MuleConfiguration.class) ) {
				S2MuleConfiguration s2mConfig 
					= (S2MuleConfiguration)cd.getComponent();
				//S2MuleConfig�̖��O�̓o�^
				s2mConfig.setName(cd.getComponentName());
				
				configs.add(s2mConfig);
			}
		}
		return configs;
	}
	
	
	/**
	 * dicon�t�@�C���ɋL�q����Ă���S�ẴR���|�[�l���g���擾����
	 * �ċA�𗘗p���A�S�Ă�dicon�t�@�C������������
	 * 
	 * @param container S2Container
	 * @param set S2Container���i�[������Ɨp
	 * @return
	 */
	private void createAllDiconComponents(S2Container container, Set set) {
		
		if (set == null){
			set = new HashSet();
		}
		 //���݂�S2�R���e�i�Ƃ��Ȃ�S2�R���e�i��set�ɓo�^����Ă���ꍇ
		//�ċA���I��������
	     if (set.contains(container)) {
	    	 return;
	     }
	     set.add(container);

	     // �q�R���e�i�ւ�����
	     for (int i = 0; i <  container.getChildSize(); i++) {
	    	 createAllDiconComponents(container.getChild(i), set);
	     }
	     
	     for(int i = 0;i < container.getComponentDefSize();i ++) {
	    	 allDiconComponentDefs.add(container.getComponentDef(i));
	     }
	}
	
	/**
	 * MuleServer���~������
	 * Registry�ɓo�^����Ă����R���|�[�l���g�͑S�Ĕj�������
	 *
	 */
	public void destroy() {
		
	}
	
	public void setContainer(S2Container container) {
		this.container = container.getRoot();
	}

}
