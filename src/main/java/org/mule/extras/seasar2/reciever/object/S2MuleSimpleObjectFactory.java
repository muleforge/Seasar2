package org.mule.extras.seasar2.reciver.object;

import org.mule.umo.lifecycle.InitialisationException;
import org.mule.util.object.ObjectFactory;

/**
 * {@link ObjectFactory}�̎����N���X
 * �ݒ肳�ꂽ�I�u�W�F�N�g��Ԃ��B
 * Factory�̖����͂Ȃ��BMuleDescriptor�ɐݒ肷�邽�߂����ɍ쐬���ꂽ�N���X�B
 * 
 * @author Saito_Shinya@ogis-ri.co.jp
 *
 */
public class S2MuleSimpleObjectFactory implements ObjectFactory {
	
	/**
	 * UMO�̎����N���X
	 */
	private Object umoImpl;
	
	public S2MuleSimpleObjectFactory(Object umoImpl) {
		this.umoImpl = umoImpl;
	}

	/**
	 * UMO�̎����N���X��Ԃ��B
	 */
	public Object create() throws Exception {
		return umoImpl;
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
