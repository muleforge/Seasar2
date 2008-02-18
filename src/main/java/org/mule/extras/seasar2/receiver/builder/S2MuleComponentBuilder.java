package org.mule.extras.seasar2.receiver.builder;

import org.mule.api.MuleContext;
import org.mule.api.MuleException;

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
	MuleContext configure()  throws MuleException;
	
	void destroy() ;
}
