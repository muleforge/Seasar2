/*
 * Copyright (c) Osaka Gas Information System Research Institute Co., Ltd.
 * All rights reserved.  http://www.ogis-ri.co.jp/
 * 
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.extras.seasar2.test.component;

import org.mule.extras.seasar2.test.component.Echo;

public class EchoUMO implements Echo
{
	public String echo(String str)
	{
		System.out.println("Message:" + str);
		return str;
	}
}
