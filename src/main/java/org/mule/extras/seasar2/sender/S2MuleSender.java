package org.mule.extras.seasar2.sender;

/**
 * Mule�𗘗p���ă��b�Z�[�W�𑗐M����R���|�[�l���g�̃C���^�t�F�[�X�ł��B
 * 
 * @author Shinya_Saito@ogis-ri.co.jp
 *
 */
public interface S2MuleSender {
	
	/**
	 * �񓯊����b�Z�[�W�𑗐M���܂��B
	 * 
	 * @param payload ���b�Z�[�W�̖{��
	 */
	void dispatch(Object payload);
	
//	/**
//	 * �񓯊����b�Z�[�W�𑗐M���܂��B�v���p�e�B�ɂ���đ��M���b�Z�[�W�̐ݒ��ς��邱�Ƃ��ł��܂��B
//	 * ���C���X���b�h�Ƒ��M�̂��߂̃X���b�h���Ⴂ�A
//	 * ���M�������I���O�Ƀ��C���X���b�h���I������\��������̂Ő�������܂���B
//	 * 
//	 * @param payload ���b�Z�[�W�̖{��
//	 * @param messageProperties ���b�Z�[�W�̃v���p�e�B
//	 */
//	void dispatch(Object payload, Map messageProperties);
	
	/**
	 * �������b�Z�[�W�𑗐M���܂�
	 * 
	 * @param payload ���b�Z�[�W�̖{��
	 */
	Object send(Object payload);
	
	/**
	 * �v���p�e�B��ݒ肷��
	 * 
	 * @param key �v���p�e�B�̃L�[
	 * @param value �v���p�e�B�̒l
	 */
	void setProperty(String key, Object value);
	
	
}
