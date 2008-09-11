package org.mule.extras.seasar2.connector.impl;

import java.util.ArrayList;
import java.util.List;

import org.mule.api.transport.Connector;
import org.mule.extras.seasar2.connector.AbstractConnector;
import org.mule.util.ObjectNameHelper;
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
    
    /**
     * インスタンスを生成する
     */
    public AxisConnector() 
    {
        
    }

    /**
     * @see org.mule.extras.seasar2.connector.ConnectorConfig#getConnector()
     */
    public Connector buildConnector()
    {
    	org.mule.transport.soap.axis.AxisConnector connector 
    		= new org.mule.transport.soap.axis.AxisConnector();
        
    	setName(ObjectNameHelper.getConnectorName(connector));
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
    
}
