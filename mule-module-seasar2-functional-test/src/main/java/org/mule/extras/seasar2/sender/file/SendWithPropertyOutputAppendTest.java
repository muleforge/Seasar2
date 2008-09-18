/*
 * Copyright (c) Osaka Gas Information System Research Institute Co., Ltd.
 * All rights reserved.  http://www.ogis-ri.co.jp/
 * 
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.extras.seasar2.sender.file;

import org.mule.extras.seasar2.sender.S2MuleSender;
import org.seasar.extension.unit.S2TestCase;

/**
 * ID:FILE-O-003
 * 
 * プロパティoutputAppendを設定し、Fileプロバイダを使って
 *メッセージを送信する。
 */
public class SendWithPropertyOutputAppendTest extends S2TestCase {
    
    private S2MuleSender sender_;
    
    public void setUp() throws Exception 
    {
        include("SendWithPropertyOutputAppendTest.dicon");
    }
    
    public void testDispatch() throws Exception 
    {
        int loopNum = 10;
        for (int  i = 0; i < loopNum; i++)
        {
            sender_.dispatch("SendWithPropertyOutputAppendTest : OK " + " Num=" + i  + "\n");
        }
    }
    
}
