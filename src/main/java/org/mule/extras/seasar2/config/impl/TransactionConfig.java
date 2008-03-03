package org.mule.extras.seasar2.config.impl;

import org.mule.extras.seasar2.exception.S2MuleConfigurationException;

/**
 * S2Muleのトランザクションの構成情報
 * 
 * @author Saito_Shinya@ogis-ri.co.jp
 */
public class TransactionConfig {
	
	/** トランザクションに参加しない*/
    public static final String NONE_STRING = "NONE";
    
    /** 常にトランザクションを開始する。すでにトランザクションがある場合は例外を投げる*/
    public static final String ALWAYS_BEGIN_STRING = "ALWAYS_BEGIN";
    
    /** すでにトランザクションがある場合はそれに参加する。それ以外の場合はトランザクションを開始する*/
    public static final String BEGIN_OR_JOIN_STRING = "BEGIN_OR_JOIN";
    
    /** すでにトランザクションがある場合はそれに参加する。それ以外の場合は例外を投げる*/
    public static final String ALWAYS_JOIN_STRING = "ALWAYS_JOIN";
    
    /** すでにトランザクションがある場合はそれに参加する。それ以外の場合はなにもしない*/
    public static final String JOIN_IF_POSSIBLE_STRING = "JOIN_IF_POSSIBLE";

	
	/** トランザクション処理の設定*/
	private String action;

	/**
	 * インスタンスの生成
	 * 
	 * @param action
	 */
	public TransactionConfig(String action) {
		this.action = action;
	}
	
	public byte getAction() {
		if (NONE_STRING.equals(action)) {
			return org.mule.api.transaction.TransactionConfig.ACTION_NONE;
		} else if (ALWAYS_BEGIN_STRING.equals(action)) {
			return org.mule.api.transaction.TransactionConfig.ACTION_ALWAYS_BEGIN;
		} else if (BEGIN_OR_JOIN_STRING.equals(action)) {
			return org.mule.api.transaction.TransactionConfig.ACTION_BEGIN_OR_JOIN;
		} else if (ALWAYS_JOIN_STRING.equals(action)) {
			return org.mule.api.transaction.TransactionConfig.ACTION_ALWAYS_JOIN;
		} else if(JOIN_IF_POSSIBLE_STRING.equals(action)) {
			return org.mule.api.transaction.TransactionConfig.ACTION_JOIN_IF_POSSIBLE;
		} else {
			throw new S2MuleConfigurationException("ESML0002",new Object[]{"action"});
		}
	}

	public void setAction(String action) {
		this.action = action;
	}
}
