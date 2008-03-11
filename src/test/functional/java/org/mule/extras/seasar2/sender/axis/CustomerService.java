package org.mule.extras.seasar2.sender.axis;

import org.mule.extras.seasar2.sender.common.Customer;

public interface CustomerService {
	Customer changeCustomer(Customer before);
}
