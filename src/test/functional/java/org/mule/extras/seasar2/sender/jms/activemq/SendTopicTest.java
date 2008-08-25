package org.mule.extras.seasar2.sender.jms.activemq;

import org.mule.extras.seasar2.sender.S2MuleSender;
import org.seasar.extension.unit.S2TestCase;

/**
 * ID-JMS-O-008
 * 
 * topicにメッセージを送信する
 *
 */
public class SendTopicTest extends S2TestCase 
{
    
    private S2MuleSender sender_;
    
    public void setUp() throws Exception 
    {
        include("SendTopicTest.dicon");
    }
    
    public void testDispatch() throws Exception 
    {
        sender_.dispatch("SendTopic : OK");
    }

}
