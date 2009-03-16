
/*
 * 
 */

package org.mule.example.client;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;

/**
 * This class was generated by Apache CXF 2.1.2
 * Tue Feb 24 13:50:39 JST 2009
 * Generated source version: 2.1.2
 * 
 */


@WebServiceClient(name = "EchoService", 
                  wsdlLocation = "http://localhost:65082/services/EchoUMO?wsdl",
                  targetNamespace = "http://echo.example.mule.org/") 
public class EchoService extends Service {

    public final static URL WSDL_LOCATION;
    public final static QName SERVICE = new QName("http://echo.example.mule.org/", "EchoService");
    public final static QName EchoPort = new QName("http://echo.example.mule.org/", "EchoPort");
    static {
        URL url = null;
        try {
            url = new URL("http://localhost:65082/services/EchoUMO?wsdl");
        } catch (MalformedURLException e) {
            System.err.println("Can not initialize the default wsdl from http://localhost:65082/services/EchoUMO?wsdl");
            // e.printStackTrace();
        }
        WSDL_LOCATION = url;
    }

    public EchoService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public EchoService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public EchoService() {
        super(WSDL_LOCATION, SERVICE);
    }

    /**
     * 
     * @return
     *     returns Echo
     */
    @WebEndpoint(name = "EchoPort")
    public Echo getEchoPort() {
        return super.getPort(EchoPort, Echo.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns Echo
     */
    @WebEndpoint(name = "EchoPort")
    public Echo getEchoPort(WebServiceFeature... features) {
        return super.getPort(EchoPort, Echo.class, features);
    }

}
