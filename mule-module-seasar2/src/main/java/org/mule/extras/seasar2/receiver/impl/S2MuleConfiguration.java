/*
 * Copyright (c) Osaka Gas Information System Research Institute Co., Ltd.
 * All rights reserved.  http://www.ogis-ri.co.jp/
 * 
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.extras.seasar2.receiver.impl;

import java.util.ArrayList;
import java.util.List;

import org.mule.extras.seasar2.endpoint.EndpointConfig;
import org.mule.extras.seasar2.endpoint.impl.EndpointConfigFactoryImpl;
import org.mule.api.MuleException;
import org.mule.extras.seasar2.endpoint.EndpointConfigFactory;
import org.seasar.framework.util.ClassUtil;

/**
 * Muleの構成情報を保持するクラスです
 * 
 * @author Saito_Shinya@ogis-ri.co.jp
 *
 */
public class S2MuleConfiguration 
{
    
    /**
     * 複数のinboundのEndpoint
     */
    private List inboundEndpoints = new ArrayList();
    
    
    /**
     * Messageを受け取るクラス
     */
    private Object umoImpl;
    
    /**
     * MuleDesciptorの名前になる
     */
    private String name;
    
    /**
     * デフォルトコンストラクタ
     * @throws UMOException
     */
    public S2MuleConfiguration() throws MuleException 
    {
        
    }
        
    /**
     * InboundEndpointを追加する
     * @param endpoint 
     */
    public void addInboundEndpoint(EndpointConfig endpoint)
    {
        inboundEndpoints.add(endpoint);
    }
    
    /**
     * InboudEndpointを追加する
     * @return endpointUri
     */
    public void addInboundEndpoint(String endpointUri)
    {
        EndpointConfigFactory factory
            = new EndpointConfigFactoryImpl(endpointUri);
        inboundEndpoints.add(factory.createEndpoint());
    }
    
    
    public Object getUmoImpl() 
    {
        return umoImpl;
    }

    public void setUmoImpl(Object umoImpl) 
    {
        this.umoImpl = umoImpl;
    }

    public String getName() 
    {
        if (name == null && umoImpl != null) 
        {
            return ClassUtil.getShortClassName(umoImpl.getClass());
        } 
        else 
        {
            return name;
        } 
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public List getInboundEndpoints() 
    {
        return inboundEndpoints;
    }
}
