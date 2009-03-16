/*
 * Copyright (c) Osaka Gas Information System Research Institute Co., Ltd.
 * All rights reserved.  http://www.ogis-ri.co.jp/
 * 
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.extras.seasar2.connector.impl;

import java.util.Map;

import org.mule.api.MuleException;
import org.mule.extras.seasar2.connector.MessageDispatcher;
import org.mule.extras.seasar2.exception.S2MuleRuntimeException;
import org.mule.module.client.MuleClient;

public class FileMessageDispatcherImpl implements MessageDispatcher 
{

	public void dispache(String uri, Object payload, Map properties,
			MuleClient client) throws MuleException 
	{
		client.dispatch(uri,payload,properties);
	}

	public Object send(String uri, Object payload, Map properties,
			MuleClient client) throws MuleException {
		//同期メッセージ送信はサポートしていません。
		throw new S2MuleRuntimeException(new UnsupportedOperationException());
	}

}
