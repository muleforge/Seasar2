package org.mule.extras.seasar2.receiver.jms.activemq;

import java.util.Map;

import com.sun.xml.bind.v2.schemagen.xmlschema.List;

public class JmsTestComponent 
{
	public void echoString(String str)
	{
		System.out.println("Receive String:" + str);
	}
	
	public void echoByte(byte[] bytes)
	{
		System.out.println("Receive byte[]:" + bytes);
	}
	
	public void echoMap(Map map)
	{
		System.out.println("Receive Map:" + map);
	}
}
