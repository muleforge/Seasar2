/*
* テストコメント
*/

package org.mule.extras.seasar2.endpoint.impl;

import org.mule.extras.seasar2.endpoint.AbstractEndpoint;

public class FileEndpoint extends AbstractEndpoint {

	public static final String SCHEME = "file";
	
	public FileEndpoint()
	{
		
	}
	
	public FileEndpoint(String uri)
	{
		super(uri);
	}
	
	public String getUriScheme() {
		return SCHEME;
	}

}
