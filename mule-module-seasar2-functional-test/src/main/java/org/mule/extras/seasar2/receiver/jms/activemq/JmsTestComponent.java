/*
 * Copyright (c) Osaka Gas Information System Research Institute Co., Ltd.
 * All rights reserved.  http://www.ogis-ri.co.jp/
 * 
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
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
