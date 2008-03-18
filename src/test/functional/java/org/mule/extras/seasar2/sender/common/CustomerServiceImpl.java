package org.mule.extras.seasar2.sender.common;

public class CustomerServiceImpl implements CustomerService {
	
	public Customer changeCustomer(Customer before) {
		before.address="どこか";
		return before;
	}
}
