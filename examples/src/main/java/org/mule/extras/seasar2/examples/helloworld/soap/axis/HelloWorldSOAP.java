package org.mule.extras.seasar2.examples.helloworld.soap.axis;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.S2ContainerFactory;
import org.seasar.framework.exception.ResourceNotFoundRuntimeException;

public class HelloWorldSOAP {
	
	// dicon �t�@�C��
	private static final String CONFIGURE_PATH = "helloworld-soap-axis.dicon";

	public static void main(String[] args) {
		try {
			// dicon �t�@�C�����w�肵�� S2 �R���e�i�𐶐�����
			S2Container container = S2ContainerFactory.create(CONFIGURE_PATH);
		
			// S2MuleSender �̃C���X�^���X���擾����
			Echo echo = (Echo) container.getComponent(Echo.class);
		
			// ���b�Z�[�W�𑗐M����
			String rtn = echo.echo("Hello World!");
		
			// ����
			System.out.println("The message is sent successfully.");
			System.out.println("The Returned message is: \"" + rtn + "\"");
		
		} catch (ResourceNotFoundRuntimeException e){
			System.out.println("dicon �t�@�C����������܂���F " + CONFIGURE_PATH);
		}
	}

}
