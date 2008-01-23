package org.mule.extras.seasar2.client.wrapper;

import java.util.Map;

import org.mule.umo.UMOException;
import org.mule.umo.UMOMessage;
import org.mule.umo.provider.UMOConnector;

/**
 * Mule���g���ă��b�Z�[�W�𑗐M����@�\�̃C���^�t�F�[�X�ł��B
 * 
 * @author Saito_Shinya@ogis-ri.co.jp
 */
public interface MuleClientWrapper {
	
	/**
	 * �񓯊����b�Z�[�W�𑗐M���܂��B
	 * ���C���X���b�h�Ƒ��M�̂��߂̃X���b�h���Ⴂ�A
	 * ���M�������I���O�Ƀ��C���X���b�h���I������\��������̂Ő�������܂���B
	 * 
	 * @param url ���M���url
	 * @param payload ���b�Z�[�W�̖{��
	 * @deprecated
	 */
	void dispatch(String url, Object payload);
	
	/**
	 * �񓯊����b�Z�[�W�𑗐M���܂��B�v���p�e�B�ɂ���đ��M���b�Z�[�W�̐ݒ��ς��邱�Ƃ��ł��܂��B
	 * ���C���X���b�h�Ƒ��M�̂��߂̃X���b�h���Ⴂ�A
	 * ���M�������I���O�Ƀ��C���X���b�h���I������\��������̂Ő�������܂���B
	 * 
	 * @param url ���M���url
	 * @param payload ���b�Z�[�W�̖{��
	 * @param messageProperties ���b�Z�[�W�̃v���p�e�B
	 * @deprecated
	 */
	void dispatch(String url, Object payload, Map messageProperties);
	
	/**
	 * �������b�Z�[�W�𑗐M���܂��B
	 * 
	 * @param url ���M���url
	 * @param payload ���b�Z�[�W�̖{��
	 */
	UMOMessage send(String url, Object payload);
	
	/**
	 * �������b�Z�[�W�𑗐M���܂��B�v���p�e�B�ɂ���đ��M���b�Z�[�W�̐ݒ��ς��邱�Ƃ��ł��܂��B
	 * 
	 * @param url ���M���url
	 * @param payload ���b�Z�[�W�̖{��
	 * @param messageProperties ���b�Z�[�W�̃v���p�e�B
	 */
	UMOMessage send(String url, Object payload, Map messageProperties);
	
	/**
	 * Connector��Registry�ɓo�^���܂�
	 * 
	 * @param connector �o�^����Connector
	 * 
	 * @exception UMOException Mule���ŗ�O�����������ꍇ�ɃX���[����܂�
	 */
	void registerConnector(UMOConnector connector) throws UMOException;
}
