package org.mule.extras.seasar2.exception;

/**
 * �K�v��Proprty��������Ȃ����Ƃ�ʒm������s����O
 * 
 * @author Saito_Shinya@ogis-ri.co.jp
 *
 */
public class ProprtyNotFoundException extends MuleConfigurationException {

	private static final long serialVersionUID = 7448310817447042432L;

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
