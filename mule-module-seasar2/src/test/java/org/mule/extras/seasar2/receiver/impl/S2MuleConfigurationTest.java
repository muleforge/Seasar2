/*
 * Copyright (c) Osaka Gas Information System Research Institute Co., Ltd.
 * All rights reserved.  http://www.ogis-ri.co.jp/
 * 
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.extras.seasar2.receiver.impl;

import org.seasar.extension.unit.S2TestCase;

public class S2MuleConfigurationTest extends S2TestCase
{
	private S2MuleConfiguration config_;
	
	public S2MuleConfigurationTest(String name)
	{
		super(name);
	}
	
	public void setUp() throws Exception
	{
		include("S2MuleConfigurationTest.dicon");
	}
	
	public void testGetName() throws Exception
	{
		assertEquals("Echo", config_.getName());
	}
}
