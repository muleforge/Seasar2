/*
 * Copyright (c) Osaka Gas Information System Research Institute Co., Ltd.
 * All rights reserved.  http://www.ogis-ri.co.jp/
 * 
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
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
