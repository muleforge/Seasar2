package org.mule.extras.seasar2.sender.jms.activemq;


import org.mule.extras.seasar2.sender.S2MuleSender;
import org.seasar.extension.unit.S2TestCase;

/**
 * ID-JMS-O-002
 * 
 * byte配列型のメッセージを送信する
 *
 */
public class SendByteTest extends S2TestCase {
	
	private S2MuleSender sender_;
	
	public void setUp() throws Exception {
		include("NoPropertySendTest.dicon");
	}
	
	public void testDispatch() throws Exception {
		String str = "Send byte: OK";
		byte[] payload = str.getBytes();
		sender_.dispatch(payload);		
	}

}
