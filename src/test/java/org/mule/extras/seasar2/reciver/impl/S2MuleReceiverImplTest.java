package org.mule.extras.seasar2.reciver.impl;

import org.mule.extras.seasar2.receiver.impl.S2MuleReceiverImpl;
import org.mule.extras.seasar2.receiver.impl.S2MuleConfiguration;
import org.seasar.extension.unit.S2TestCase;

public class S2MuleReceiverImplTest extends S2TestCase 
{
    
    private S2MuleConfiguration config_;
    private S2MuleReceiverImpl builder_;

    public S2MuleReceiverImplTest(String name) 
    {
        super(name);
    }

    public void setUp() throws Exception 
    {
        include("S2MuleComponentBuilderTest.dicon");
    }

    public void testConfigure() throws Exception 
    {
    	while (true)
    	{
    		
    	}
    	//builder_.configure();
    }
}
