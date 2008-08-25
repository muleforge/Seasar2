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
