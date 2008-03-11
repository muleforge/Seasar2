package org.mule.extras.seasar2.sender.axis;

import org.mule.extras.seasar2.sender.axis.Echo;
import org.seasar.extension.unit.S2TestCase;

/**
 * ID:AXIS-O-001
 * 
 * プロパティをなにも設定せず、メッセージを送信する。
 *
 */
public class SendWithNoPropertyTest extends S2TestCase {
	
	private Echo echo_;
	
	public void setUp() throws Exception {
		include("SendWithNoPropertyTest.dicon");
		echo_ = (Echo)getComponent(Echo.class);
	}
	
	public void testDispatch() throws Exception {
		
		assertEquals("SendWithNoPropertyTest : OK", 
				echo_.echo("SendWithNoPropertyTest : OK"));
	}
	
}
