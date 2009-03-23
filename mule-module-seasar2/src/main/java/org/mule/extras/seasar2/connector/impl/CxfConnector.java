/*
 * Copyright (c) Osaka Gas Information System Research Institute Co., Ltd.
 * All rights reserved.  http://www.ogis-ri.co.jp/
 * 
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.extras.seasar2.connector.impl;

import org.mule.api.transport.Connector;
import org.mule.extras.seasar2.connector.AbstractConnector;
import org.mule.extras.seasar2.connector.MessageDispatcher;
import org.mule.util.ObjectNameHelper;

/**
 * org.mule.transport.cxf.CxfConnectorの構成情報を保持するクラスです。
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
    
    /*
     * @see org.mule.extras.seasar2.connector.ConnectorConfig#getMessageDispatcher()
     */
    public MessageDispatcher getMessageDispatcher()
    {
        if (messageDispatcher == null)
        {
            messageDispatcher = new DefaultMessageDispatcherImpl();
        }
        
        return messageDispatcher;
    }

}
