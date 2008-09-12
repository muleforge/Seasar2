package org.mule.extras.seasar2.receiver.jms.activemq;

import org.mule.extras.seasar2.receiver.common.TestProperty;
import org.mule.extras.seasar2.sender.S2MuleSender;
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
    	while(true){
    		Thread.sleep(TestProperty.THREAD_SLEEP_TIME);
    	}
    }
}
