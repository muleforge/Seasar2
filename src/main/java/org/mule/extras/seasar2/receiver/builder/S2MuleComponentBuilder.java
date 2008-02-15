package org.mule.extras.seasar2.receiver.builder;

import org.mule.umo.UMOException;
import org.mule.umo.UMOManagementContext;

/**
 * Seasar2を使用してMuleのComponentを構築する機能のインターフェースです。
 * TODO 検討 ConfigurationBuliderインターフェースにするべきか？
 * 
 * @author Saito_Shinya@ogis-ri.co.jp
 *
 */
public interface S2MuleComponentBuilder {
	
	/**
	 * MuleDescriptorをregistryに登録する。
	 * 
	 * @return managementContexts
	 */
	UMOManagementContext configure()  throws UMOException;
	
	void destroy() ;
}
