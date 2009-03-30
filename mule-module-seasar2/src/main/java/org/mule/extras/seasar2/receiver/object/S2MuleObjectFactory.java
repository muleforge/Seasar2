/*
 * Copyright (c) Osaka Gas Information System Research Institute Co., Ltd.
 * All rights reserved.  http://www.ogis-ri.co.jp/
 * 
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.extras.seasar2.receiver.object;

import org.mule.api.lifecycle.InitialisationCallback;
import org.mule.api.lifecycle.InitialisationException;
import org.mule.api.object.ObjectFactory;
import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.InstanceDef;
import org.seasar.framework.container.S2Container;

/**
 * {@link ObjectFactory}の実装クラス
 * Seasar2を使ってUMOのクラスを生成する
 * 
 * @author Saito_Shinya@ogis-ri.co.jp
 *
 */
public class S2MuleObjectFactory implements ObjectFactory 
{
    
    /**
     * 作成するクラス名
     */
    private ComponentDef componentDef;
    
    /**
     * コンストラクタ
     * 
     * @param container
     * @param object
     */
    public S2MuleObjectFactory( S2Container container, Object object )
    {
        this.componentDef = container.getRoot().getComponentDef(object.getClass());
    }
    
    /**
     * S2Containerからコンポーネントを取得する
     * 
     * @return コンポーネント
     */
    public final Object getInstance() 
    {
         return componentDef.getComponent();
    }
    
    public void addObjectInitialisationCallback(InitialisationCallback callback)
    {
        // TODO Auto-generated method stub
        
    }
    
    /**
     * クラスを取得する
     * 
     * @return class
     */
    public final Class getObjectClass() 
    {
        return componentDef.getComponentClass();
    }
    
    public void initialise() throws InitialisationException 
    {
        // TODO
    }
    
    public boolean isSingleton() 
    {
        return componentDef.getInstanceDef().getName()
        .equals(InstanceDef.SINGLETON_NAME);
    }
    

    public Object getOrCreate() throws Exception 
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    /**
     * TODO 実装
     */
    public Object lookup(String id) throws Exception 
    {
        return null;
    }

    /**
     * Seasar2側でdisposeされるので不要
     */
    public void release(Object object) throws Exception 
    {
        //blank
    }
    
    /**
     * Seasar2側でdisposeされるので不要
     */
    public void dispose() 
    {
        //blank
    }

    public boolean isExternallyManagedLifecycle()
    {
        // TODO Auto-generated method stub
        return false;
    }
}
