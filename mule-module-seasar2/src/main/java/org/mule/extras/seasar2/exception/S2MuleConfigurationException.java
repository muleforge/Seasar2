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
import org.seasar.framework.message.MessageFormatter;

/**
 * dicon ファイルから構成を作成するときに発生したエラーを通知するための例外
 * 
 * @author Saito_Shinya@ogis-ri.co.jp
 */
public class S2MuleConfigurationException extends SRuntimeException 
{
    /** UID */
    private static final long serialVersionUID = -1235115149163252649L;

    /**
     * インスタンスを生成します。
     * 
     * @param messageCode メッセージコード
     */
    public S2MuleConfigurationException(String messageCode) 
    {
        this(messageCode, null, null);
    }
    
    /**
     * インスタンスを生成します
     * 
     * @param messageCode メッセージコード
     * @param args メッセージに埋め込まれる引数
     */
    public S2MuleConfigurationException(String messageCode, Object[] args) 
    {
        this(messageCode, args, null);
    }
    
    /**
     * インスタンスを生成します。
     * 
     * @param messageCode メッセージコード
     * @param args メッセージに埋め込まれる引数
     * @param cause 原因となった例外
     *
     */
    public S2MuleConfigurationException(String messageCode, Object[] args, Throwable cause) 
    {
        super(messageCode, args, cause);
    }
}
