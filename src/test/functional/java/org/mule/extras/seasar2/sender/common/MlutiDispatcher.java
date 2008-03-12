package org.mule.extras.seasar2.sender.common;

/**
 * ID-JMS-O-006
 * の機能テストで利用される複数Endpointへ投げるメソッドが
 * 定義されたインターフェース
 *
 */
public interface MlutiDispatcher {
	void mlutiDispatch();
}
