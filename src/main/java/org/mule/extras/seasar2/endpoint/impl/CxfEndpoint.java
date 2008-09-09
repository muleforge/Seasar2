/*
* テストコメント
*/

package org.mule.extras.seasar2.endpoint.impl;

import org.mule.extras.seasar2.endpoint.AbstractEndpoint;

public class CxfEndpoint extends AbstractEndpoint 
{
	public static final String SCHEME = "cxf";
	
	public CxfEndpoint()
	{
		
	}
	
	public CxfEndpoint(String uri)
	{
		super(uri);
	}

	public String getUriScheme() 
	{
		return SCHEME;
	}

}
