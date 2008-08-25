package org.mule.extras.seasar2.config.impl;

import java.util.ArrayList;
import java.util.List;

import org.mule.api.transport.Connector;
import org.mule.extras.seasar2.config.AbstractConnector;
import org.seasar.framework.beans.util.BeanUtil;

/**
 * AxisConnectorの構成情報を保持するクラスです。
 * 
 * @author Saito_Shinya@ogis-ri.co.jp
 *
 */
public class AxisConnector extends AbstractConnector
{
    
    /** ビーンタイプ */
    private List beanTypes = null;
    
    private String style = null;
    
    private String use=null;
    
    /**
     * インスタンスを生成する
     */
    public AxisConnector() {
        
    }
    

    /**
     * @see org.mule.extras.seasar2.config.ConnectorConfig#getConnector()
     */
    public Connector buildConnector()
    {
    	org.mule.transport.soap.axis.AxisConnector connector 
    		= new org.mule.transport.soap.axis.AxisConnector();
//        if (beanTypes != null)
//        {
//            setProperty("beanTypes", beanTypes);
//        }
        
        BeanUtil.copyProperties(this, connector);
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


    
	public List getBeanTypes() {
		return beanTypes;
	}


	public void setBeanTypes(List beanTypes) {
		this.beanTypes = beanTypes;
	}


	public String getStyle() 
	{
		return style;
	}


	public void setStyle(String style) 
	{
		this.style = style;
	}


	public String getUse() 
	{
		return use;
	}


	public void setUse(String use) 
	{
		this.use = use;
	}

    
    
    /*
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
                else
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
    */
    
}
