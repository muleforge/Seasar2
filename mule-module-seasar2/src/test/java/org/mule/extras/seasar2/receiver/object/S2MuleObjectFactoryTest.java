package org.mule.extras.seasar2.receiver.object;

import org.mule.extras.seasar2.receiver.impl.Echo;
import org.seasar.extension.unit.S2TestCase;

public class S2MuleObjectFactoryTest extends S2TestCase
{
	S2MuleObjectFactory factory_;
	
	public S2MuleObjectFactoryTest(String name)
	{
		super(name);
	}
	
	public void setUp() throws Exception
	{
		include("S2MuleObjectFactoryTest.dicon");
	}
	
	public void testGetInstance() throws Exception
	{
		assertEquals(new Echo().getClass(), factory_.getInstance().getClass());
	}
	
	public void testIsSingleton() throws Exception
	{
		assertTrue(factory_.isSingleton());
	}
	
	
	
}
