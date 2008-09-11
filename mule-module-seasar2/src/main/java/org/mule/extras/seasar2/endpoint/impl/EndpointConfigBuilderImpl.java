/*
* テストコメント
*/

package org.mule.extras.seasar2.endpoint.impl;

import org.mule.extras.seasar2.endpoint.EndpointConfig;
import org.mule.extras.seasar2.endpoint.EndpointConfigBuilder;
import org.mule.extras.seasar2.exception.S2MuleConfigurationException;

public class EndpointConfigBuilderImpl implements EndpointConfigBuilder 
{
	
	private String uri;

	public EndpointConfigBuilderImpl(String uri)
	{
		this.uri=uri;
	}
	
	/**
	 * @see org.mule.extras.seasar2.endpoint.EndpointConfigBuilder#build(String)
	 */
	public EndpointConfig build()
	{
		String scheme = getUriScheme(uri);
		
		if(scheme.equals(FileEndpoint.SCHEME))
		{
			return new FileEndpoint(uri);
		}
		else if(scheme.equals(JMSEndpoint.SCHEME))
		{
			return new JMSEndpoint(uri);
		}
		else if(scheme.equals(AxisEndpoint.SCHEME))
		{
			return new AxisEndpoint(uri);
		}
		else if(scheme.equals(CxfEndpoint.SCHEME))
		{
			return new CxfEndpoint(uri);
		}
		return null;
	}
	
	private String getUriScheme(String uri)
	{
		int index = uri.indexOf(":");
		if (index == -1 )
		{
			throw new S2MuleConfigurationException("EMSL0006" ,new Object[]{uri});
		}
		return uri.substring(0,index);
	}

}
