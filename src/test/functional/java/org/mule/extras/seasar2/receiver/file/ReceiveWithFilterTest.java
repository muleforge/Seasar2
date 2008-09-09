package org.mule.extras.seasar2.receiver.file;

import org.mule.extras.seasar2.receiver.common.TestProperty;
import org.seasar.extension.unit.S2TestCase;

public class ReceiveWithFilterTest extends S2TestCase 
{
	public ReceiveWithFilterTest(String name)
	{
		super(name);
	}
	
	public void setUp() throws Exception
	{
		include("ReceiveFilterTest.dicon");
	}
	
	public void testConfigure() throws Exception
	{
		Thread.sleep(TestProperty.THREAD_SLEEP_TIME);
	}
	
	
}
