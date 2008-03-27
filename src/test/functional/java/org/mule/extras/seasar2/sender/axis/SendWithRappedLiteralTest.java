package org.mule.extras.seasar2.sender.axis;

import org.mule.extras.seasar2.sender.common.Customer;
import org.mule.extras.seasar2.sender.common.CustomerService;
import org.mule.extras.seasar2.sender.common.Echo;
import org.seasar.extension.unit.S2TestCase;

/**
 * ID:AXIS-O-003
 * 
 * rapped/literalパターンのSOAPスタイルでメッセージを送信する
 * 
 */
public class SendWithRappedLiteralTest extends S2TestCase 
{

    private CustomerService customerService_;

    public void setUp() throws Exception 
    {
        include("SendWithRappedLiteralTest.dicon");
        customerService_ = (CustomerService) getComponent(CustomerService.class);
    }

    public void testDispatch() throws Exception 
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
