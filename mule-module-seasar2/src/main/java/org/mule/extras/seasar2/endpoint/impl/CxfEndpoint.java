/*
* テストコメント
*/

package org.mule.extras.seasar2.endpoint.impl;

import org.mule.extras.seasar2.endpoint.AbstractEndpoint;
import org.mule.transport.cxf.CxfConstants;

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

	public void setClientClass(String clientClass)
	{
		setProperty(CxfConstants.CLIENT_CLASS, clientClass);
	}
	
	public void setWsdlPort(String wsdlPort)
	{
		setProperty(CxfConstants.CLIENT_PORT,wsdlPort);
	}
	
	public void setWsdlLocation(String wsdlLocation)
	{
		setProperty(CxfConstants.WSDL_LOCATION,wsdlLocation);
	}
	
	public void setOperation(String operation)
	{
		setProperty(CxfConstants.OPERATION,operation);
	}
	

}
