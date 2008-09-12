package org.mule.extras.seasar2.sender.impl;

import org.mule.extras.seasar2.sender.S2MuleSender;
import org.seasar.extension.unit.S2TestCase;

public class S2MuleSenderImplTest extends S2TestCase 
{
    
    private S2MuleSender sender_;
    
    public S2MuleSenderImplTest(String name) 
    {
        super(name);
    }
    
    public void setUp() throws Exception 
    {
        include("SenderImplTest.dicon");
    }
    
    public void testProperties() throws Exception 
    {
 
    }
    
    public void testDispatch() throws Exception 
    {
        sender_.dispatch("Hello World Test!");
    }
    
}
