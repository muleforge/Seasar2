package org.mule.extras.seasar2.receiver.builder.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.mule.api.MuleContext;
import org.mule.api.MuleException;
import org.mule.endpoint.EndpointURIEndpointBuilder;
import org.mule.endpoint.InboundEndpoint;
import org.mule.endpoint.URIBuilder;
import org.mule.extras.seasar2.receiver.builder.S2MuleComponentBuilder;
import org.mule.extras.seasar2.receiver.impl.S2MuleConfiguration;
import org.mule.extras.seasar2.receiver.object.S2MuleObjectFactory;
import org.mule.api.service.Service;
import org.mule.api.endpoint.EndpointBuilder;
import org.mule.api.model.Model;
import org.mule.api.routing.InboundRouterCollection;
import org.mule.context.DefaultMuleContextFactory;
import org.mule.model.seda.SedaModel;
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
	 * �f�t�H���g��ServiceName
	 * Mule-config��<service name="">�^�O�ɑΉ�
	 */
	private final String DEFAULT_SERVICE_NAME = "S2MuleUMO";
	
	/**
	 * �I�[�g�o�C���f�B���O�ɂ����S2Container���ݒ肳���
	 */
	private S2Container container;
	
	/**
	 * dicon�ɋL�q����Ă���Mule�C���X�^���X�̏��
	 */
	private List s2MuleConfigs;
	
	/**
	 * MuleContext
	 */
	private MuleContext muleContext;
	
	/**
	 * dicon�t�@�C���ɋL�q����Ă���S�ẴR���|�[�l���g
	 */
	private List allDiconComponentDefs = new ArrayList();
	
	/**
	 * �C���X�^���X�̍쐬 
	 * @throws MuleException MuleContext�쐬���̗�O
	 */
	public S2MuleComponentBuilderImpl() throws MuleException {
		
		//MuleContext�̍쐬
		DefaultMuleContextFactory factory = new DefaultMuleContextFactory();
		muleContext = factory.createMuleContext();
	}

//	/**
//	 * MuleConfigurationFile���w�肵���ꍇ�AMule-config����Mule���N������
//	 * @param muleConfigPass
//	 */
//	public S2MuleComponentBuilderImpl( String muleConfigPass) {
//		
//	}
//	
//	/**
//	 * 
//	 * @param smDeciptor
//	 */
//	public S2MuleComponentBuilderImpl( S2MuleConfiguration smDecriptor) {
//		
//	}
	
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
				muleContext.getRegistry().registerService(service);
			}
		} else {
			//TODO ��O����
		}
		// test configure�̒���muleServer���X�^�[�g�����Ă��܂�
		muleContext.start();
		
		return muleContext;
	}
	
	/**
	 * Service���쐬����
	 * @return
	 */
	private Service createService(S2MuleConfiguration s2MuleConfig) throws MuleException {
		
		//Mule��Default�ł���SedaService���쐬
		Service service = new SedaService();
		
		Model model = new SedaModel();
		model.initialise();
		service.setModel(model);
		
		InboundRouterCollection iRouterCollection = new DefaultInboundRouterCollection();
		
		//InboundEndpoits�̐������s��
		List endpointUris = s2MuleConfig.getInboundEndpoints();
		for(int i =0;i<endpointUris.size();i++) {
			//InboundEndpoint�̍쐬
			URIBuilder uriBuilder = new URIBuilder((String)endpointUris.get(i));
			EndpointBuilder endpointBuilder = new EndpointURIEndpointBuilder(uriBuilder,muleContext);
			InboundEndpoint endpoint = (InboundEndpoint)endpointBuilder.buildInboundEndpoint();
			
			iRouterCollection.addEndpoint(endpoint);
		}
		service.setInboundRouter(iRouterCollection);
		
		//ServiceName�̍쐬
		String serviceName;
		if (s2MuleConfig.getName() != null) {
			serviceName = s2MuleConfig.getName();
		} else {
			serviceName = DEFAULT_SERVICE_NAME;
		}
		service.setName(serviceName);
		
		//ObjectFactory�̍쐬
		ObjectFactory factory = null;
		//umo��dicon�ɐݒ肳��Ă����ꍇ�AS2MuleSimpleObjectFactory��ݒ肷��B
		if( s2MuleConfig.getUmoImpl() != null ) {
			factory = new S2MuleObjectFactory(container,s2MuleConfig.getUmoImpl()); 
		} else {
			//TODO ��O
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
	 * Mule���~������
	 * Registry�ɓo�^����Ă����R���|�[�l���g�͑S�Ĕj�������
	 *
	 */
	public void destroy() {
		muleContext.dispose();
	}
	
	public void setContainer(S2Container container) {
		this.container = container.getRoot();
	}

}
