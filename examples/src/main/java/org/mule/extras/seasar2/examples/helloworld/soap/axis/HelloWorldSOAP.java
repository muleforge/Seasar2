package org.mule.extras.seasar2.examples.helloworld.soap.axis;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.S2ContainerFactory;
import org.seasar.framework.exception.ResourceNotFoundRuntimeException;

public class HelloWorldSOAP {
	
	// dicon ファイル
	private static final String CONFIGURE_PATH = "helloworld-soap-axis.dicon";

	public static void main(String[] args) {
		try {
			
			// dicon ファイルを指定して S2 コンテナを生成する
			S2Container container = S2ContainerFactory.create(CONFIGURE_PATH);
		
			// S2MuleSender のインスタンスを取得する
			Echo echo = (Echo) container.getComponent(Echo.class);
		
			// メッセージを送信する
			String rtn = echo.echo("Hello World!");
		
			// 成功
			System.out.println("The message is sent successfully.");
			System.out.println("The Returned message is: \"" + rtn + "\"");
		
		} catch (ResourceNotFoundRuntimeException e){
			System.out.println("dicon ファイルが見つかりません： " + CONFIGURE_PATH);
		}
	}

}
