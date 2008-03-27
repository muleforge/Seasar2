package org.mule.extras.seasar2.sender.common;

/**
 * テスト用のEchoインターフェース
 * 
 * @author Saito_Shinya@ogis-ri.co.jp
 */
public interface Echo 
{
    /**
     * Echo
     * @param echo 入力文字列
     * @return エコー文字列
     */
    String echo(String echo);
    
}
