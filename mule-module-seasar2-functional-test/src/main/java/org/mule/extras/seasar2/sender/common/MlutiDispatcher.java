/*
 * Copyright (c) Osaka Gas Information System Research Institute Co., Ltd.
 * All rights reserved.  http://www.ogis-ri.co.jp/
 * 
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.extras.seasar2.sender.common;

/**
 * ID-JMS-O-006
 * の機能テストで利用される複数Endpointへ投げるメソッドが
 * 定義されたインターフェース
 *
 */
public interface MlutiDispatcher 
{
	void mlutiDispatch();
}
