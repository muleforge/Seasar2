/*
 * Copyright (c) Osaka Gas Information System Research Institute Co., Ltd.
 * All rights reserved.  http://www.ogis-ri.co.jp/
 * 
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.extras.seasar2.sender.cxf;

import org.mule.extras.seasar2.test.component.Echo;
import org.seasar.extension.unit.S2TestCase;

/**
 * ID:AXIS-O-001
 * 
 * プロパティをなにも設定せず、メッセージを送信する。
 * 
 */
public class SendWithNoPropertyTest extends S2TestCase {

    private Echo echo_;

    public void setUp() throws Exception 
    {
        include("SendWithNoPropertyTest.dicon");
        echo_ = (Echo) getComponent(Echo.class);
    }

    public void testDispatch() throws Exception 
    {
        assertEquals("SendWithNoPropertyTest : OK", echo_
                .echo("SendWithNoPropertyTest : OK"));
    }

}
