package org.mule.extras.seasar2.sender.jms.activemq;

import org.mule.extras.seasar2.sender.S2MuleSender;
import org.seasar.extension.unit.S2TestCase;

/**
 * ID-JMS-O-009
 * S2Txの設定をせずに、XA Transactionを実行する
 *
 */
public class SendWithXATransactionWithoutS2TxTest extends S2TestCase {
	
	private S2MuleSender sender_;
	
	public void setUp() throws Exception {
		include("SendWithXATransactionWithoutS2TxTest.dicon");
	}
	
	public void testMlutiDispatch() throws Exception {
		try {
		 sender_.dispatch("SendWithXATransactionWithoutS2TxTest : OK");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
