/*
 * Copyright (c) Osaka Gas Information System Research Institute Co., Ltd.
 * All rights reserved.  http://www.ogis-ri.co.jp/
 * 
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.extras.seasar2.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.mule.extras.seasar2.connector.ConnectorConfig;
import org.mule.extras.seasar2.receiver.impl.S2MuleConfiguration;
import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.S2Container;

/**
 * S2Muleのコンポーネントに対するユーティリティクラスです。
 * 
 * @author Saito_Shinya@ogis-ri.co.jp
 *
 */
public class S2MuleComponentUtil
{
    /**
     * インスタンスを生成する
     */
    private S2MuleComponentUtil()
    {
        //blank
    }
    
    /** diconファイルに記述されている全てのコンポーネント　*/
    private static List < ComponentDef > allDiconComponentDefs;
    
    /**
     * diconファイルに記述されている全てのコンポーネントを取得する
     * 再帰を利用し、全てのdiconファイルを検索する
     * 
     * @param container S2Container
     * @param set S2Containerが格納される作業用変数
     */
    private static void createAllDiconComponents(S2Container container, Set set) 
    {
         if (allDiconComponentDefs == null)
         {
             allDiconComponentDefs = new ArrayList < ComponentDef > ();
         }
        
         if (set == null)
         {
             set = new HashSet < S2Container > ();
         }
         
         //現在のS2コンテナとおなじS2コンテナがsetに登録されている場合
         //再帰を終了させる
         if (set.contains(container)) 
         {
             return;
         }
         set.add(container);

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
     * S2cotainerからS2MuleConfigrationを探索し、リストに格納する
     * @param container S2コンテナ
     * @return configs MuleConfigのリスト
     */
    public static List < S2MuleConfiguration > getS2MuleConfigs(S2Container container)
    {
        List < S2MuleConfiguration > configs 
            = new ArrayList < S2MuleConfiguration > ();
        if (allDiconComponentDefs == null)
        {
            createAllDiconComponents(container, null);
        }
        for (int i = 0; i < allDiconComponentDefs.size(); i++ )
        {
            ComponentDef cd = allDiconComponentDefs.get(i);
            
            //S2MuleConfigurationクラスの場合、リストに追加する
            if ( cd.getComponent() instanceof S2MuleConfiguration)
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
     * S2ContainerからConnectorConfigを探索し、リストに格納する
     * 
     * @param container S2コンテナ
     * @return connectors ConnectorConfigのリスト
     */
    public static List getConnectorConfigs(S2Container container)
    {
        List connectors = new ArrayList();
        if (allDiconComponentDefs == null)
        {
            createAllDiconComponents(container, null);
        }
        for (int i = 0; i < allDiconComponentDefs.size(); i++)
        {
            ComponentDef cd = allDiconComponentDefs.get(i);
            
            if ( cd.getComponent() instanceof ConnectorConfig)
            {
                ConnectorConfig connectorConfig
                    = (ConnectorConfig) cd.getComponent();
                connectors.add(connectorConfig);
            }
            
        }
        return connectors;
    }
    
}
