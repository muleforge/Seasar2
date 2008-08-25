package org.mule.extras.seasar2.sender.common;

import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractTransformer;

/**
 * メッセージにStringを追加するトランスフォーマ
 * 
 * @author Saito_Shinya@ogis-ri.co.jp
 *
 */
public class AddStringTransformer extends AbstractTransformer 
{
    
    public AddStringTransformer() 
    {
        setReturnClass(String.class);
    }

    @Override
    protected Object doTransform(Object src, String encoding)
            throws TransformerException 
     {
        if (src instanceof String) 
        {
            return src + " after Transformer";
        }
        return null;
    }

}
