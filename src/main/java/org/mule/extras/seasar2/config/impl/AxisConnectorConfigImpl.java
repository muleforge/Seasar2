package org.mule.extras.seasar2.config.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.mule.api.transport.Connector;
import org.mule.extras.seasar2.config.ConnectorConfig;
import org.mule.extras.seasar2.exception.S2MuleConfigurationException;
import org.mule.transport.soap.axis.AxisConnector;
import org.seasar.framework.beans.PropertyNotFoundRuntimeException;

/**
 * AxisConnectorの構成情報を保持するクラスです。
 * 
 * @author Saito_Shinya@ogis-ri.co.jp
 *
 */
public class AxisConnectorConfigImpl extends AbstractConfig implements ConnectorConfig
{
    
    /** ビーンタイプ */
    private List beanTypes;
    
    /**
     * インスタンスを生成する
     */
    public AxisConnectorConfigImpl() {
        
    }
    

    /**
     * @see org.mule.extras.seasar2.config.ConnectorConfig#getConnector()
     */
    public Connector buildConnector()
    {
        AxisConnector connector = new AxisConnector();
        if (beanTypes != null)
        {
            setProperty("beanTypes", beanTypes);
        }
        populate(connector, properties);
        return connector;
    }
    
    /**
     * Axis TypeMappingRegistryに登録する
     * 
     * @param beanType 登録するクラス名
     */
    public void addBeanType(String beanTypeName)
    {
        if (beanTypes == null )
        {
            beanTypes = new ArrayList();
        }
        beanTypes.add(beanTypeName);
    }
    
    @Override
    protected void populate(Object bean, Map properties)
    {
        if ((bean == null) || (properties == null)) {
            return;
        }
        
        BeanUtilsBean beanUtils = BeanUtilsBean.getInstance();
        try
        {
            List removeNames = new ArrayList();
            
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
                    removeNames.add(name);
                }
                else if (!(this instanceof AxisConnectorConfigImpl))
                {
                    throw new PropertyNotFoundRuntimeException(bean.getClass(), name);
                }
            }
            
            for (int i = 0; i < removeNames.size(); i++ ) 
            {
                properties.remove((String) removeNames.get(i));
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
