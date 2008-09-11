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
