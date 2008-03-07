package org.mule.extras.seasar2.sender.file;

import org.mule.extras.seasar2.sender.S2MuleSender;
import org.seasar.extension.unit.S2TestCase;

public class SendWithTransformer extends S2TestCase {

	private S2MuleSender sender_;
	
	public void setUp() throws Exception {
		include("SendWithTransformerTest.dicon");
	}
	
	public void testDispatch() throws Exception {
		Customer customer = new Customer();
		customer.id = 100001;
		customer.name = "Test";
		customer.age = 25;
		customer.sex = Customer.MAN;
		customer.address = "++都--区**0-00-000 Aビル 909号";
		customer.eMailAddress = "test@test.com";
		
		sender_.dispatch(customer);
	}
}
