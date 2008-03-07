package org.mule.extras.seasar2.sender.file;

import org.mule.extras.seasar2.sender.S2MuleSender;
import org.seasar.extension.unit.S2TestCase;

/**
 * ID:FILE-O-001
 * 
 * プロパティをなにも設定せず、Fileプロバイダを使って
 * メッセージを送信する。
 *
 */
public class NoPropertySendTest extends S2TestCase {
	
	private S2MuleSender sender_;
	
	public void setUp() throws Exception {
		include("NoPropertySendTest.dicon");
	}
	
	public void testDispatch() throws Exception {
		sender_.dispatch("NoPropertySendTest : OK");
	}
	
}
