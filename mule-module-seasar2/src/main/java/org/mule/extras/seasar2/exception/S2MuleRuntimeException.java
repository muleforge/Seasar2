/*
 * Copyright (c) Osaka Gas Information System Research Institute Co., Ltd.
 * All rights reserved.  http://www.ogis-ri.co.jp/
 * 
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.extras.seasar2.exception;

import org.seasar.framework.exception.SRuntimeException;

/**
 * S2Mule で発生したエラーを通知するための実行時例外
 * 
 * @author Shinya_Saito@ogis-ri.co.jp
 */
public class S2MuleRuntimeException extends SRuntimeException 
{
    
    private static final long serialVersionUID = -331466760857177938L;

    /**
     * インスタンスを構築します
     * 
     * @param cause 原因となった例外
     */
    public S2MuleRuntimeException(Throwable cause) 
    {
        this(null, null, cause);
    }
    
    /**
     * インスタンスを構築します
     * 
     * @param messageCode メッセージコード
     * @param args メッセージに埋め込まれる引数
     * @param cause 原因となった例外
     */
    public S2MuleRuntimeException(String messageCode, Object[] args, Throwable cause) 
    {
        super(messageCode, args, cause);
    }

}
