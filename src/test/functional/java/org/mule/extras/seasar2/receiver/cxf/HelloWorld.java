package org.mule.extras.seasar2.receiver.cxf;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
public interface HelloWorld
{
    @WebResult(name="src")
    public String echo(@WebParam(name="src") String string);
}


