/*
 * Copyright (c) Osaka Gas Information System Research Institute Co., Ltd.
 * All rights reserved.  http://www.ogis-ri.co.jp/
 * 
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.extras.seasar2.sender.jms.activemq;

import org.mule.extras.seasar2.sender.common.MlutiDispatcher;
import org.seasar.extension.unit.S2TestCase;

/**
 * ID-JMS-O-005
 * XA Transactionalなメッセージを送信する
 *
 */
public class SendWithXATransactionTest extends S2TestCase {
    
    private MlutiDispatcher dispatcher_;
    
    public void setUp() throws Exception 
    {
        include("SendWithXATransactionTest.dicon");
    }
    
    public void testMlutiDispatch() throws Exception 
    {
        dispatcher_.mlutiDispatch();
    }
}
