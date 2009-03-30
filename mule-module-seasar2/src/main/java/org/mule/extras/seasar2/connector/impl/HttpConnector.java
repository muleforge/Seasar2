/*
 * Copyright (c) Osaka Gas Information System Research Institute Co., Ltd.
 * All rights reserved.  http://www.ogis-ri.co.jp/
 * 
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.extras.seasar2.connector.impl;

import org.mule.api.transport.Connector;
import org.mule.transport.http.HttpConstants;
import org.mule.util.ObjectNameHelper;
import org.seasar.framework.beans.util.BeanUtil;

/**
 * org.mule.transport.http.HttpConnectorの構成情報を保持するクラスです
 * 
 * @author Saito_Shinya@ogis-ri.co.jp
 *
 */
public class HttpConnector extends TcpConnector
{
    /** プロキシのホスト*/
    private String proxyHostname = null;

    /** プロキシのポート番号*/
    private int proxyPort = HttpConstants.DEFAULT_HTTP_PORT;

    /** プロキシのユーザ名*/
    private String proxyUsername = null;

    /** プロキシのパスワード*/
    private String proxyPassword = null;

    /** クッキー*/
    private String cookieSpec = "netscape";

    /** クッキーを有効にするか*/
    private boolean enableCookies = false;
    
    /**
     * @see org.mule.extras.seasar2.connector.ConnectorConfig#getConnector()
     */
    @Override
    public Connector buildConnector()
    {
       org.mule.transport.http.HttpConnector connector
           = new org.mule.transport.http.HttpConnector();
       setName(ObjectNameHelper.getConnectorName(connector));
       BeanUtil.copyProperties(this, connector);
       
       return connector;
    }

    public String getProxyHostname()
    {
        return proxyHostname;
    }

    public void setProxyHostname(String proxyHostname)
    {
        this.proxyHostname = proxyHostname;
    }

    public int getProxyPort()
    {
        return proxyPort;
    }

    public void setProxyPort(int proxyPort)
    {
        this.proxyPort = proxyPort;
    }

    public String getProxyUsername()
    {
        return proxyUsername;
    }

    public void setProxyUsername(String proxyUsername)
    {
        this.proxyUsername = proxyUsername;
    }

    public String getProxyPassword()
    {
        return proxyPassword;
    }

    public void setProxyPassword(String proxyPassword)
    {
        this.proxyPassword = proxyPassword;
    }

    public String getCookieSpec()
    {
        return cookieSpec;
    }

    public void setCookieSpec(String cookieSpec)
    {
        this.cookieSpec = cookieSpec;
    }

    public boolean isEnableCookies()
    {
        return enableCookies;
    }

    public void setEnableCookies(boolean enableCookies)
    {
        this.enableCookies = enableCookies;
    }
    
    

}
