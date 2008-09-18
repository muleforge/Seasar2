/*
 * Copyright (c) Osaka Gas Information System Research Institute Co., Ltd.
 * All rights reserved.  http://www.ogis-ri.co.jp/
 * 
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.extras.seasar2.exception;

/**
 * 必要なProprtyが見つからないことを通知する実行時例外
 * 
 * @author Saito_Shinya@ogis-ri.co.jp
 */
public class PropertyNotFoundException extends S2MuleConfigurationException 
{

    private static final long serialVersionUID = 7448310817447042432L;

    /**
     * インスタンスを生成します
     * 
     * @param messageCode メッセージコード
     * @param args メッセージに埋め込まれる引数
     */
    public PropertyNotFoundException(String messageCode, Object[] args) 
    {
        super(messageCode, args);
    }
}
