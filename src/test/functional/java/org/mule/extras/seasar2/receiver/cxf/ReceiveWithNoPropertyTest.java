package org.mule.extras.seasar2.receiver.cxf;

import org.mule.extras.seasar2.sender.common.Echo;
import org.seasar.extension.unit.S2TestCase;

public class ReceiveWithNoPropertyTest extends S2TestCase 
{
	private Echo echo_;
	
	public ReceiveWithNoPropertyTest(String name)
	{
		super(name);
	}
	
	public void setUp() throws Exception
	{
		include("ReceiveWithNoPropertyTest.dicon");
	}
	
	public void testSend() throws Exception
	{
//		 assertEquals("SendWithNoPropertyTest : OK", echo_
//	                .echo("SendWithNoPropertyTest : OK"));
		
		while (true)
		{
			
		}
	}
	
	
}
