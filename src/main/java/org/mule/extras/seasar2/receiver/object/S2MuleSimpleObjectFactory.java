package org.mule.extras.seasar2.receiver.object;

import org.mule.umo.lifecycle.InitialisationException;
import org.mule.util.object.ObjectFactory;

/**
 * {@link ObjectFactory}の実装クラス
 * 設定されたオブジェクトを返す。
 * Factoryの役割はない。MuleDescriptorに設定するためだけに作成されたクラス。
 * 
 * @author Saito_Shinya@ogis-ri.co.jp
 *
 */
public class S2MuleSimpleObjectFactory implements ObjectFactory {
	
	/**
	 * UMOの実装クラス
	 */
	private Object umoImpl;
	
	public S2MuleSimpleObjectFactory(Object umoImpl) {
		this.umoImpl = umoImpl;
	}

	/**
	 * UMOの実装クラスを返す。
	 */
	public Object create() throws Exception {
		return umoImpl;
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
