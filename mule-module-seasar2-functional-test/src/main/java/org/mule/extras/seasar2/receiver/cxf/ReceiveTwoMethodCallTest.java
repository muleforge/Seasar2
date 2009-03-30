/*
 * Copyright (c) Osaka Gas Information System Research Institute Co., Ltd.
 * All rights reserved.  http://www.ogis-ri.co.jp/
 * 
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.extras.seasar2.receiver.cxf;

import org.mule.extras.seasar2.test.component.CustomerService;
import org.seasar.extension.unit.S2TestCase;

public class ReceiveTwoMethodCallTest extends S2TestCase 
{
	private CustomerService service_;
	
	public ReceiveTwoMethodCallTest(String name)
	{
		super(name);
	}
	
	public void setUp() throws Exception
	{
		include("ReceiveWithNoPropertyTest.dicon");
	}
	
	public void testSend() throws Exception
	{	
		while (true)
		{
			
		}
	}
	
	
}
