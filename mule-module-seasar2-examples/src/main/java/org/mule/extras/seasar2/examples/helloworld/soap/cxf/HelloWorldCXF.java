/*
 * Copyright (c) Osaka Gas Information System Research Institute Co., Ltd.
 * All rights reserved.  http://www.ogis-ri.co.jp/
 * 
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.extras.seasar2.examples.helloworld.soap.cxf;

import org.mule.extras.seasar2.examples.common.Echo;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.S2ContainerFactory;
import org.seasar.framework.exception.ResourceNotFoundRuntimeException;

/**
 * SOAPメッセージをCXFを使って送信するサンプル
 * 
 * @author Saito_Shinya@ogis-ri.co.jp
 *
 */
public class HelloWorldCXF 
{
    /** diconファイルのパス*/
    private static final String CONFIGURE_PATH = "helloworld-send-cxf.dicon";

    /** S2コンテナ */
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
