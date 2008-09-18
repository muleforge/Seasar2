/*
 * Copyright (c) Osaka Gas Information System Research Institute Co., Ltd.
 * All rights reserved.  http://www.ogis-ri.co.jp/
 * 
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.extras.seasar2.sender.file;

import org.mule.extras.seasar2.sender.S2MuleSender;
import org.mule.extras.seasar2.sender.common.Customer;
import org.seasar.extension.unit.S2TestCase;

/**
 * ID-FILE-O-004
 * 
 * オブジェクト型のメッセージを送信する
 *
 */
public class SendBeanTest extends S2TestCase 
{
    
    private S2MuleSender sender_;
    
    public void setUp() throws Exception 
    {
        include("SendBeanTest.dicon");
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
        
        sender_.dispatch(customer);
    }
    
}
