package org.mule.extras.seasar2.reciver.impl;

import org.mule.config.builders.QuickConfigurationBuilder;
import org.mule.extras.seasar2.reciver.S2MuleComponentBuilder;
import org.mule.extras.seasar2.reciver.object.S2MuleConfiguration;
import org.mule.extras.seasar2.reciver.object.S2MuleSimpleObjectFactory;
import org.mule.impl.MuleDescriptor;
import org.mule.routing.inbound.InboundRouterCollection;
import org.mule.routing.outbound.OutboundPassThroughRouter;
import org.mule.routing.outbound.OutboundRouterCollection;
import org.mule.umo.UMODescriptor;
import org.mule.umo.UMOException;
import org.mule.util.object.ObjectFactory;
import org.mule.util.object.SimpleObjectFactory;
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
	private final String DEFAULT_DESCRIPTOR_NAME ="S2MuleUMO";
	
	/**
	 * デフォルトのUMOName
	 */
	private final String DEFAULT_UMO_NAME = "org.mule.components.simple.BridgeComponent";
	
	/**
	 * オートバインディングによってS2Containerが設定される
	 */
	private S2Container container;
	
	/**
	 * diconに記述されていたMuleインスタンスの情報
	 * 
	 * TODO 暫定的 将来的には複数のs2MuleConfigを扱えるようにしたい。
	 */
	private S2MuleConfiguration s2MuleConfig;
	
	private QuickConfigurationBuilder qcBuilder;
	
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
	public S2MuleComponentBuilderImpl( String muleConfigPass ) {
		
	}
	
	/**
	 * 
	 * @param smDeciptor
	 */
	public S2MuleComponentBuilderImpl( S2MuleConfiguration smDecriptor ) {
		
	}
	
	/**
	 * managementContextをstartさせ、
	 *MuleServerを起動する
	 */
	public void configure()  throws UMOException {
		qcBuilder.getManagementContext().start();
	}
	
	/**
	 * MuleDescriptorを作成する
	 * @return
	 */
	public UMODescriptor createDescriptor() throws UMOException {
		
		UMODescriptor descriptor = new MuleDescriptor();
		
		//InboundRouterCollectionの作成
		InboundRouterCollection iRouterCollection = new InboundRouterCollection();
		iRouterCollection.addEndpoint(s2MuleConfig.getInboundEndpoint());
		
		descriptor.setInboundRouter(iRouterCollection);
		
		//outboundRouterCollectionの作成
		OutboundRouterCollection oRouterCollection = new OutboundRouterCollection();
		//TODO diconで指定されたRouterを扱えるようにしたい。
		//デフォルトはPassThroughRouter
		OutboundPassThroughRouter oPassThroughRouter = new OutboundPassThroughRouter();
		oPassThroughRouter.addEndpoint(s2MuleConfig.getOutboundEndpoint());
		oRouterCollection.addRouter(oPassThroughRouter);
		
		descriptor.setOutboundRouter(oRouterCollection);
		
		// modelNameの作成

		String modelName = DEFAULT_MODEL_NAME;
		descriptor.setModelName(modelName);
		
		//descriptorNameの作成
		//TODO diconで指定されたdescriptorNmaeを扱えるようにしたい
		//S2MuleConfigurationのComponentNameから取得する?
		String descriptorName = DEFAULT_DESCRIPTOR_NAME;
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
		
		//テストコード
		qcBuilder.registerComponent(descriptor);
		
		return descriptor;
	}
	
	public void setContainer(S2Container container) {
		this.container = container;
	}

	public void setS2MuleConfig(S2MuleConfiguration muleConfig) {
		s2MuleConfig = muleConfig;
	}
}
