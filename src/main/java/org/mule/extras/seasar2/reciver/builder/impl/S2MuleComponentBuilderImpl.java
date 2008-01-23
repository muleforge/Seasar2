package org.mule.extras.seasar2.reciver.builder.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.mule.config.builders.QuickConfigurationBuilder;
import org.mule.extras.seasar2.reciver.builder.S2MuleComponentBuilder;
import org.mule.extras.seasar2.reciver.object.S2MuleConfiguration;
import org.mule.extras.seasar2.reciver.object.S2MuleSimpleObjectFactory;
import org.mule.impl.MuleDescriptor;
import org.mule.impl.endpoint.MuleEndpoint;
import org.mule.routing.inbound.InboundRouterCollection;
import org.mule.routing.outbound.OutboundPassThroughRouter;
import org.mule.routing.outbound.OutboundRouterCollection;
import org.mule.umo.UMODescriptor;
import org.mule.umo.UMOException;
import org.mule.umo.UMOManagementContext;
import org.mule.umo.endpoint.UMOEndpoint;
import org.mule.util.object.ObjectFactory;
import org.mule.util.object.SimpleObjectFactory;
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
	private QuickConfigurationBuilder qcBuilder;
	
	/**
	 * dicon�t�@�C���ɋL�q����Ă���S�ẴR���|�[�l���g
	 */
	private List allDiconComponentDefs = new ArrayList();
	
	/**
	 * 
	 * @throws UMOException
	 */
	public S2MuleComponentBuilderImpl() throws UMOException {
		//QuickConfigurationBuilder�̍쐬
		qcBuilder = new QuickConfigurationBuilder(false);
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
	 * MuleDescriptor��registry�ɓo�^����B
	 * TODO Connector��registry�ɓo�^����B
	 * 
	 * @return managementContext
	 * 
	 */
	public UMOManagementContext configure()  throws UMOException {
		
		s2MuleConfigs = getS2MuleConfigs(container);
		
		if ( s2MuleConfigs != null ){
			for( int i = 0; i < s2MuleConfigs.size(); i++) {
				S2MuleConfiguration s2MuleConfig = 
					(S2MuleConfiguration) s2MuleConfigs.get(i);
				UMODescriptor descriptor = createDescriptor( s2MuleConfig );
				//MuleDescriptor��o�^����
				qcBuilder.registerComponent(descriptor);
			}
		} else {
			//TODO ��O�𓊂���
		}
		// test configure�̒���muleServer���X�^�[�g�����Ă��܂�
		qcBuilder.getManagementContext().start();
		
		
		return qcBuilder.getManagementContext();
	}
	
	/**
	 * MuleDescriptor���쐬����
	 * @return
	 */
	private UMODescriptor createDescriptor(S2MuleConfiguration s2MuleConfig) throws UMOException {
		
		if(!s2MuleConfig.isInitalize()){
			s2MuleConfig.initialize();
		}
		
		UMODescriptor descriptor = new MuleDescriptor();
		
		//InboundRouterCollection�̍쐬
		InboundRouterCollection iRouterCollection = new InboundRouterCollection();
		
		//InboundEndpoits�̐������s��
		List inboundEndpoints = s2MuleConfig.getInboundEndpoints();
		for(int i =0;i<inboundEndpoints.size();i++) {
			UMOEndpoint endpoint = (UMOEndpoint)inboundEndpoints.get(i);
			iRouterCollection.addEndpoint(endpoint);
		}
		descriptor.setInboundRouter(iRouterCollection);
		
		//outboundRouterCollection�̍쐬
		OutboundRouterCollection oRouterCollection = new OutboundRouterCollection();
		//TODO dicon�Ŏw�肳�ꂽRouter��������悤�ɂ������B
		//�f�t�H���g��PassThroughRouter
		OutboundPassThroughRouter oPassThroughRouter = new OutboundPassThroughRouter();
		MuleEndpoint outbound = (MuleEndpoint)s2MuleConfig.getOutboundEndpoint();
		outbound.setManagementContext(qcBuilder.getManagementContext());
		
		oPassThroughRouter.addEndpoint(outbound);
		oRouterCollection.addRouter(oPassThroughRouter);
		
		descriptor.setOutboundRouter(oRouterCollection);
		
		// modelName�̍쐬
		String modelName = DEFAULT_MODEL_NAME;
		descriptor.setModelName(modelName);
		
		//descriptorName�̍쐬
		String descriptorName;
		if (s2MuleConfig.getName() != null) {
			descriptorName = s2MuleConfig.getName();
		} else {
			descriptorName = DEFAULT_DESCRIPTOR_NAME;
		}
		descriptor.setName(descriptorName);
		
		//ObjectFactory�̍쐬
		ObjectFactory factory = null;
		//umo��dicon�ɐݒ肳��Ă����ꍇ�AS2MuleSimpleObjectFactory��ݒ肷��B
		if( s2MuleConfig.getUmoImpl() != null ) {
			factory = new S2MuleSimpleObjectFactory(s2MuleConfig.getUmoImpl()); 
		} else {
			//umo��dicon�ɐݒ肳��Ă��Ȃ��ꍇ�ABridgeComponent��ݒ肷��B
			factory = new SimpleObjectFactory(DEFAULT_UMO_NAME);
		}
		descriptor.setServiceFactory(factory);
		
		return descriptor;
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
		qcBuilder.getManagementContext().getNotificationManager().dispose();
		qcBuilder.getManagementContext().getWorkManager().dispose();
		qcBuilder.getManagementContext().getRegistry().dispose();
		qcBuilder.getManagementContext().dispose();
	}
	
	public void setContainer(S2Container container) {
		this.container = container.getRoot();
	}

}
