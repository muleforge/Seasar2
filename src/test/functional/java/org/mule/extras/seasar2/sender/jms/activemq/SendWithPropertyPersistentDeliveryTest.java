package org.mule.extras.seasar2.sender.jms.activemq;

import org.mule.extras.seasar2.sender.S2MuleSender;
import org.seasar.extension.unit.S2TestCase;

/**
 * ID-JMS-O-005
 * 
 * プロパティpersistentDeliveryを設定し、メッセージを送信する
 *
 */
public class SendWithPropertyPersistentDeliveryTest extends S2TestCase {
    
    private S2MuleSender sender_;
    
    public void setUp() throws Exception 
    {
        include("SendWithPropertyPersistentDeliveryTest.dicon");
    }
    
    public void testDispatch() throws Exception 
    {
        sender_.dispatch("SendWithPropertyPersistentDeliveryTest.dicon : OK");
    }

}
