/*
 * Copyright (c) Osaka Gas Information System Research Institute Co., Ltd.
 * All rights reserved.  http://www.ogis-ri.co.jp/
 * 
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.extras.seasar2.receiver.axis;

import org.mule.api.MuleContext;
import org.mule.extras.seasar2.test.component.Customer;
import org.mule.extras.seasar2.test.component.CustomerService;
import org.seasar.extension.unit.S2TestCase;

public class ReceiveBeanTest extends S2TestCase 
{
	private MuleContext muleContext_;
	private CustomerService customerService_;
	
	public ReceiveBeanTest(String name)
	{
		super(name);
	}
	
	public void setUp() throws Exception
	{
		include("ReceiveBeanTest.dicon");
	}
	
	public void testSend() throws Exception
	{
//		 Customer customer = new Customer();
//	        customer.id = 100001;
//	        customer.name = "Test";
//	        customer.age = 25;
//	        customer.sex = Customer.MAN;
//	        customer.address = "++都--区**0-00-000 Aビル 909号";
//	        customer.eMailAddress = "test@test.com";
//	        
//	        String newAddress = customerService_.changeCustomer(customer).address;
//	        
//	        assertEquals("どこか", newAddress);
	        
	        while(true)
	        {
	            
	        }
	        
	}
	
	
}