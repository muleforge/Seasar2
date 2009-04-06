/*
 * Copyright (c) Osaka Gas Information System Research Institute Co., Ltd.
 * All rights reserved.  http://www.ogis-ri.co.jp/
 * 
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.extras.seasar2.receiver;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mule.api.MuleException;
import org.mule.api.MuleContext;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.S2ContainerFactory;

/**
 * コマンドライン引数--dicon diconfileを指定すると、
 * diocnファイルに書かれているMuleComponentの情報を元に
 * MuleServerを起動するクラスです。
 * コマンドライン引数を省略するとapp.diconになります
 * 
 * @author Saito_Shinya@ogis-ri.co.jp
 *
 */
public class S2MuleServer {
    /**
     * デフォルトのdiconファイル名
     */
    private static final String DEFAULT_DICON_FILE = "app.dicon";
    
    /**
     * logger
     */
    private static final Log logger = LogFactory.getLog( S2MuleServer.class );
    
    /**
     * S2Container
     */
    private S2Container container;
    
    /**
     * Muleのマネジメントコンテキスト
     */
    private MuleContext muleContext;
    
     /**
      *  シャットダウンフックと同期を取るためのラッチ
      */
    protected static final CountDownLatch latch = new CountDownLatch(1);

    
    /**
     * Main
     */
    public static void main( String[] args ) throws Exception 
    {
        S2MuleServer server = new S2MuleServer(args);
        server.run();
    }
    
    /**
     * インスタンスを生成する
     * 
     * @param args コマンドライン引数
     */
    public S2MuleServer( String[] args ) 
    {
        init(args);
    }
    
    /**
     * 初期化を行う
     * 
     * @param args
     */
    public void init( String[] args ) 
    {
        String dicon = getDicon(args);
        S2Container container = createS2Container(dicon);
        setContainer(container);
    }
    
    /**
     * MuleServerを起動します。
     *
     *@exception MuleException Muleの例外
     */
    public void run() throws MuleException 
    {
    
         Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() 
                {
                   destoryS2Container();
                    latch.countDown();
                }
            });
            
            try 
            {
                //テスト
//                Thread.sleep(3000);
//                try {
//                    builder.destroy();
//                } catch (Exception e) {
//                    
//                }
                //テスト終わり
                latch.await();
            }
            catch (InterruptedException e) 
            {
                destoryS2Container();
            } 
    }
    
    /**
     *  S2Containerを作成します。
     * @param dicon diconファイル名
     * @return S2Container
     */
    private S2Container createS2Container(String dicon) 
    {
        S2Container container = S2ContainerFactory.create(dicon);
        return container;
    }
    
    /**
     * S2Containerを破棄します。
     */
    private void destoryS2Container() 
    {
        this.container.destroy();
    }
    
    
    /**
     * コマンドライン引数で指定されたdiconファイルのパス名を返します。
     * 
     * @param args
     *            コマンドライン引数
     * @return diconファイルのパス名
     */
    private String getDicon(final String[] args) 
    {
        final String dicon = getArg("--dicon", args);
        return dicon.equals("") ? DEFAULT_DICON_FILE : dicon;
    }
    
     /**
      * コピー org.seasar.jms.server.Main#getArg
      * 
     * コマンドライン引数から指定されたキーに対応する値を返します。
     * 
     * @param name
     *            コマンドライン引数の名前
     * @param args
     *            コマンドライン引数
     * @return コマンドライン引数
     */
    private String getArg(final String name, final String[] args) 
    {
        for (int i = 0; i < args.length; i++) 
        {
            if (args[i].equals(name)) 
            {
                if (i + 1 < args.length) 
                {
                    return args[i + 1];
                }
                throw new IllegalArgumentException(Arrays.toString(args));
            }
        }
        return "";
    }

    public void setContainer(S2Container container) {
        this.container = container;
    }

    
}
