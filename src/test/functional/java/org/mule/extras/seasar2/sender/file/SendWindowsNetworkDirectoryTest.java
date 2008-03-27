package org.mule.extras.seasar2.sender.file;

import org.mule.extras.seasar2.sender.S2MuleSender;
import org.seasar.extension.unit.S2TestCase;

/**
 * ID:FILE-O-006
 * 
 * Windowsのネットワーク上にあるディレクトリにメッセージを送信する
 *
 */
public class SendWindowsNetworkDirectoryTest extends S2TestCase {
    
    private S2MuleSender sender_;
    
    public void setUp() throws Exception 
    {
        include("SendWindowsNetworkDirectoryTest.dicon");
    }
    
    public void testDispatch() throws Exception 
    {
        sender_.dispatch("SendWindowsNetworkDirectoryTest : OK");
    }
    
}
