/*
 * Copyright (c) Osaka Gas Information System Research Institute Co., Ltd.
 * All rights reserved.  http://www.ogis-ri.co.jp/
 * 
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.extras.seasar2.connector;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.mule.api.MuleContext;
import org.mule.api.MuleException;
import org.mule.api.transport.Connector;
import org.mule.extras.seasar2.exception.S2MuleConfigurationException;
import org.mule.extras.seasar2.exception.S2MuleRuntimeException;
import org.seasar.framework.beans.PropertyNotFoundRuntimeException;
import org.seasar.framework.beans.util.BeanUtil;

/**
 * ConnectorConfigの抽象クラス
 * 
 * @author Shinya_Saito@ogis-ri.co.jp
 *
 */
public abstract class AbstractConnector implements ConnectorConfig
{
    /** トランザクション設定を行っときのレシーバの数*/
    protected volatile int numberOfConcurrentTransactedReceivers 
        = org.mule.transport.AbstractConnector.DEFAULT_NUM_CONCURRENT_TX_RECEIVERS;
    
    /** トランザクション */
    protected boolean transacted = false;
    
    /** Connectorのプロパティ */
    protected Map properties = new HashMap();
    
    /** 名前 */
    protected String name;
    
    /**
     * プロパティを設定する
     * @param key プロパティの名前
     * @param value プロパティの値
     */
    public void setProperty(String key, Object value)
    {
        properties.put(key, value);
    }
    
    /**
     * プロパティの値を取得する
     * @param key プロパティの名前
     * @return プロパティの値
     */
    public Object getProperty(String key)
    {
        return properties.get(key);
    }
    
    
    /**
     * プロパティを取得する
     * @return プロパティのMap
     */
    public Map getProperties()
    {
        return properties;
    }
    
	public int getNumberOfConcurrentTransactedReceivers() 
    {
        return numberOfConcurrentTransactedReceivers;
    }

    public void setNumberOfConcurrentTransactedReceivers(
            int numberOfConcurrentTransactedReceivers) 
    {
        this.numberOfConcurrentTransactedReceivers = numberOfConcurrentTransactedReceivers;
    }

    public boolean isTransacted() 
    {
        return transacted;
    }

    public void setTransacted(boolean transacted) 
    {
        this.transacted = transacted;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getName()
    {
        return name;
    }
       
}
