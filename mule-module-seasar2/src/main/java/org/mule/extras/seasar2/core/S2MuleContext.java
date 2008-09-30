/*
 * Copyright (c) Osaka Gas Information System Research Institute Co., Ltd.
 * All rights reserved.  http://www.ogis-ri.co.jp/
 * 
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.extras.seasar2.core;

import org.mule.api.MuleContext;
import org.mule.api.MuleException;
import org.mule.context.DefaultMuleContextFactory;
import org.mule.extras.seasar2.exception.S2MuleConfigurationException;
import org.mule.extras.seasar2.exception.S2MuleRuntimeException;

/**
 * MuleContextの保持を行うクラス
 * 
 * @author Saito_Shinya@ogis-ri.co.jp
 */
public class S2MuleContext 
{
    /** MuleContext */
    private MuleContext muleContext;
    
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
     * MuleContextを開放する
     */
    public void dispose()
    {
        if (muleContext != null)
        {
            muleContext.dispose();
        }
    }
    
    
    public MuleContext getMuleContext() 
    {
        return muleContext;
    }

}
