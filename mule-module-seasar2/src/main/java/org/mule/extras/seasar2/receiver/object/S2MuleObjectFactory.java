package org.mule.extras.seasar2.receiver.object;

import org.mule.api.lifecycle.InitialisationCallback;
import org.mule.api.lifecycle.InitialisationException;
import org.mule.api.lifecycle.LifecycleTransitionResult;
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
     * S2コンテナ
     */
    private S2Container container;
    
    /**
     * 作成するクラス名
     */
    private ComponentDef componentDef;
    
    /**
     * コンストラクタ
     * 
     * @param container
     * @param objectClassName
     */
    public S2MuleObjectFactory( S2Container container, Object object )
    {
        this.container = container;
        this.componentDef = container.getRoot().getComponentDef(object.getClass());
    }
    
    public Object getInstance() throws Exception 
    {
         return componentDef.getComponent();
    }
    
    public void addObjectInitialisationCallback(InitialisationCallback callback) 
    {
        // TODO Auto-generated method stub
        
    }
    
    public Class getObjectClass() 
    {
        return componentDef.getComponentClass();
    }
    
    public void initialise() throws InitialisationException 
    {
	// TODO Auto-generated method stub
	
    }
    
    public boolean isSingleton() 
    {
        if(componentDef.getInstanceDef().getName().equals(InstanceDef.SINGLETON_NAME))
        {
        	return true;
        } 
        else
        {
        	return false;
        }
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
        
    }
    
    /**
     * Seasar2側でdisposeされるので不要
     */
    public void dispose() 
    {
        
    }

}
