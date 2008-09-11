package org.mule.extras.seasar2.connector.impl;

import org.mule.api.transport.Connector;
import org.mule.extras.seasar2.connector.AbstractConnector;
import org.mule.util.ObjectNameHelper;

/**
 * CxfConnectorの構成情報を保持するクラスです。
 * 
 * @author Saito_Shinya@ogis-ri.co.jp
 *
 */
public class CxfConnector extends AbstractConnector 
{

	/**
     * @see org.mule.extras.seasar2.connector.ConnectorConfig#getConnector()
     */
	public Connector buildConnector() 
	{
		org.mule.transport.cxf.CxfConnector connector
			= new org.mule.transport.cxf.CxfConnector();
		setName(ObjectNameHelper.getConnectorName(connector));
		return connector;
	}

}
