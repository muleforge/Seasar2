package org.mule.extras.seasar2.sender;

import org.mule.extras.seasar2.config.ConnectorConfig;

/**
 * Muleを利用してメッセージを送信するコンポーネントのインタフェースです。
 * 
 * @author Shinya_Saito@ogis-ri.co.jp
 *
 */
public interface S2MuleSender 
{
    
    /**
     * 非同期メッセージを送信します。
     * 
     * @param payload メッセージの本文
     */
    void dispatch(Object payload);
    
    /**
     * 同期メッセージを送信します
     * 
     * @param payload メッセージの本文
     * @return レスポンスメッセージ
     */
    Object send(Object payload);
    
    /**
     * プロパティを設定する
     * 
     * @param key プロパティのキー
     * @param value プロパティの値
     */
    void setProperty(String key, Object value);
    
    /**
     * コネクタの構成情報を取得する
     * 
     * @return connectorConfig コネクタの構成情報
     */
  //  ConnectorConfig getConnectorConfig() ;
    
}
