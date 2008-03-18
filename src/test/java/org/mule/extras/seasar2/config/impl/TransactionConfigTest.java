package org.mule.extras.seasar2.config.impl;

import org.mule.extras.seasar2.exception.S2MuleConfigurationException;
import org.seasar.extension.unit.S2TestCase;

public class TransactionConfigTest extends S2TestCase {
	
	private TransactionConfig config_;

	public TransactionConfigTest(String name) {
		super(name);
	}
	
	public void setUp() throws Exception {
		include("TransactionConfigTest.dicon");
	}
	
	public void testGetAction() throws Exception {
		
		assertEquals(org.mule.api.transaction.TransactionConfig.ACTION_NONE, config_.getAction());
		
		config_.setAction("ALWAYS_BEGIN");
		assertEquals(org.mule.api.transaction.TransactionConfig.ACTION_ALWAYS_BEGIN, config_.getAction());
		
		config_.setAction("BEGIN_OR_JOIN");
		assertEquals(org.mule.api.transaction.TransactionConfig.ACTION_BEGIN_OR_JOIN, config_.getAction());
		
		config_.setAction("ALWAYS_JOIN");
		assertEquals(org.mule.api.transaction.TransactionConfig.ACTION_ALWAYS_JOIN, config_.getAction());
		
		config_.setAction("JOIN_IF_POSSIBLE");
		assertEquals(org.mule.api.transaction.TransactionConfig.ACTION_JOIN_IF_POSSIBLE, config_.getAction());
		
		//例外のテスト
		config_.setAction("aaa");
		try {
			config_.getAction();
		} catch (S2MuleConfigurationException expected) {
			System.out.println(expected.getMessage());
		}
	}
}
