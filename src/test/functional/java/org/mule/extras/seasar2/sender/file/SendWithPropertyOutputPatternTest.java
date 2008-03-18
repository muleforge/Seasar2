package org.mule.extras.seasar2.sender.file;

import org.mule.extras.seasar2.sender.S2MuleSender;
import org.seasar.extension.unit.S2TestCase;

/**
 * ID:FILE-O-002
 * 
 * プロパティ outputPatternを設定し、Fileプロバイダを使って
 * メッセージを送信する。
 *
 */
public class SendWithPropertyOutputPatternTest extends S2TestCase {
	
	private S2MuleSender sender_;
	
	public void setUp() throws Exception {
		include("SendWithPropertyOutputPatternTest.dicon");
	}
	
	public void testDispatch() throws Exception {
		sender_.dispatch("SendWithPropertyOutputPatternTest : OK");
	}
	
}
