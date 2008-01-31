package org.mule.extras.seasar2.config.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.mule.extras.seasar2.exception.MuleConfigurationException;
import org.seasar.framework.beans.PropertyNotFoundRuntimeException;

/**
 * Config�̒��ۃN���X�ł��B
 * 
 * @author Shinya_Saito@ogis-ri.co.jp
 *
 */
public abstract class AbstractConfig {
	/** Connector�̃v���p�e�B */
	protected Map properties = new HashMap();
	
	/**
	 * �v���p�e�B��ǉ�����
	 */
	public void setProperty(String key, Object value) {
		properties.put(key, value);
	}
	
	/**
	 * �v���p�e�B�̒l���擾����
	 */
	public Object getProperty(String key) {
		return properties.get(key);
	}
	
	public Map getProperties(){
		return properties;
	}
	
	/**
	 * {@link org.apache.commons.beanutils.BeanUtilsBean#populate(Object, Map)}�̃��b�v���\�b�h
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
				
				//dicon�ɋL�q���ꂽ�v���p�e�B�����݂��邩�`�F�b�N
				if(beanUtils.getPropertyUtils().getPropertyDescriptor(bean, name)!=null){
					beanUtils.setProperty(bean, name, value);
				} else {
					throw new PropertyNotFoundRuntimeException(bean.getClass(),name);
				}
			}
		} catch(PropertyNotFoundRuntimeException e) {
			throw e;
		} catch(Exception e) {
			//TODO Error Message Code
			throw new MuleConfigurationException(null,null,e);
		} 
	}
}
