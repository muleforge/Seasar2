package org.mule.extras.seasar2.client.wrapper;

import java.util.Map;

import org.mule.umo.UMOException;
import org.mule.umo.UMOMessage;
import org.mule.umo.provider.UMOConnector;

/**
 * Muleを使ってメッセージを送信する機能のインタフェースです。
 * 
 * @author Saito_Shinya@ogis-ri.co.jp
 */
public interface MuleClientWrapper {
	
	/**
	 * 非同期メッセージを送信します。
	 * メインスレッドと送信のためのスレッドが違い、
	 * 送信処理が終わる前にメインスレッドが終了する可能性があるので推奨されません。
	 * 
	 * @param url 送信先のurl
	 * @param payload メッセージの本文
	 * @deprecated
	 */
	void dispatch(String url, Object payload);
	
	/**
	 * 非同期メッセージを送信します。プロパティによって送信メッセージの設定を変えることができます。
	 * メインスレッドと送信のためのスレッドが違い、
	 * 送信処理が終わる前にメインスレッドが終了する可能性があるので推奨されません。
	 * 
	 * @param url 送信先のurl
	 * @param payload メッセージの本文
	 * @param messageProperties メッセージのプロパティ
	 * @deprecated
	 */
	void dispatch(String url, Object payload, Map messageProperties);
	
	/**
	 * 同期メッセージを送信します。
	 * 
	 * @param url 送信先のurl
	 * @param payload メッセージの本文
	 */
	UMOMessage send(String url, Object payload);
	
	/**
	 * 同期メッセージを送信します。プロパティによって送信メッセージの設定を変えることができます。
	 * 
	 * @param url 送信先のurl
	 * @param payload メッセージの本文
	 * @param messageProperties メッセージのプロパティ
	 */
	UMOMessage send(String url, Object payload, Map messageProperties);
	
	/**
	 * ConnectorをRegistryに登録します
	 * 
	 * @param connector 登録するConnector
	 * 
	 * @exception UMOException Mule側で例外が発生した場合にスローされます
	 */
	void registerConnector(UMOConnector connector) throws UMOException;
}
