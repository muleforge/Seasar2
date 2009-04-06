/*
 * Copyright (c) Osaka Gas Information System Research Institute Co., Ltd.
 * All rights reserved.  http://www.ogis-ri.co.jp/
 * 
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.extras.seasar2.sender.jms.activemq;

import java.util.HashMap;
import java.util.Map;

import org.mule.extras.seasar2.sender.S2MuleSender;
import org.mule.extras.seasar2.sender.common.Customer;
import org.seasar.extension.unit.S2TestCase;

/**
 * 
 * ID-JMS-O-004
 * 
 * Map型のメッセージを送信する
 *
 */
public class SendMapTest extends S2TestCase {
    
    private S2MuleSender sender_;
    
    public void setUp() throws Exception 
    {
        include("SendWithNoPropertyTest.dicon");
    }
    
    public void testDispatch() throws Exception 
    {
        Map payload = new HashMap();
        payload.put("String", "SendMap : OK");
        
        payload.put("Int", 1000);
        
        payload.put("isMap", true);

        sender_.dispatch(payload);
    }

}
