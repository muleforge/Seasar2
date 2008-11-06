/*
 * Copyright (c) Osaka Gas Information System Research Institute Co., Ltd.
 * All rights reserved.  http://www.ogis-ri.co.jp/
 * 
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.extras.seasar2.receiver.file;

import org.mule.extras.seasar2.receiver.common.TestProperty;
import org.mule.extras.seasar2.sender.S2MuleSender;
import org.seasar.extension.unit.S2TestCase;

public class ReceiveWithPropertyMoveToDirectoryTest extends S2TestCase 
{
	
	private S2MuleSender sender_;
	
	public ReceiveWithPropertyMoveToDirectoryTest(String name)
	{
		super(name);
	}
	
	public void setUp() throws Exception
	{
		include("ReceiveWithPropertyMoveToDirectoryTest.dicon");
	}
	
	public void testReceive() throws Exception
	{
		sender_.dispatch("NoPropertySendTest : OK");		
		Thread.sleep(TestProperty.THREAD_SLEEP_TIME);
	}
	
	
}
