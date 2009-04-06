package org.mule.extras.seasar2.ws.hello;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.1.2
 * Fri Mar 27 19:18:59 JST 2009
 * Generated source version: 2.1.2
 * 
 */
 
@WebService(targetNamespace = "http://cxf.receiver.seasar2.extras.mule.org/", name = "HelloWorld")
@XmlSeeAlso({ObjectFactory.class})
public interface HelloWorld {

    @ResponseWrapper(localName = "helloResponse", targetNamespace = "http://cxf.receiver.seasar2.extras.mule.org/", className = "org.mule.extras.seasar2.ws.hello.HelloResponse")
    @RequestWrapper(localName = "hello", targetNamespace = "http://cxf.receiver.seasar2.extras.mule.org/", className = "org.mule.extras.seasar2.ws.hello.Hello")
    @WebResult(name = "response", targetNamespace = "")
    @WebMethod
    public java.lang.String hello(
        @WebParam(name = "src", targetNamespace = "")
        java.lang.String src
    );
}
