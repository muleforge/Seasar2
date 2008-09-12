package org.mule.extras.seasar2.receiver.axis;

import org.mule.extras.seasar2.sender.common.Customer;
import org.mule.extras.seasar2.sender.common.CustomerService;
import org.seasar.extension.unit.S2TestCase;

public class ReceiveWithRappedLiteralTest extends S2TestCase 
{
	
	private CustomerService customerService_;
	
	public ReceiveWithRappedLiteralTest(String name)
	{
		super(name);
	}
	
	public void setUp() throws Exception
	{
		include("ReceiveWithRappedLiteralTest.dicon");
	}
	
	public void testSend() throws Exception
	{
		Customer customer = new Customer();
        customer.id = 100001;
        customer.name = "Test";
        customer.age = 25;
        customer.sex = Customer.MAN;
        customer.address = "++都--区**0-00-000 Aビル 909号";
        customer.eMailAddress = "test@test.com";

        String newAddress = customerService_.changeCustomer(customer).address;

        assertEquals("どこか", newAddress);
	}
	
	
}