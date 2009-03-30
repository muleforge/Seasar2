/*
 * Copyright (c) Osaka Gas Information System Research Institute Co., Ltd.
 * All rights reserved.  http://www.ogis-ri.co.jp/
 * 
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.extras.seasar2.sender.jms.activemq;

import org.mule.tck.FunctionalTestCase;

public class ReplyToTestServer extends FunctionalTestCase {

	@Override
	protected String getConfigResources() 
	{
		return "org/mule/extras/seasar2/sender/jms/activemq/jmsEcho.xml";
	}

	
	public void testServer()
	{
		while(true)
		{
			
		}
	}
	
}
