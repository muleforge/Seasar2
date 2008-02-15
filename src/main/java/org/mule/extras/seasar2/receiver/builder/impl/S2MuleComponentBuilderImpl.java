package org.mule.extras.seasar2.receiver.builder.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.mule.config.builders.QuickConfigurationBuilder;
import org.mule.extras.seasar2.receiver.builder.S2MuleComponentBuilder;
import org.mule.extras.seasar2.receiver.object.S2MuleConfiguration;
import org.mule.extras.seasar2.receiver.object.S2MuleSimpleObjectFactory;
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
	private QuickConfigurationBuilder qcBuilder;
	
	/**
	 * diconファイルに記述されている全てのコンポーネント
	 */
	private List allDiconComponentDefs = new ArrayList();
	
	/**
	 * 
	 * @throws UMOException
	 */
	public S2MuleComponentBuilderImpl() throws UMOException {
		//QuickConfigurationBuilderの作成
		qcBuilder = new QuickConfigurationBuilder(false);
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
	 * MuleDescriptorをregistryに登録する。
	 * TODO Connectorをregistryに登録する。
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
				//MuleDescriptorを登録する
				qcBuilder.registerComponent(descriptor);
			}
		} else {
			//TODO 例外を投げる
		}
		// test configureの中でmuleServerをスタートさせてしまう
		qcBuilder.getManagementContext().start();
		
		
		return qcBuilder.getManagementContext();
	}
	
	/**
	 * MuleDescriptorを作成する
	 * @return
	 */
	private UMODescriptor createDescriptor(S2MuleConfiguration s2MuleConfig) throws UMOException {
		
		if(!s2MuleConfig.isInitalize()){
			s2MuleConfig.initialize();
		}
		
		UMODescriptor descriptor = new MuleDescriptor();
		
		//InboundRouterCollectionの作成
		InboundRouterCollection iRouterCollection = new InboundRouterCollection();
		
		//InboundEndpoitsの数だけ行う
		List inboundEndpoints = s2MuleConfig.getInboundEndpoints();
		for(int i =0;i<inboundEndpoints.size();i++) {
			UMOEndpoint endpoint = (UMOEndpoint)inboundEndpoints.get(i);
			iRouterCollection.addEndpoint(endpoint);
		}
		descriptor.setInboundRouter(iRouterCollection);
		
		//outboundRouterCollectionの作成
		OutboundRouterCollection oRouterCollection = new OutboundRouterCollection();
		//TODO diconで指定されたRouterを扱えるようにしたい。
		//デフォルトはPassThroughRouter
		OutboundPassThroughRouter oPassThroughRouter = new OutboundPassThroughRouter();
		MuleEndpoint outbound = (MuleEndpoint)s2MuleConfig.getOutboundEndpoint();
		outbound.setManagementContext(qcBuilder.getManagementContext());
		
		oPassThroughRouter.addEndpoint(outbound);
		oRouterCollection.addRouter(oPassThroughRouter);
		
		descriptor.setOutboundRouter(oRouterCollection);
		
		// modelNameの作成
		String modelName = DEFAULT_MODEL_NAME;
		descriptor.setModelName(modelName);
		
		//descriptorNameの作成
		String descriptorName;
		if (s2MuleConfig.getName() != null) {
			descriptorName = s2MuleConfig.getName();
		} else {
			descriptorName = DEFAULT_DESCRIPTOR_NAME;
		}
		descriptor.setName(descriptorName);
		
		//ObjectFactoryの作成
		ObjectFactory factory = null;
		//umoがdiconに設定されていた場合、S2MuleSimpleObjectFactoryを設定する。
		if( s2MuleConfig.getUmoImpl() != null ) {
			factory = new S2MuleSimpleObjectFactory(s2MuleConfig.getUmoImpl()); 
		} else {
			//umoがdiconに設定されていない場合、BridgeComponentを設定する。
			factory = new SimpleObjectFactory(DEFAULT_UMO_NAME);
		}
		descriptor.setServiceFactory(factory);
		
		return descriptor;
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
		qcBuilder.getManagementContext().getNotificationManager().dispose();
		qcBuilder.getManagementContext().getWorkManager().dispose();
		qcBuilder.getManagementContext().getRegistry().dispose();
		qcBuilder.getManagementContext().dispose();
	}
	
	public void setContainer(S2Container container) {
		this.container = container.getRoot();
	}

}
