package org.mule.extras.seasar2.receiver.builder.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.mule.MuleServer;
import org.mule.RegistryContext;
import org.mule.api.MuleContext;
import org.mule.api.MuleException;
import org.mule.endpoint.InboundEndpoint;
import org.mule.endpoint.URIBuilder;
import org.mule.extras.seasar2.receiver.builder.S2MuleComponentBuilder;
import org.mule.extras.seasar2.receiver.object.S2MuleConfiguration;
import org.mule.extras.seasar2.receiver.object.S2MuleObjectFactory;
import org.mule.api.service.Service;
import org.mule.api.model.Model;
import org.mule.api.routing.InboundRouterCollection;
import org.mule.context.DefaultMuleContextBuilder;
import org.mule.context.DefaultMuleContextFactory;
import org.mule.model.seda.SedaModel;
import org.mule.model.seda.SedaService;
import org.mule.routing.inbound.DefaultInboundRouterCollection;
import org.mule.util.object.ObjectFactory;
import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.S2Container;



/**
 * {@link S2MuleComponentBuilder}の実装クラス
 * 
 * @author Saito_Shinya@ogis-ri.co.jp
 *
 */
public class S2MuleComponentBuilderImpl implements S2MuleComponentBuilder {
	
	/**
	 * デフォルトのmodleName
	 * Mule-configの<modle name="">タグに対応
	 */
	private final String DEFAULT_MODEL_NAME = "default-qbuilder";
	
	/**
	 * デフォルトのdescriptorName
	 * Mule-configの<mule-descriptor name="">タグに対応
	 */
	private final String DEFAULT_DESCRIPTOR_NAME = "S2MuleUMO";
	
	/**
	 * デフォルトのUMOName
	 */
	private final String DEFAULT_UMO_NAME 
		= "org.mule.components.simple.BridgeComponent";
	
	/**
	 * オートバインディングによってS2Containerが設定される
	 */
	private S2Container container;
	
	/**
	 * TODO 将来的には複数のs2MuleConfigを扱えるようにしたい。
	 * diconに記述されていたMuleインスタンスの情報
	 * 暫定的
	 */
	private List s2MuleConfigs;
	
	/**
	 * MuleのquickConfigurationBuilderを利用
	 */
	//private QuickConfigurationBuilder qcBuilder;
	private MuleContext context;
	
	/**
	 * diconファイルに記述されている全てのコンポーネント
	 */
	private List allDiconComponentDefs = new ArrayList();
	
	/**
	 * 
	 * @throws MuleException
	 */
	public S2MuleComponentBuilderImpl() throws MuleException {
		//QuickConfigurationBuilderの作成
		//qcBuilder = new QuickConfigurationBuilder(false);
		
		//MuleContextの作成
		DefaultMuleContextFactory factory = new DefaultMuleContextFactory();
		context = factory.createMuleContext();
		
	}

	/**
	 * MuleConfigurationFileを指定した場合、Mule-configからMuleを起動する
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
	 * Serviceをregistryに登録する。
	 * TODO Connectorをregistryに登録する。
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
				
				//Serviceを登録する
				context.getRegistry().registerService(service);
			}
		} else {
			//TODO 例外処理
		}
		// test configureの中でmuleServerをスタートさせてしまう
		context.start();
		
		return context;
	}
	
	/**
	 * MuleDescriptorを作成する
	 * @return
	 */
	private Service createService(S2MuleConfiguration s2MuleConfig) throws MuleException {
		
//		if(!s2MuleConfig.isInitalize()){
//			s2MuleConfig.initialize();
//		}
		
		//MuleのDefaultであるSedaServiceを作成
		Service service = new SedaService();
		
		Model model = new SedaModel();
		service.setModel(model);
		
		InboundRouterCollection iRouterCollection = new DefaultInboundRouterCollection();
		
		//InboundEndpoitsの数だけ行う
		List endpointUris = s2MuleConfig.getInboundEndpoints();
		for(int i =0;i<endpointUris.size();i++) {
			//InboundEndpointの作成
			URIBuilder uriBuilder = new URIBuilder((String)endpointUris.get(i));
			InboundEndpoint endpoint = new InboundEndpoint();
			endpoint.setEndpointURI(uriBuilder.getEndpoint());
			iRouterCollection.addEndpoint(endpoint);
		}
		service.setInboundRouter(iRouterCollection);
		
//		//outboundRouterCollectionの作成
//		OutboundRouterCollection oRouterCollection = new OutboundRouterCollection();
//		//TODO diconで指定されたRouterを扱えるようにしたい。
//		//デフォルトはPassThroughRouter
//		OutboundPassThroughRouter oPassThroughRouter = new OutboundPassThroughRouter();
//		MuleEndpoint outbound = (MuleEndpoint)s2MuleConfig.getOutboundEndpoint();
//		outbound.setManagementContext(qcBuilder.getManagementContext());
//		
//		oPassThroughRouter.addEndpoint(outbound);
//		oRouterCollection.addRouter(oPassThroughRouter);
//		
//		descriptor.setOutboundRouter(oRouterCollection);
		
//		// modelNameの作成
//		String modelName = DEFAULT_MODEL_NAME;
//		descriptor.setModelName(modelName);
		
		//descriptorNameの作成
		String serviceName;
		if (s2MuleConfig.getName() != null) {
			serviceName = s2MuleConfig.getName();
		} else {
			serviceName = DEFAULT_DESCRIPTOR_NAME;
		}
		service.setName(serviceName);
		
		//ObjectFactoryの作成
		ObjectFactory factory = null;
		//umoがdiconに設定されていた場合、S2MuleSimpleObjectFactoryを設定する。
		if( s2MuleConfig.getUmoImpl() != null ) {
			factory = new S2MuleObjectFactory(container,s2MuleConfig.getUmoImpl()); 
		} else {
			//umoがdiconに設定されていない場合、BridgeComponentを設定する。
			factory = new S2MuleObjectFactory(container,s2MuleConfig.getUmoImpl());
		}
		service.setServiceFactory(factory);
		
		return service;
	}
	
	/**
	 * S2cotainerからS2MuleConfigrationを探索し、リストに格納する
	 * @param container
	 * @return
	 */
	private List getS2MuleConfigs( S2Container container ) {
		List configs = new ArrayList();
		createAllDiconComponents(container, null);
		for( int i = 0; i < allDiconComponentDefs.size(); i++ ) {
			ComponentDef cd = (ComponentDef)allDiconComponentDefs.get(i);
			
			//S2MuleConfigurationクラスの場合、リストに追加する
			if ( cd.getComponentClass().equals(S2MuleConfiguration.class) ) {
				S2MuleConfiguration s2mConfig 
					= (S2MuleConfiguration)cd.getComponent();
				//S2MuleConfigの名前の登録
				s2mConfig.setName(cd.getComponentName());
				
				configs.add(s2mConfig);
			}
		}
		return configs;
	}
	
	
	/**
	 * diconファイルに記述されている全てのコンポーネントを取得する
	 * 再帰を利用し、全てのdiconファイルを検索する
	 * 
	 * @param container S2Container
	 * @param set S2Containerが格納される作業用
	 * @return
	 */
	private void createAllDiconComponents(S2Container container, Set set) {
		
		if (set == null){
			set = new HashSet();
		}
		 //現在のS2コンテナとおなじS2コンテナがsetに登録されている場合
		//再帰を終了させる
	     if (set.contains(container)) {
	    	 return;
	     }
	     set.add(container);

	     // 子コンテナへもぐる
	     for (int i = 0; i <  container.getChildSize(); i++) {
	    	 createAllDiconComponents(container.getChild(i), set);
	     }
	     
	     for(int i = 0;i < container.getComponentDefSize();i ++) {
	    	 allDiconComponentDefs.add(container.getComponentDef(i));
	     }
	}
	
	/**
	 * MuleServerを停止させる
	 * Registryに登録されていたコンポーネントは全て破棄される
	 *
	 */
	public void destroy() {
		
	}
	
	public void setContainer(S2Container container) {
		this.container = container.getRoot();
	}

}
