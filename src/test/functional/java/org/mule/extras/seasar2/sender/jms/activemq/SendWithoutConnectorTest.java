package org.mule.extras.seasar2.sender.jms.activemq;

import org.mule.extras.seasar2.sender.S2MuleSender;
import org.seasar.extension.unit.S2TestCase;

/**
 * ID-JMS-E-002
 * 
 * Connectorの設定をせずに、メッセージを送信する
 *
 */
public class SendWithoutConnectorTest extends S2TestCase {
	
	private S2MuleSender sender_;
	
	public void setUp() throws Exception {
		include("SendWithoutConnectorTest.dicon");
	}
	
	public void testDispatch() throws Exception {
		try {
			sender_.dispatch("SendWithoutConnector : OK");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
