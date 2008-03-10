package org.mule.extras.seasar2.sender.jms.activemq;

import java.util.HashMap;
import java.util.Map;

import org.mule.extras.seasar2.sender.S2MuleSender;
import org.mule.extras.seasar2.sender.common.Customer;
import org.seasar.extension.unit.S2TestCase;

public class SendMapTest extends S2TestCase {
	
	private S2MuleSender sender_;
	
	public void setUp() throws Exception {
		include("SendWithNoPropertyTest.dicon");
	}
	
	public void testDispatch() throws Exception {
		Map payload = new HashMap();
		payload.put("String", "SendMap : OK");
		
		payload.put("Int", 1000);
		
//		Customer customer = new Customer();
//		customer.id = 100001;
//		customer.name = "Test";
//		customer.age = 25;
//		customer.sex = Customer.MAN;
//		customer.address = "++都--区**0-00-000 Aビル 909号";
//		customer.eMailAddress = "test@test.com";
//		
//		payload.put("Object",	customer);
//		
		sender_.dispatch(payload);
	}

}
