package org.mule.extras.seasar2.receiver.builder;

import org.mule.api.MuleContext;
import org.mule.api.MuleException;

/**
 * Seasar2を使用してMuleのComponentを構築する機能のインターフェースです
 * TODO 検討 ConfigurationBuliderインターフェースにするべきか？
 * 
 * @author Saito_Shinya@ogis-ri.co.jp
 *
 */
public interface S2MuleComponentBuilder 
{

    /**
     * MuleDescriptorをregistryに登録する。
     * 
     * @return managementContexts
     * @throws MuleException Muleで発生した例外
     */
    MuleContext configure()  throws MuleException;
   
    /**
     * Muleを終了する際、全てのコンポーネントを破棄する
     */
    void destroy();
}
