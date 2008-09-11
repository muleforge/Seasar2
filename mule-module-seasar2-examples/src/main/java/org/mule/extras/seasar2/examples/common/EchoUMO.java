package org.mule.extras.seasar2.examples.common;

public class EchoUMO implements Echo
{
	public String echo(String str)
	{
		
		System.out.println("Message:" + str);
		return str;
	}
}
