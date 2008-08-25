package org.mule.extras.seasar2.receiver.file;

import java.io.File;

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
		
		File file = new File("C:/temp/afterFile/Test-処理済み");
		assertTrue(file.isFile());
	}
	
	
}
