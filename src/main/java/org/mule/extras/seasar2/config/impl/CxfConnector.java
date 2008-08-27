package org.mule.extras.seasar2.config.impl;

import org.mule.api.transport.Connector;
import org.mule.extras.seasar2.config.AbstractConnector;

public class CxfConnector extends AbstractConnector 
{

	public Connector buildConnector() 
	{
		org.mule.transport.cxf.CxfConnector connector
			= new org.mule.transport.cxf.CxfConnector();
		return null;
	}

}
