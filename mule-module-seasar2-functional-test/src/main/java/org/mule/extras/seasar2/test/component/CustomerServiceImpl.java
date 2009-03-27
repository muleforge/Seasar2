/*
 * Copyright (c) Osaka Gas Information System Research Institute Co., Ltd.
 * All rights reserved.  http://www.ogis-ri.co.jp/
 * 
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.extras.seasar2.test.component;

public class CustomerServiceImpl implements CustomerService 
{
    private static Customer[] customers = new Customer[2];
    
    static {
        Customer c1 = new Customer();
        c1.address = "A県";
        c1.age = 19;
        c1.name = "てすお";
        c1.id = 3333;
        
        Customer c2 = new Customer();
        c2.address = "B県";
        c2.id = 1112;
        c2.age = 23;
        c2.name = "てすこ";
        
        customers[1] = c1;
        customers[2] = c2;
    }
    
    public Customer getCustomer(int id)
    {
        return customers[id];
    }
    
    public Customer changeCustomer(Customer before) 
    {
        before.address="どこか";
        return before;
    }
}
