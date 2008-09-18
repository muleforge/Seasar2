/*
 * Copyright (c) Osaka Gas Information System Research Institute Co., Ltd.
 * All rights reserved.  http://www.ogis-ri.co.jp/
 * 
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
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
