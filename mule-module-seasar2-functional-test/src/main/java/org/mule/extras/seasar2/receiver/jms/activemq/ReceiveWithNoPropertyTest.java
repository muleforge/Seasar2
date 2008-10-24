/*
 * Copyright (c) Osaka Gas Information System Research Institute Co., Ltd.
 * All rights reserved.  http://www.ogis-ri.co.jp/
 * 
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.extras.seasar2.receiver.jms.activemq;

import org.mule.extras.seasar2.sender.S2MuleSender;
import org.mule.extras.seasar2.test.component.TestProperty;
import org.seasar.extension.unit.S2TestCase;

public class ReceiveWithNoPropertyTest extends S2TestCase
{
	private S2MuleSender sender_;
	
    public ReceiveWithNoPropertyTest(String name) 
    {
        super(name);
    }

    public void setUp() throws Exception 
    {
        include("ReceiveWithNoPropertyTest.dicon");
    }

    public void testConfigure() throws Exception 
    {
    	//JMSの送信にはorg.mule.extras.seasar2.sender.jms.activemq
    	//SendByteTest, SendMapTest, SendStreamTestを利用する
        
        sender_.dispatch("OK");
        
    	while(true)
    	{
    		Thread.sleep(TestProperty.THREAD_SLEEP_TIME);
    	}
    }
}
