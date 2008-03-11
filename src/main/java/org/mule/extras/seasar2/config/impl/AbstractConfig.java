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
	 */
	public void setProperty(String key, Object value) {
		properties.put(key, value);
	}
	
	/**
	 * プロパティの値を取得する
	 */
	public Object getProperty(String key) {
		return properties.get(key);
	}
	
	public Map getProperties(){
		return properties;
	}
	
	/**
	 * {@link org.apache.commons.beanutils.BeanUtilsBean#populate(Object, Map)}をラップしたメソッド
	 * 
	 * @param bean
	 * @param properties
	 */
	protected void populate(Object bean, Map properties) {
		if ((bean == null) || (properties == null)) {
			return;
		}
		
		BeanUtilsBean beanUtils = BeanUtilsBean.getInstance();
		try {
			Iterator names = properties.keySet().iterator();
			while (names.hasNext()) {

				// Identify the property name and value(s) to be assigned
				String name = (String) names.next();
				if (name == null) {
					continue;
				}
				Object value = properties.get(name);
				
				//diconに記述されたプロパティが存在するかチェック
				if(beanUtils.getPropertyUtils().getPropertyDescriptor(bean, name)!=null){
					beanUtils.setProperty(bean, name, value);
				} else {
					throw new PropertyNotFoundRuntimeException(bean.getClass(),name);
				}
			}
		} catch(PropertyNotFoundRuntimeException e) {
			throw e;
		} catch(Exception e) {
			throw new S2MuleConfigurationException("ESML0000", new Object[]{e},e);
		} 
	}
}
