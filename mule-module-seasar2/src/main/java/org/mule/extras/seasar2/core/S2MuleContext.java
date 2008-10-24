/*
 * Copyright (c) Osaka Gas Information System Research Institute Co., Ltd.
 * All rights reserved.  http://www.ogis-ri.co.jp/
 * 
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.extras.seasar2.core;

import java.util.List;

import org.mule.api.MuleContext;
import org.mule.api.MuleException;
import org.mule.context.DefaultMuleContextFactory;
import org.mule.extras.seasar2.connector.ConnectorConfig;
import org.mule.extras.seasar2.exception.S2MuleRuntimeException;
import org.mule.extras.seasar2.util.S2MuleComponentUtil;
import org.seasar.framework.container.S2Container;
import org.springframework.jmx.support.ConnectorServerFactoryBean;

/**
 * MuleContextの保持を行うクラス
 * 
 * @author Saito_Shinya@ogis-ri.co.jp
 */
public class S2MuleContext 
{
    /** MuleContext */
    private MuleContext muleContext;
    
    private S2Container container;
    
    /**
     * MuleContextを作成する
     */
    public void createMuleContext() 
    {
        try
        {
            DefaultMuleContextFactory muleContextFactory
                = new DefaultMuleContextFactory();
            muleContext = muleContextFactory.createMuleContext();
        }
        catch (MuleException e) 
        {
            throw new S2MuleRuntimeException("ESML0000", new Object[]{e}, e);
        }
    }

    /**
     * 初期化処理
     */
    public void init()
    {
        
        try
        {
          //MuleContextの作成
            DefaultMuleContextFactory muleContextFactory
                = new DefaultMuleContextFactory();
            muleContext = muleContextFactory.createMuleContext();
            
           //Connectorの登録
            List connectors = S2MuleComponentUtil.getConnectorConfigs(container.getRoot());
            if (connectors != null)
            {
                for (int i = 0; i < connectors.size(); i++)
                {
                    ConnectorConfig connectorConfig 
                        = (ConnectorConfig) connectors.get(i);
                    muleContext.getRegistry()
                        .registerConnector(connectorConfig.buildConnector());
                }
            }    
            
        }
        catch (MuleException e) 
        {
            throw new S2MuleRuntimeException("ESML0000", new Object[]{e}, e);
        }
    }
    
    /**
     * MuleContextを開放する
     */
    public void dispose()
    {
        if (muleContext != null)
        {
            muleContext.dispose();
        }
    }
    
    public void setContainer(S2Container container)
    {
        this.container = container;
    }

    public MuleContext getMuleContext() 
    {
        return muleContext;
    }

}
