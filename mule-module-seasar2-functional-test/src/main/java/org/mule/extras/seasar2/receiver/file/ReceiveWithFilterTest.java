/*
 * Copyright (c) Osaka Gas Information System Research Institute Co., Ltd.
 * All rights reserved.  http://www.ogis-ri.co.jp/
 * 
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.extras.seasar2.receiver.file;

import org.mule.extras.seasar2.test.component.TestProperty;
import org.seasar.extension.unit.S2TestCase;

public class ReceiveWithFilterTest extends S2TestCase 
{
	public ReceiveWithFilterTest(String name)
	{
		super(name);
	}
	
	public void setUp() throws Exception
	{
		include("ReceiveWithFilterTest.dicon");
	}
	
	public void testConfigure() throws Exception
	{
		Thread.sleep(TestProperty.THREAD_SLEEP_TIME);
	}
	
	
}
