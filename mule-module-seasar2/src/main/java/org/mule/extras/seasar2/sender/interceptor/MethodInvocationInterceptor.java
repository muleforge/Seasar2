/*
 * Copyright (c) Osaka Gas Information System Research Institute Co., Ltd.
 * All rights reserved.  http://www.ogis-ri.co.jp/
 * 
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.extras.seasar2.sender.interceptor;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.aopalliance.intercept.MethodInvocation;
import org.mule.extras.seasar2.sender.S2MuleSender;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;
import org.seasar.framework.util.MethodUtil;

/**
 * S2Muleの同期通信を行うときのインターセプタです
 * 
 * @author Saito_Shinya@ogis-ri.co.jp
 */
public class MethodInvocationInterceptor extends AbstractInterceptor 
{
    /** 元のメソッドを表す定数 */
    public static final String ORIGINAL_METHOD = "orijinalMethod";
    
    /** UID */
    private static final long serialVersionUID = -9223020020060206825L;
    
    /** S2MuleSender */
    private S2MuleSender sender;

    /**
     * カットポイントを設定されたメソッドが起動されたときに呼び出されます。
     * S2MuleSenderを使ってメッセージを送信します
     * 
     * @return 呼び出したメソッドの返り値
     * @param methodInvocation 
     * @exception Throwable 全ての例外
     */
    public Object invoke(MethodInvocation methodInvocation) throws Throwable 
    {
        
        Method method = methodInvocation.getMethod();
        if (MethodUtil.isAbstract(method)) 
        {
            Object payload = methodInvocation.getArguments();
            Map properties = new HashMap();
            properties.put(ORIGINAL_METHOD, method);
            return sender.send(payload, properties);
        }
        return methodInvocation.proceed();
                
    }
    
    /**
     * 外部リソースへメッセージ送信を行うS2MuleSenderを設定します。
     * このプロパティは必須です
     * 
     * @param sender
     */
    public void setSender(S2MuleSender sender) 
    {
        this.sender = sender;
    }

}
