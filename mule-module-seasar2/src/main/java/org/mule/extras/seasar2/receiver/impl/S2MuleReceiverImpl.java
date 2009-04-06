/*
 * Copyright (c) Osaka Gas Information System Research Institute Co., Ltd.
 * All rights reserved.  http://www.ogis-ri.co.jp/
 * 
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.extras.seasar2.receiver.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.TransactionManager;

import org.mule.api.MuleContext;
import org.mule.api.MuleException;
import org.mule.endpoint.EndpointURIEndpointBuilder;
import org.mule.endpoint.DefaultInboundEndpoint;
import org.mule.endpoint.URIBuilder;
import org.mule.extras.seasar2.endpoint.EndpointConfig;
import org.mule.extras.seasar2.exception.S2MuleConfigurationException;
import org.mule.extras.seasar2.exception.S2MuleRuntimeException;
import org.mule.extras.seasar2.receiver.S2MuleReceiver;
import org.mule.extras.seasar2.receiver.object.S2MuleObjectFactory;
import org.mule.api.service.Service;
import org.mule.api.endpoint.EndpointBuilder;
import org.mule.api.model.Model;
import org.mule.api.routing.InboundRouterCollection;
import org.mule.component.DefaultJavaComponent;
import org.mule.context.DefaultMuleContextFactory;
import org.mule.model.seda.SedaModel;
import org.mule.model.seda.SedaService;
import org.mule.routing.inbound.DefaultInboundRouterCollection;
import org.mule.api.object.ObjectFactory;
import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.S2Container;


/**
 * {@link S2MuleReceiver}の実装クラス
 * 
 * @author Saito_Shinya@ogis-ri.co.jp
 *
 */
public class S2MuleReceiverImpl implements S2MuleReceiver 
{
    
    /**
     * デフォルトのServiceName
     * Mule-configの<service name="">タグに対応
     */
    private static final String DEFAULT_SERVICE_NAME = "S2MuleComponent";
    
    /**
     * オートバインディングによってS2Containerが設定される
     */
    private S2Container container;
    
    /**
     * diconに記述されていたMuleインスタンスの情報
     */
    private List s2MuleConfigs;
    
    /**
     * MuleContext
     */
    private MuleContext muleContext;
    
    /**
     * diconファイルに記述されている全てのコンポーネント
     */
    private List allDiconComponentDefs = new ArrayList();
    
    /** 
     * トランザクションマネージャ 
     */
    private TransactionManager transactionManager;
    
    /**
     * インスタンスの作成 
     * @throws MuleException MuleContext作成時の例外
     */
    public S2MuleReceiverImpl() throws MuleException,Exception
    {    
       
    }
    
    /**
     * 
     * 
     * @return managementContext
     * @throws MuleException Muleの例外
     */
    public final void start() throws MuleException 
    {
    	try
    	{             
            s2MuleConfigs = getS2MuleConfigs(container);
            
            if ( s2MuleConfigs != null )
            {
                for ( int i = 0; i < s2MuleConfigs.size(); i++) 
                {
                    S2MuleConfiguration s2MuleConfig = 
                        (S2MuleConfiguration) s2MuleConfigs.get(i);
                    Service service = createService( s2MuleConfig );
                    
                    //Serviceを登録する
                    muleContext.getRegistry().registerService(service);
                }
            } 
            else
            {
                throw new S2MuleConfigurationException("ESML0000",null,null);
            }
            muleContext.start();            
    	} 
    	catch (Exception e)
    	{
    		throw new S2MuleRuntimeException("ESML0000", new Object[]{e},e);
    	}
    }
    
    /**
     * Serviceを作成する
     * 
     * @param s2MuleConfig Muleの構成情報
     * @return Muleにおけるサービス
     * @throws MuleException Muleの例外
     */
    private Service createService(S2MuleConfiguration s2MuleConfig) throws MuleException,Exception
    {
        
        //MuleのDefaultであるSedaServiceを作成
        Service service = new SedaService();
        
        Model model = new SedaModel();
        model.setMuleContext(muleContext);
        model.initialise();
        service.setModel(model);
        
        InboundRouterCollection iRouterCollection = new DefaultInboundRouterCollection();
        
        List endpoints = s2MuleConfig.getInboundEndpoints();
        for (int i = 0; i < endpoints.size(); i++)
        {
        	//InboundEndpointの作成
        	EndpointConfig endpointConfig = (EndpointConfig)endpoints.get(i);
        	EndpointBuilder endpointBuilder = endpointConfig.buildEndpointBuilder(muleContext);
        	DefaultInboundEndpoint endpoint = (DefaultInboundEndpoint) endpointBuilder.buildInboundEndpoint();
        	
        	if(transactionManager==null &&
        			endpoint.getTransactionConfig().isTransacted())
        	{
        		 transactionManager = (TransactionManager)container.getRoot().getComponent(TransactionManager.class);
                 muleContext.setTransactionManager(transactionManager);
        	}
        	
            iRouterCollection.addEndpoint(endpoint);
        }
        service.setInboundRouter(iRouterCollection);
        
        //ServiceNameの作成
//        String serviceName;
//        if (s2MuleConfig.getName() != null)
//        {
//            serviceName = s2MuleConfig.getName();
//        }
//        else
//        {
//            serviceName = DEFAULT_SERVICE_NAME;
//        }
//        service.setName(serviceName);
                
        //S2MuleObjectFactoryを設定
        if (s2MuleConfig.getUmoImpl() != null )
        {
        	ObjectFactory factory 
        		= new S2MuleObjectFactory(container,s2MuleConfig.getUmoImpl()); 
        	service.setComponent(new DefaultJavaComponent(factory));
        	
        	//MuleのUMOの名前を設定
        	service.setName(s2MuleConfig.getName());
        }
        else
        {
        	 throw new S2MuleConfigurationException("ESML0002", new Object[]{"umoImpl"});
        }
        
        
        return service;
    }
    
    /**
     * S2cotainerからS2MuleConfigrationを探索し、リストに格納する
     * @param container S2コンテナ
     * @return MuleConfig
     */
    private List getS2MuleConfigs( S2Container container )
    {
        List configs = new ArrayList();
        createAllDiconComponents(container, null);
        for ( int i = 0; i < allDiconComponentDefs.size(); i++ )
        {
            ComponentDef cd = (ComponentDef) allDiconComponentDefs.get(i);
            
            //S2MuleConfigurationクラスの場合、リストに追加する
            if ( cd.getComponentClass().equals(S2MuleConfiguration.class) ) 
            {
                S2MuleConfiguration s2mConfig 
                    = (S2MuleConfiguration) cd.getComponent();
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
        
        if (set == null)
        {
            set = new HashSet();
        }
         //現在のS2コンテナとおなじS2コンテナがsetに登録されている場合
        //再帰を終了させる
         if (set.contains(container)) {
             return;
         }
         set.add(container);

         // 子コンテナ
         for (int i = 0; i <  container.getChildSize(); i++)
         {
             createAllDiconComponents(container.getChild(i), set);
         }
         
         for (int i = 0; i < container.getComponentDefSize(); i++)
         {
             allDiconComponentDefs.add(container.getComponentDef(i));
         }
    }
    
	/**
     * Muleを停止させる
     * Registryに登録されていたコンポーネントは全て破棄される
     *
     */
    public final void destroy()
    {
        muleContext.dispose();
    }
    
    
    public void setMuleContext(MuleContext muleContext) 
    {
		this.muleContext = muleContext;
	}
    
    public void setContainer(S2Container container)
    {
        this.container = container.getRoot();
    }

}
