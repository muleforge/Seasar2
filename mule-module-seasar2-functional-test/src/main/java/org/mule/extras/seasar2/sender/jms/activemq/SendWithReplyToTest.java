package org.mule.extras.seasar2.sender.jms.activemq;

import org.mule.extras.seasar2.sender.S2MuleSender;
import org.mule.extras.seasar2.sender.common.Echo;
import org.seasar.extension.unit.S2TestCase;

/**
 * ID-JMS-O-001
 * 
 * 最低限必要なプロパティのみを設定して、String型のメッセージを送信する
 *
 */
public class SendWithReplyToTest extends S2TestCase 
{
    
	private Echo echo_;
	
    private S2MuleSender sender_;
    
    public void setUp() throws Exception 
    {
        include("SendReplyToTest.dicon");
        echo_ = (Echo) getComponent(Echo.class);
    }
    
    public void testSend() throws Exception 
    {
       assertEquals("Test",	echo_.echo("Test"));
    }

}
