/*
 * Copyright (c) Osaka Gas Information System Research Institute Co., Ltd.
 * All rights reserved.  http://www.ogis-ri.co.jp/
 * 
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.extras.seasar2.test.component;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
public interface CustomerService 
{
    @WebResult(name="customer")
    Customer getCustomer(@WebParam(name="id")int id);
    
    @WebResult(name="changedCustomer")
    Customer changeCustomer(@WebParam(name="before")Customer before);
}
