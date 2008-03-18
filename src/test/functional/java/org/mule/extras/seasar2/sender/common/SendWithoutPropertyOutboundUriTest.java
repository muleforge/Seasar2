package org.mule.extras.seasar2.sender.common;

import org.mule.extras.seasar2.exception.S2MuleConfigurationException;
import org.mule.extras.seasar2.sender.S2MuleSender;
import org.mule.extras.seasar2.sender.common.Customer;
import org.seasar.extension.unit.S2TestCase;

/**
 * ID-COMMON-E-001
 * 
 * Outbound Uriを設定せずにメッセージを送信する
 *
 */
public class SendWithoutPropertyOutboundUriTest extends S2TestCase {
	
	private S2MuleSender sender_;
	
	public void setUp() throws Exception {
		include("SendWithoutPropertyOutboundUriTest.dicon");
	}
	
	public void testDispatch() throws Exception {
		try {
			sender_.dispatch("SendWithoutPropertyOutboundUriTest : OK");
		} catch (S2MuleConfigurationException e) {
			e.printStackTrace();
		}
	}
	
}
