package org.mule.extras.seasar2.receiver.object;

import org.mule.umo.lifecycle.InitialisationException;
import org.mule.util.object.ObjectFactory;
import org.seasar.framework.container.S2Container;

/**
 * {@link ObjectFactory}の実装クラス
 * Seasar2を使ってUMOのクラスを生成する。
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

	public S2MuleObjectFactory( S2Container container, Object objectClassName ){
		this.container = container;
		this.objectClassName = objectClassName;
	}
	
	/**
	 * Seasar2からコンポーネントを取り出す。
	 */
	public Object create() throws Exception {
		Object component = container.getComponent(objectClassName.getClass());
		return component;
	}

	/**
	 * Seasar2側でintialiseされるため不要
	 */
	public void initialise() throws InitialisationException {
		//No implement
	}

	/**
	 * Seasar2側でdisposeされるため不要
	 */
	public void dispose() {
		//No implement
	}

}
