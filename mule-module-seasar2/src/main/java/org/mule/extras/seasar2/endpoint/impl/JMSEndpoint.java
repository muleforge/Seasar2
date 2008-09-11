/*
* テストコメント
*/

package org.mule.extras.seasar2.endpoint.impl;

import org.mule.extras.seasar2.endpoint.AbstractEndpoint;
import org.mule.transport.jms.JmsConstants;

public class JMSEndpoint extends AbstractEndpoint {
	
	public static final String SCHEME = "jms";
	
	public JMSEndpoint()
	{
		
	}
	
	public JMSEndpoint(String uri)
	{
		super(uri);
	}
	
	public String getUriScheme() 
	{
		return SCHEME;
	}

	public void setJMSReplyTo(String replyTo) 
	{
		setProperty(JmsConstants.JMS_REPLY_TO, replyTo);
	}
}