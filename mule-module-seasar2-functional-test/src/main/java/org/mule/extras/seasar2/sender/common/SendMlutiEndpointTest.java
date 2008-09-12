package org.mule.extras.seasar2.sender.common;

import org.mule.extras.seasar2.sender.S2MuleSender;
import org.seasar.extension.unit.S2TestCase;

/**
 * ID-COMMON-O-001
 *複数のエンドポイントにメッセージを送信する
 *
 */
public class SendMlutiEndpointTest extends S2TestCase {
    
    private S2MuleSender sender_;
    
    public void setUp() throws Exception 
    {
        include("SendMlutiEndpointTest.dicon");
    }
    
    public void testMlutiDispatch() throws Exception 
    {
    	sender_.dispatch("MlutiEndpointTest : OK");
    }
}
