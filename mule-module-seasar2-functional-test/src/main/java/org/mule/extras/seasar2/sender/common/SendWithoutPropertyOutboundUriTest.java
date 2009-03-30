/*
 * Copyright (c) Osaka Gas Information System Research Institute Co., Ltd.
 * All rights reserved.  http://www.ogis-ri.co.jp/
 * 
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.extras.seasar2.sender.common;

import org.mule.extras.seasar2.exception.S2MuleConfigurationException;
import org.mule.extras.seasar2.sender.S2MuleSender;
import org.mule.extras.seasar2.test.component.Customer;
import org.seasar.extension.unit.S2TestCase;

/**
 * ID-COMMON-E-001
 * 
 * Outbound Uriを設定せずにメッセージを送信する
 *
 */
public class SendWithoutPropertyOutboundUriTest extends S2TestCase {
    
    private S2MuleSender sender_;
    
    public void setUp() throws Exception 
    {
        include("SendWithoutPropertyOutboundUriTest.dicon");
    }
    
    public void testDispatch() throws Exception 
    {
        try {
            sender_.dispatch("SendWithoutPropertyOutboundUriTest : OK");
        }
        catch (S2MuleConfigurationException e) 
        {
            e.printStackTrace();
        }
    }
    
}
