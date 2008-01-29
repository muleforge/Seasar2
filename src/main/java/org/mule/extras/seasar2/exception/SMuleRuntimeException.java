package org.mule.extras.seasar2.exception;

import org.seasar.framework.exception.SRuntimeException;

/**
 * S2Muleで発生したエラーを通知するための実行時例外

 * @author Shinya_Saito@ogis-ri.co.jp
 *
 */
public class SMuleRuntimeException extends SRuntimeException {
	
	private static final long serialVersionUID = -331466760857177938L;

	/**
	 * インスタンスを構築します
	 * 
	 * @param cause 原因となった例外
	 */
	public SMuleRuntimeException(Throwable cause) {
		this(null,null,cause);
	}
	
	/**
	 * インスタンスを構築します
	 * 
	 * @param messageCode メッセージコード
	 * @param args メッセージに埋め込まれる引数
	 * @param cause 原因となった例外
	 */
	public SMuleRuntimeException(String messageCode, Object[] args, Throwable cause) {
		super(messageCode,args,cause);
	}

}
