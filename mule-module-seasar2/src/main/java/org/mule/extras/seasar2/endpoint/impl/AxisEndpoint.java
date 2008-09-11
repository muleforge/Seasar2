/*
* テストコメント
*/

package org.mule.extras.seasar2.endpoint.impl;

import org.mule.extras.seasar2.endpoint.AbstractEndpoint;
import org.mule.transport.soap.axis.AxisConnector;

public class AxisEndpoint extends AbstractEndpoint {

	public static final String SCHEME = "axis";
	
	public AxisEndpoint()
	{
		
	}
	
	public AxisEndpoint(String uri)
	{
		super(uri);
	}
	
	public String getUriScheme() 
	{
		return SCHEME;
	}
	
	public void setStyle(String style)
	{
		setProperty(AxisConnector.STYLE, style);
	}
	
	public void setUse(String use)
	{
		setProperty(AxisConnector.USE, use);
	}
}
