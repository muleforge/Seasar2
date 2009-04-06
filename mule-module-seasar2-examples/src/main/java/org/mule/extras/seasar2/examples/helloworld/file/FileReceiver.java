/*
 * Copyright (c) Osaka Gas Information System Research Institute Co., Ltd.
 * All rights reserved.  http://www.ogis-ri.co.jp/
 * 
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.extras.seasar2.examples.helloworld.file;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.S2ContainerFactory;
import org.seasar.framework.exception.ResourceNotFoundRuntimeException;

public class FileReceiver 
{
	private static final String CONFIGURE_PATH
		= "helloworld-receive-file.dicon";
	
	public static void main(String[] args)
	{
		try
		{
			S2Container container = S2ContainerFactory.create(CONFIGURE_PATH);
			container.init();
		} 
		catch (ResourceNotFoundRuntimeException e)
        {
            System.out.println("dicon ファイルが見つかりません： " + CONFIGURE_PATH);
        }
	}
	
}
