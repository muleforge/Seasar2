/*
 * Copyright (c) Osaka Gas Information System Research Institute Co., Ltd.
 * All rights reserved.  http://www.ogis-ri.co.jp/
 * 
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.extras.seasar2.endpoint.impl;

import org.mule.extras.seasar2.connector.MessageDispatcher;
import org.mule.extras.seasar2.connector.impl.CXFMessageDispatcherImpl;
import org.mule.extras.seasar2.endpoint.AbstractEndpoint;
import org.mule.transport.cxf.CxfConstants;

/**
 * CxfEndpointの構成情報を保持するクラス
 * 
 * @author Saito_Shinya@ogis-ri.co.jp
 *
 */
public class CxfEndpoint extends AbstractEndpoint 
{
    /** CxfEndpointのスキーム*/
    public static final String SCHEME = "cxf";
    
    /**
     * インスタンスを作成する
     */
    public CxfEndpoint()
    {
        //blank
    }
    
    /**
     * インスタンスを作成する
     * 
     * @param uri
     */
    public CxfEndpoint(String uri)
    {
        super(uri);
    }

    @Override
    protected MessageDispatcher createMessageDispatcher()
    {
        return new CXFMessageDispatcherImpl();
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
        setProperty(CxfConstants.CLIENT_PORT, wsdlPort);
    }
    
    public void setWsdlLocation(String wsdlLocation)
    {
        setProperty(CxfConstants.WSDL_LOCATION, wsdlLocation);
    }
    
    public void setOperation(String operation)
    {
        setProperty(CxfConstants.OPERATION, operation);
    }

}
