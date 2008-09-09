/*
* テストコメント
*/

package org.mule.extras.seasar2.receiver.cxf;

import javax.jws.WebService;


@WebService(endpointInterface = "org.mule.extras.seasar2.receiver.cxf.HelloWorld",
		serviceName = "HelloWorld")
public class HelloWorldImpl implements HelloWorld 
{

	public String echo(String string) 
	{
		return "Hello " + string;
	}

}
