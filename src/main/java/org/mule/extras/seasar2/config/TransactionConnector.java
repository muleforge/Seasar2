package org.mule.extras.seasar2.config;

/**
 * トランザクショナルなコネクタを表す
 * 
 * @author Saito_Shinya@ogis-ri.co.jp
 *
 */
public interface TransactionConnector
{
   boolean isTransaction();
}
