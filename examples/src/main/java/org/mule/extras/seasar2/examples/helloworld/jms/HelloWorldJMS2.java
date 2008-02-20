package org.mule.extras.seasar2.examples.helloworld.jms;

import org.mule.extras.seasar2.sender.S2MuleSender;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.S2ContainerFactory;
import org.seasar.framework.exception.ResourceNotFoundRuntimeException;

public class HelloWorldJMS2 {

	// dicon �t�@�C��
	private static final String CONFIGURE_PATH = "helloworld-jms-2.dicon";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// dicon �t�@�C�����w�肵�� S2 �R���e�i�𐶐�����
			S2Container container = S2ContainerFactory.create(CONFIGURE_PATH);
			
			// S2MuleSender �̃C���X�^���X���擾����
			S2MuleSender sender = (S2MuleSender) container.getComponent(S2MuleSender.class);
			
			// ���b�Z�[�W�𑗐M����
			sender.dispatch("Hello World!");
			
			// ����
			System.out.println("The message is sent successfully.");
			
		} catch (ResourceNotFoundRuntimeException e){
			System.out.println("dicon �t�@�C����������܂���F " + CONFIGURE_PATH);
		}
	}
	
}
