package org.mule.extras.seasar2.sender.jms;

import org.mule.extras.seasar2.sender.S2MuleSender;
import org.seasar.framework.container.annotation.tiger.Binding;

public class MlutiDispatcherImpl implements MlutiDispatcher {

	@Binding("sender_1")
	private S2MuleSender sender_1;
	
	@Binding("sender_2")
	private S2MuleSender sender_2;
	
	public void mlutiDispatch() {
		sender_1.dispatch("Sender_1 : OK");
		sender_2.dispatch("Sender_2 : OK");
	}

}
