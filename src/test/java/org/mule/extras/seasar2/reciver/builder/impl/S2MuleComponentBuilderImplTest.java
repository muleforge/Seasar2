package org.mule.extras.seasar2.reciver.builder.impl;

import org.mule.extras.seasar2.receiver.builder.impl.S2MuleComponentBuilderImpl;
import org.mule.extras.seasar2.receiver.object.S2MuleConfiguration;
import org.seasar.extension.unit.S2TestCase;

public class S2MuleComponentBuilderImplTest extends S2TestCase {
	
	private S2MuleConfiguration config_;
	private S2MuleComponentBuilderImpl builder_;

	public S2MuleComponentBuilderImplTest(String name) {
		super(name);
	}

	public void setUp() throws Exception {
		include("S2MuleComponentBuilderTest.dicon");
	}

	public void testCreateDescriptor() throws Exception {
		
	}
}
