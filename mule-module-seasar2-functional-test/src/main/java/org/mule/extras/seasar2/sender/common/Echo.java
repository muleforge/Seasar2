/*
 * Copyright (c) Osaka Gas Information System Research Institute Co., Ltd.
 * All rights reserved.  http://www.ogis-ri.co.jp/
 * 
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.extras.seasar2.sender.common;

/**
 * テスト用のEchoインターフェース
 * 
 * @author Saito_Shinya@ogis-ri.co.jp
 */
public interface Echo 
{
    /**
     * Echo
     * @param echo 入力文字列
     * @return エコー文字列
     */
    String echo(String echo);
    
}
