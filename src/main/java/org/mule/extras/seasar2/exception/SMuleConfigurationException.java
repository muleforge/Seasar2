package org.mule.extras.seasar2.exception;

import org.seasar.framework.exception.SRuntimeException;
import org.seasar.framework.message.MessageFormatter;

/**
 * dicon�t�@�C������\�����쐬����Ƃ��ɔ��������G���[��ʒm���邽�߂̗�O
 * 
 * @author Saito_Shinya@ogis-ri.co.jp
 *
 */
public class SMuleConfigurationException extends SRuntimeException {
	
	private static final long serialVersionUID = -1235115149163252649L;
	
	/**
	 * ���b�Z�[�W�R�[�h
	 */
	protected String messageCode;
	
	/**
	 * ���b�Z�[�W�ɖ��ߍ��܂�����
	 */
	protected Object[] args;

	/**
	 * �C���X�^���X�𐶐����܂��B
	 * 
	 * @param messageCode ���b�Z�[�W�R�[�h
	 */
	public SMuleConfigurationException(String messageCode) {
		this(messageCode,null,null);
	}
	
	/**
	 * �C���X�^���X�𐶐����܂�
	 * 
	 * @param messageCode ���b�Z�[�W�R�[�h
	 * @param args ���b�Z�[�W�ɖ��ߍ��܂�����
	 */
	public SMuleConfigurationException(String messageCode,Object[] args) {
		this(messageCode,args,null);
	}
	
	/**
	 * �C���X�^���X�𐶐����܂��B
	 * 
	 * @param messageCode ���b�Z�[�W�R�[�h
	 * @param args ���b�Z�[�W�ɖ��ߍ��܂�����
	 * @param cause �����ƂȂ�����O
	 *
	 */
	public SMuleConfigurationException(String messageCode, Object[] args,Throwable cause) {
		super(MessageFormatter.getMessage(messageCode, args));
		this.messageCode = messageCode;
		this.args = args;
	}
}
