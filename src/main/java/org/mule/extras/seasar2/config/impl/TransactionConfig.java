package org.mule.extras.seasar2.config.impl;

import org.mule.extras.seasar2.exception.S2MuleConfigurationException;

/**
 * S2Mule�̃g�����U�N�V�����̍\�����
 * 
 * @author Saito_Shinya@ogis-ri.co.jp
 */
public class TransactionConfig {
	
	/** �g�����U�N�V�����ɎQ�����Ȃ�*/
    public static final String NONE_STRING = "NONE";
    
    /** ��Ƀg�����U�N�V�������J�n����B���łɃg�����U�N�V����������ꍇ�͗�O�𓊂���*/
    public static final String ALWAYS_BEGIN_STRING = "ALWAYS_BEGIN";
    
    /** ���łɃg�����U�N�V����������ꍇ�͂���ɎQ������B����ȊO�̏ꍇ�̓g�����U�N�V�������J�n����*/
    public static final String BEGIN_OR_JOIN_STRING = "BEGIN_OR_JOIN";
    
    /** ���łɃg�����U�N�V����������ꍇ�͂���ɎQ������B����ȊO�̏ꍇ�͗�O�𓊂���*/
    public static final String ALWAYS_JOIN_STRING = "ALWAYS_JOIN";
    
    /** ���łɃg�����U�N�V����������ꍇ�͂���ɎQ������B����ȊO�̏ꍇ�͂Ȃɂ����Ȃ�*/
    public static final String JOIN_IF_POSSIBLE_STRING = "JOIN_IF_POSSIBLE";

	
	/** �g�����U�N�V���������̐ݒ�*/
	private String action;

	/**
	 * �C���X�^���X�̐���
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
