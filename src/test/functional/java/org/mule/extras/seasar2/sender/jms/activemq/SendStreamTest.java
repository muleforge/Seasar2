package org.mule.extras.seasar2.sender.jms.activemq;


import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.mule.extras.seasar2.sender.S2MuleSender;
import org.seasar.extension.unit.S2TestCase;

/**
 * 
 * ID-JMS-O-003
 * 
 * Stream型のメッセージを送信する
 *
 */
public class SendStreamTest extends S2TestCase {
	
	private S2MuleSender sender_;
	
	public void setUp() throws Exception {
		include("NoPropertySendTest.dicon");
	}
	
	public void testDispatch() throws Exception {
		String str = "Send byte: OK";
		ByteArrayInputStream payload = new ByteArrayInputStream(str.getBytes());
		sender_.dispatch(payload);		
	}

}
