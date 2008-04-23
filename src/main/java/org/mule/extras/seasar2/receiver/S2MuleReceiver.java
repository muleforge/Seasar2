package org.mule.extras.seasar2.receiver;

/**
 * Muleを利用して、POJOがメッセージを
 * 受信できるようにするコンポーネントのインタフェースです。
 * 
 * @author Shinya_Saito@ogis-ri.co.jp
 *
 */
public interface S2MuleReceiver 
{
	/**
	 * Muleにサービスコンポーネントを登録します
	 * サービスコンポーネントはdiconファイルに登録されます
	 */
	void addService();
}
