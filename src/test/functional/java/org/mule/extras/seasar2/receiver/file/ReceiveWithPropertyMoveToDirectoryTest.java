package org.mule.extras.seasar2.receiver.file;

import java.io.File;

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
