package org.mule.extras.seasar2.exception;

import org.seasar.framework.exception.SRuntimeException;

/**
 * S2Mule�Ŕ��������G���[��ʒm���邽�߂̎��s����O

 * @author Shinya_Saito@ogis-ri.co.jp
 *
 */
public class SMuleRuntimeException extends SRuntimeException {
	
	/**
	 * �C���X�^���X���\�z���܂�
	 * 
	 * @param cause �����ƂȂ�����O
	 */
	public SMuleRuntimeException(Throwable cause) {
		this(null,null,cause);
	}
	
	/**
	 * �C���X�^���X���\�z���܂�
	 * 
	 * @param messageCode ���b�Z�[�W�R�[�h
	 * @param args ���b�Z�[�W�ɖ��ߍ��܂�����
	 * @param cause �����ƂȂ�����O
	 */
	public SMuleRuntimeException(String messageCode, Object[] args, Throwable cause) {
		super(messageCode,args,cause);
	}

}
