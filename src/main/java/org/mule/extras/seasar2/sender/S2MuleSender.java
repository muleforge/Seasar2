package org.mule.extras.seasar2.sender;

/**
 * Muleを利用してメッセージを送信するコンポーネントのインタフェースです。
 * 
 * @author Shinya_Saito@ogis-ri.co.jp
 *
 */
public interface S2MuleSender {
	
	/**
	 * 非同期メッセージを送信します。
	 * 
	 * @param payload メッセージの本文
	 */
	void dispatch(Object payload);
	
//	/**
//	 * 非同期メッセージを送信します。プロパティによって送信メッセージの設定を変えることができます。
//	 * メインスレッドと送信のためのスレッドが違い、
//	 * 送信処理が終わる前にメインスレッドが終了する可能性があるので推奨されません。
//	 * 
//	 * @param payload メッセージの本文
//	 * @param messageProperties メッセージのプロパティ
//	 */
//	void dispatch(Object payload, Map messageProperties);
	
	/**
	 * 同期メッセージを送信します
	 * 
	 * @param payload メッセージの本文
	 */
	Object send(Object payload);
	
	/**
	 * プロパティを設定する
	 * 
	 * @param key プロパティのキー
	 * @param value プロパティの値
	 */
	void setProperty(String key, Object value);
	
	
}
