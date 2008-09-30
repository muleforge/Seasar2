/*
 * Copyright (c) Osaka Gas Information System Research Institute Co., Ltd.
 * All rights reserved.  http://www.ogis-ri.co.jp/
 * 
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.extras.seasar2.receiver.cxf;

import javax.jws.WebService;


@WebService(endpointInterface = "org.mule.extras.seasar2.receiver.cxf.HelloWorld",
		serviceName = "HelloWorld")
public class HelloWorldImpl implements HelloWorld 
{

	public String hello(String str) 
	{
		return "Hello " + str;
	}

}
