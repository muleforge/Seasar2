package org.mule.extras.seasar2.exception;

/**
 * 必要なProprtyが見つからないことを通知する実行時例外
 * 
 * @author Saito_Shinya@ogis-ri.co.jp
 *
 */
public class ProprtyNotFoundException extends SMuleConfigurationException {
	
	/**
	 * インスタンスを生成します
	 * 
	 * @param messageCode メッセージコード
	 * @param args メッセージに埋め込まれる引数
	 */
	public ProprtyNotFoundException(String messageCode,Object[] args) {
		super(messageCode,args);
	}
}
