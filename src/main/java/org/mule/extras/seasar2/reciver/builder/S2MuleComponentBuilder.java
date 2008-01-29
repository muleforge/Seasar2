package org.mule.extras.seasar2.reciver.builder;

import org.mule.umo.UMOException;
import org.mule.umo.UMOManagementContext;

/**
 * Seasar2���g�p����Mule��Component���\�z����@�\�̃C���^�[�t�F�[�X�ł��B
 * TODO ���� ConfigurationBulider�C���^�[�t�F�[�X�ɂ���ׂ����H
 * 
 * @author Saito_Shinya@ogis-ri.co.jp
 *
 */
public interface S2MuleComponentBuilder {
	
	/**
	 * MuleDescriptor��registry�ɓo�^����B
	 * 
	 * @return managementContexts
	 */
	UMOManagementContext configure()  throws UMOException;
	
	void destroy() ;
}
