/*
 * Copyright (c) Osaka Gas Information System Research Institute Co., Ltd.
 * All rights reserved.  http://www.ogis-ri.co.jp/
 * 
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.extras.seasar2.sender.jms.activemq;

import org.mule.extras.seasar2.sender.S2MuleSender;
import org.seasar.extension.unit.S2TestCase;

/**
 * ID-JMS-O-002
 * 
 * byte配列型のメッセージを送信する
 *
 */
public class SendByteTest extends S2TestCase 
{
    
    private S2MuleSender sender_;
    
    public void setUp() throws Exception
    {
        include("SendWithNoPropertyTest.dicon");
    }
    
    public void testDispatch() throws Exception 
    {
        String str = "Send byte: OK";
        byte[] payload = str.getBytes();
        sender_.dispatch(payload);        
    }

}
