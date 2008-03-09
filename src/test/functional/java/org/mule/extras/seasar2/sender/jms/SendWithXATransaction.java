package org.mule.extras.seasar2.sender.jms;

import org.seasar.extension.unit.S2TestCase;

/**
 * ID-JMS-O-005
 * XA Transactionalなメッセージを送信する
 *
 */
public class SendWithXATransaction extends S2TestCase {
	
	private MlutiDispatcher dispatcher_;
	
	public void setUp() throws Exception {
		include("SendWithXATransaction.dicon");
	}
	
	public void testMlutiDispatch() throws Exception {
		dispatcher_.mlutiDispatch();
	}
}
