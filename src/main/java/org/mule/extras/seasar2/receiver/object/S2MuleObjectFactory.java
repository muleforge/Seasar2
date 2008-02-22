package org.mule.extras.seasar2.receiver.object;

import org.mule.api.lifecycle.InitialisationException;
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
	
	/**
	 * �R���X�g���N�^
	 * 
	 * @param container
	 * @param objectClassName
	 */
	public S2MuleObjectFactory( S2Container container, Object objectClassName ){
		this.container = container;
		this.objectClassName = objectClassName;
	}
	
	/**
	 * TODO ����
	 */
	public void initialise() throws InitialisationException {
		
	}
	
	
	/**
	 * Seasar2����R���|�[�l���g�����o���B
	 */
	public Object getOrCreate() throws Exception {
		//TODO umo��dicon�̂ǂ��ɏ����Ă����Ă��擾�ł���悤�ɂ���
		Object component = container.getComponent(objectClassName.getClass());
		return component;
	}

	/**
	 * TODO ����
	 */
    public Class getObjectClass() throws Exception {
    	return null;
    }
    
    /**
     * TODO ����
     */
    public Object lookup(String id) throws Exception {
    	return null;
    }

    /**
     * Seasar2����dispose�����̂ŕs�v
     */
    public void release(Object object) throws Exception {
    	
    }
	
    /**
     * Seasar2����dispose�����̂ŕs�v
     */
	public void dispose() {
		
	}

}
