package org.mule.extras.seasar2.exception;

/**
 * �K�v��Proprty��������Ȃ����Ƃ�ʒm������s����O
 * 
 * @author Saito_Shinya@ogis-ri.co.jp
 *
 */
public class ProprtyNotFoundException extends SMuleConfigurationException {
	
	/**
	 * �C���X�^���X�𐶐����܂�
	 * 
	 * @param messageCode ���b�Z�[�W�R�[�h
	 * @param args ���b�Z�[�W�ɖ��ߍ��܂�����
	 */
	public ProprtyNotFoundException(String messageCode,Object[] args) {
		super(messageCode,args);
	}
}
