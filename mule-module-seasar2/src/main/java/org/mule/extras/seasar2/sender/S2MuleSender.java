/*
 * Copyright (c) Osaka Gas Information System Research Institute Co., Ltd.
 * All rights reserved.  http://www.ogis-ri.co.jp/
 * 
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.extras.seasar2.sender;

import java.util.Map;


/**
 * Muleを利用してメッセージを送信するコンポーネントのインタフェースです。
 * 
 * @author Shinya_Saito@ogis-ri.co.jp
 *
 */
public interface S2MuleSender 
{
    
    /**
     * 非同期メッセージを送信します。
     * 
     * @param payload メッセージの本文
     */
    void dispatch(Object payload);
    
    /**
     * 同期メッセージを送信します
     * 
     * @param payload メッセージの本文
     * @return レスポンスメッセージ
     */
    Object send(Object payload);
 
    /**
     * プロパティ付きの同期メッセージを送信します。
     * 
     * @param payload メッセージの本文
     * @param properties　プロパティ
     * @return レスポンスメッセージ
     */
    Object send(Object payload, Map properties);
    
}
