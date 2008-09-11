package org.mule.extras.seasar2.examples.helloworld.soap.axis;

import org.mule.extras.seasar2.examples.common.Echo;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.S2ContainerFactory;
import org.seasar.framework.exception.ResourceNotFoundRuntimeException;

public class HelloWorldSOAP 
{
    
    // dicon ファイル
    private static final String CONFIGURE_PATH = "helloworld-send-soap-axis.dicon";

    private static S2Container container;
    
    public static void main(String[] args) 
    {
        try 
        {
            
            // dicon ファイルを指定して S2 コンテナを生成する
            container = S2ContainerFactory.create(CONFIGURE_PATH);
            
            //コンテナの初期化
            container.init();
        
            // S2MuleSender のインスタンスを取得する
            Echo echo = (Echo) container.getComponent(Echo.class);
        
            // メッセージを送信する
            String rtn = echo.echo("Hello World!");
        
            // 成功
            System.out.println("The message is sent successfully.");
            System.out.println("The Returned message is: \"" + rtn + "\"");
            
        
        }
        catch (ResourceNotFoundRuntimeException e)
        {
            System.out.println("dicon ファイルが見つかりません： " + CONFIGURE_PATH);
        }
        finally
        {
        	//コンテナの破棄
            container.destroy();
        }
    }

}
