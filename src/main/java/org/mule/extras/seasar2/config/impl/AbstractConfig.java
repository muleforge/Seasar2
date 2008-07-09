package org.mule.extras.seasar2.config.impl;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.mule.extras.seasar2.exception.S2MuleConfigurationException;
import org.seasar.framework.beans.PropertyNotFoundRuntimeException;

/**
 * Configの抽象クラスです
 * 
 * @author Shinya_Saito@ogis-ri.co.jp
 *
 */
public abstract class AbstractConfig {
    
    /** Connector�のプロパティ */
    protected Map properties = new HashMap();
    
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
    
    /**
     * {@link org.apache.commons.beanutils.BeanUtilsBean#populate(Object, Map)}をラップしたメソッド
     * 
     * @param bean プロパティを設定するオブジェクト
     * @param properties プロパティ
     */
    protected void populate(Object bean, Map properties)
    {
        if ((bean == null) || (properties == null)) {
            return;
        }
        
        BeanUtilsBean beanUtils = BeanUtilsBean.getInstance();
        try
        {
            
            for (Iterator names = properties.entrySet().iterator();names.hasNext();)
            {

                // Identify the property name and value(s) to be assigned
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
    
}
