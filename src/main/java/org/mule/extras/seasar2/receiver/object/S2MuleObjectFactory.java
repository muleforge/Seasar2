package org.mule.extras.seasar2.receiver.object;

import org.mule.api.lifecycle.InitialisationCallback;
import org.mule.api.lifecycle.InitialisationException;
import org.mule.api.lifecycle.LifecycleTransitionResult;
import org.mule.api.object.ObjectFactory;
import org.seasar.framework.container.S2Container;

/**
 * {@link ObjectFactory}の実装クラス
 * Seasar2を使ってUMOのクラスを生成する
 * 
 * @author Saito_Shinya@ogis-ri.co.jp
 *
 */
public class S2MuleObjectFactory implements ObjectFactory {
	
	/**
	 * Seasar2
	 */
	private S2Container container;
	
	/**
	 * 作成するクラス名
	 */
	private Object objectClassName;
	
	/**
	 * コンストラクタ
	 * 
	 * @param container
	 * @param objectClassName
	 */
	public S2MuleObjectFactory( S2Container container, Object objectClassName ){
		this.container = container;
		this.objectClassName = objectClassName;
	}
	
	public Object getInstance() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void addObjectInitialisationCallback(InitialisationCallback callback) {
		// TODO Auto-generated method stub
		
	}
	
	public Class getObjectClass() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public LifecycleTransitionResult initialise()
			throws InitialisationException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean isSingleton() {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * Seasar2からコンポーネントを取り出す
	 */
	public Object getOrCreate() throws Exception {
		Object component = container.getRoot().getComponent(objectClassName.getClass());
		return component;
	}
    
    /**
     * TODO 実装
     */
    public Object lookup(String id) throws Exception {
    	return null;
    }

    /**
     * Seasar2側でdisposeされるので不要
     */
    public void release(Object object) throws Exception {
    	
    }
	
    /**
     * Seasar2側でdisposeされるので不要
     */
	public void dispose() {
		
	}

}
