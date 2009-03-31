/*
 * Copyright (c) Osaka Gas Information System Research Institute Co., Ltd.
 * All rights reserved.  http://www.ogis-ri.co.jp/
 * 
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.extras.seasar2.examples.common;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * Echo インタフェース
 * @author Saito_Shinya@ogis-ri.co.jp
 *
 */
@WebService
public interface Echo
{
    @WebResult(name="response")
    String echo(@WebParam(name="request")String echo);
    
}
