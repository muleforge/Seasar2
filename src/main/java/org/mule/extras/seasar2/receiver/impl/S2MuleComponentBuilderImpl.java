package org.mule.extras.seasar2.receiver.impl;

import org.mule.config.builders.QuickConfigurationBuilder;
import org.mule.extras.seasar2.receiver.S2MuleComponentBuilder;
import org.mule.extras.seasar2.receiver.object.S2MuleConfiguration;
import org.mule.extras.seasar2.receiver.object.S2MuleSimpleObjectFactory;
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
	private final String DEFAULT_DESCRIPTOR_NAME ="S2MuleUMO";
	
	/**
	 * �f�t�H���g��UMOName
	 */
	private final String DEFAULT_UMO_NAME = "org.mule.components.simple.BridgeComponent";
	
	/**
	 * �I�[�g�o�C���f�B���O�ɂ����S2Container���ݒ肳���
	 */
	private S2Container container;
	
	/**
	 * dicon�ɋL�q����Ă���Mule�C���X�^���X�̏��
	 * 
	 * TODO �b��I �����I�ɂ͕�����s2MuleConfig��������悤�ɂ������B
	 */
	private S2MuleConfiguration s2MuleConfig;
	
	private QuickConfigurationBuilder qcBuilder;
	
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
	public S2MuleComponentBuilderImpl( String muleConfigPass ) {
		
	}
	
	/**
	 * 
	 * @param smDeciptor
	 */
	public S2MuleComponentBuilderImpl( S2MuleConfiguration smDecriptor ) {
		
	}
	
	/**
	 * managementContext��start�����A
	 *MuleServer���N������
	 */
	public void configure()  throws UMOException {
		qcBuilder.getManagementContext().start();
	}
	
	/**
	 * MuleDescriptor���쐬����
	 * @return
	 */
	public UMODescriptor createDescriptor() throws UMOException {
		
		UMODescriptor descriptor = new MuleDescriptor();
		
		//InboundRouterCollection�̍쐬
		InboundRouterCollection iRouterCollection = new InboundRouterCollection();
		iRouterCollection.addEndpoint(s2MuleConfig.getInboundEndpoint());
		
		descriptor.setInboundRouter(iRouterCollection);
		
		//outboundRouterCollection�̍쐬
		OutboundRouterCollection oRouterCollection = new OutboundRouterCollection();
		//TODO dicon�Ŏw�肳�ꂽRouter��������悤�ɂ������B
		//�f�t�H���g��PassThroughRouter
		OutboundPassThroughRouter oPassThroughRouter = new OutboundPassThroughRouter();
		oPassThroughRouter.addEndpoint(s2MuleConfig.getOutboundEndpoint());
		oRouterCollection.addRouter(oPassThroughRouter);
		
		descriptor.setOutboundRouter(oRouterCollection);
		
		// modelName�̍쐬

		String modelName = DEFAULT_MODEL_NAME;
		descriptor.setModelName(modelName);
		
		//descriptorName�̍쐬
		//TODO dicon�Ŏw�肳�ꂽdescriptorNmae��������悤�ɂ�����
		//S2MuleConfiguration��ComponentName����擾����?
		String descriptorName = DEFAULT_DESCRIPTOR_NAME;
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
		
		//�e�X�g�R�[�h
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
