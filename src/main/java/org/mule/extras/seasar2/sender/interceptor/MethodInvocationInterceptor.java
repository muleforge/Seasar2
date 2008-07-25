package org.mule.extras.seasar2.sender.interceptor;

import java.lang.reflect.Method;

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
            String methodName = method.getName();
            sender.setProperty("method", methodName);
            return sender.send(payload);
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
