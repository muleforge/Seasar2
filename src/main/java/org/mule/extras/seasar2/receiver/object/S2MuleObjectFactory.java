package org.mule.extras.seasar2.reciver.object;

import org.mule.umo.lifecycle.InitialisationException;
import org.mule.util.object.ObjectFactory;
import org.seasar.framework.container.S2Container;

/**
 * {@link ObjectFactory}�̎����N���X
 * Seasar2���g����UMO�̃N���X�𐶐�����B
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
	 * �쐬����N���X��
	 */
	private Object objectClassName;

	public S2MuleObjectFactory( S2Container container, Object objectClassName ){
		this.container = container;
		this.objectClassName = objectClassName;
	}
	
	/**
	 * Seasar2����R���|�[�l���g�����o���B
	 */
	public Object create() throws Exception {
		Object component = container.getComponent(objectClassName.getClass());
		return component;
	}

	/**
	 * Seasar2����intialise����邽�ߕs�v
	 */
	public void initialise() throws InitialisationException {
		//No implement
	}

	/**
	 * Seasar2����dispose����邽�ߕs�v
	 */
	public void dispose() {
		//No implement
	}

}
