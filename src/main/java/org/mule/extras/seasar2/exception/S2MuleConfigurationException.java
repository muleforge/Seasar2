package org.mule.extras.seasar2.exception;

import org.seasar.framework.exception.SRuntimeException;
import org.seasar.framework.message.MessageFormatter;

/**
 * dicon ファイルから構成を作成するときに発生したエラーを通知するための例外
 * 
 * @author Saito_Shinya@ogis-ri.co.jp
 */
public class S2MuleConfigurationException extends SRuntimeException {
	
	private static final long serialVersionUID = -1235115149163252649L;
	
	/**
	 * メッセージコード
	 */
	protected String messageCode;
	
	/**
	 * メッセージに埋め込まれる引数
	 */
	protected Object[] args;

	/**
	 * インスタンスを生成します。
	 * 
	 * @param messageCode メッセージコード
	 */
	public S2MuleConfigurationException(String messageCode) {
		this(messageCode,null,null);
	}
	
	/**
	 * インスタンスを生成します
	 * 
	 * @param messageCode メッセージコード
	 * @param args メッセージに埋め込まれる引数
	 */
	public S2MuleConfigurationException(String messageCode,Object[] args) {
		this(messageCode,args,null);
	}
	
	/**
	 * インスタンスを生成します。
	 * 
	 * @param messageCode メッセージコード
	 * @param args メッセージに埋め込まれる引数
	 * @param cause 原因となった例外
	 *
	 */
	public S2MuleConfigurationException(String messageCode, Object[] args,Throwable cause) {
		super(MessageFormatter.getMessage(messageCode, args));
		this.messageCode = messageCode;
		this.args = args;
	}
}
