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
import org.mule.extras.seasar2.exception.S2MuleConfigurationException;
import org.seasar.framework.beans.PropertyNotFoundRuntimeException;
import org.seasar.framework.beans.util.BeanUtil;

/**
 * Configの抽象クラスです
 * 
 * @author Shinya_Saito@ogis-ri.co.jp
 *
 */
public abstract class AbstractConnector implements ConnectorConfig
{
    
	protected volatile int numberOfConcurrentTransactedReceivers 
	 	= org.mule.transport.AbstractConnector.DEFAULT_NUM_CONCURRENT_TX_RECEIVERS;
	
	protected boolean transacted = false;
	 
    /** Connector�のプロパティ */
    protected Map properties = new HashMap();
    
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
   
    /**
     * S2MuleのラッパークラスのプロパティをMuleのコンポーネントのプロパティ
     * にコピーします。
     * 
     * 
     * @param properties プロパティ
     * @param src コピー元のbean
     * @param bean プロパティを設定するオブジェクト
     */
    /*
    protected void populate(Map properties,Object src, Object bean)
    {
        if ((bean == null) || (properties == null)) {
            return;
        }
        BeanUtil.copyProperties(src, bean);
        BeanUtil.copyProperties(properties, bean);
        
        
        BeanUtilsBean beanUtils = BeanUtilsBean.getInstance();
        try
        {
            
            for (Iterator names = properties.entrySet().iterator();names.hasNext();)
            {

            	Map.Entry entry = (Map.Entry) names.next();
                String name = (String)entry.getKey();
                if (name == null) 
                {
                    continue;
                }
                Object value = (Object)entry.getValue();
                
                //diconに記述されたプロパティが存在するかチェック
                if (beanUtils.getPropertyUtils().getPropertyDescriptor(bean, name) != null)
                {
                    beanUtils.setProperty(bean, name, value);
            
                }
                else
                {
                    throw new PropertyNotFoundRuntimeException(bean.getClass(), name);
                }
            }
            
        }
        catch (PropertyNotFoundRuntimeException e)
        {
            throw e;
        }
        catch (Exception e)
        {
            throw new S2MuleConfigurationException("ESML0000", new Object[]{e}, e);
        } 
    }
    */
    
}
