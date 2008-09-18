/*
 * Copyright (c) Osaka Gas Information System Research Institute Co., Ltd.
 * All rights reserved.  http://www.ogis-ri.co.jp/
 * 
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.extras.seasar2.receiver;

import org.mule.api.MuleContext;
import org.mule.api.MuleException;

/**
 * Seasar2上でMuleを動作させるインターフェースです
 * 
 * @author Saito_Shinya@ogis-ri.co.jp
 *
 */
public interface S2MuleReceiver 
{

    /**
     * MuleDescriptorをregistryに登録する。
     * 
     * @return managementContexts
     * @throws MuleException Muleで発生した例外
     */
    void start() throws MuleException;
   
    /**
     * Muleを終了する際、全てのコンポーネントを破棄する
     */
    void destroy();
}
