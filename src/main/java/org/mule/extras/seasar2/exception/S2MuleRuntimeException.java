package org.mule.extras.seasar2.exception;

import org.seasar.framework.exception.SRuntimeException;

/**
 * S2Mule �Ŕ��������G���[��ʒm���邽�߂̎��s����O
 * 
 * @author Shinya_Saito@ogis-ri.co.jp
 *
 */
public class S2MuleRuntimeException extends SRuntimeException {
	
	private static final long serialVersionUID = -331466760857177938L;

	/**
	 * �C���X�^���X���\�z���܂�
	 * 
	 * @param cause �����ƂȂ�����O
	 */
	public S2MuleRuntimeException(Throwable cause) {
		this(null,null,cause);
	}
	
	/**
	 * �C���X�^���X���\�z���܂�
	 * 
	 * @param messageCode ���b�Z�[�W�R�[�h
	 * @param args ���b�Z�[�W�ɖ��ߍ��܂�����
	 * @param cause �����ƂȂ�����O
	 */
	public S2MuleRuntimeException(String messageCode, Object[] args, Throwable cause) {
		super(messageCode,args,cause);
	}

}
