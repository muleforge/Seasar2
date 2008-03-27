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
