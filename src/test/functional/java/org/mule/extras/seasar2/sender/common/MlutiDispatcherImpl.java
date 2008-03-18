package org.mule.extras.seasar2.sender.common;

import org.mule.extras.seasar2.sender.S2MuleSender;
import org.seasar.framework.container.annotation.tiger.Binding;

/**
 * 
 *  {@link MlutiDispatch} の実装クラスです
 *
 */
public class MlutiDispatcherImpl implements MlutiDispatcher {

	@Binding("sender_1")
	private S2MuleSender sender_1;
	
	@Binding("sender_2")
	private S2MuleSender sender_2;
	
	public void mlutiDispatch() {
		
		sender_1.dispatch("Sender_1 : OK");
		
		System.out.println("\n\n Sender_1 : OK \n\n");
		
		sender_2.dispatch("Sender_2 : OK");
		
		System.out.println("\n\n Sender_2 : OK \n\n");
		
	}

	public S2MuleSender getSender_1() {
		return sender_1;
	}

	public void setSender_1(S2MuleSender sender_1) {
		this.sender_1 = sender_1;
	}

	public S2MuleSender getSender_2() {
		return sender_2;
	}

	public void setSender_2(S2MuleSender sender_2) {
		this.sender_2 = sender_2;
	}
	
	

}
