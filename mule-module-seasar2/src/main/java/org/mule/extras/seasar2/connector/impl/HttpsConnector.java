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
import org.mule.util.ObjectNameHelper;
import org.seasar.framework.beans.util.BeanUtil;

/**
 * org.mule.transport.http.HttpsConnectorの構成情報を保持するクラス
 * 
 * @author Saito_Shinya@ogis-ri.co.jp
 *
 */
public class HttpsConnector extends HttpConnector
{
    /** クライアントキーストアのパス*/
    private String clientKeyStore;
    
    /** クライアントキーストアのパスワード*/
    private String clientKeyStorePassword;
    
    /** クライアントキーストアのタイプ*/
    private String clientKeyStoreType;
    
    /** キーストアが利用するアルゴリズム*/
    private String keyManagerAlgorithm;
    
    /** キーストアのパス*/
    private String keyStore;
    
    /** 秘密キーのパスワード*/
    private String keyPassword;
    
    /** キーストアのパスワード*/
    private String keyStorePassword;
    
    /** キーストアのタイプ*/
    private String keyStoreType;
    
    /** トラストストアのパス*/
    private String trustStore;
    
    /** トラストストアのパスワード*/
    private String trustStorePassword;
    
    /** トラストストアのタイプ*/
    private String trustStoreType;
    
    /** トラストストアが利用するアルゴリズム*/
    private String trustManagerAlgorithm;
    
    /** trustStoreが明示的に設定されていない場合にclientKeyStoreをtrustStoreに利用する */
    private boolean explicitTrustStoreOnly;
    
    /** クライアント認証を必須にする */
    private boolean requireClientAuthentication;
    
    /**
     * @see org.mule.extras.seasar2.connector.ConnectorConfig#getConnector()
     */
    @Override
    public Connector buildConnector()
    {
       org.mule.transport.http.HttpsConnector connector
           = new org.mule.transport.http.HttpsConnector();
       setName(ObjectNameHelper.getConnectorName(connector));
       BeanUtil.copyProperties(this, connector);
       
       return connector;
    }

    public String getClientKeyStore()
    {
        return clientKeyStore;
    }

    public void setClientKeyStore(String clientKeyStore)
    {
        this.clientKeyStore = clientKeyStore;
    }

    public String getClientKeyStorePassword()
    {
        return clientKeyStorePassword;
    }

    public void setClientKeyStorePassword(String clientKeyStorePassword)
    {
        this.clientKeyStorePassword = clientKeyStorePassword;
    }

    public String getClientKeyStoreType()
    {
        return clientKeyStoreType;
    }

    public void setClientKeyStoreType(String clientKeyStoreType)
    {
        this.clientKeyStoreType = clientKeyStoreType;
    }

    public String getKeyManagerAlgorithm()
    {
        return keyManagerAlgorithm;
    }

    public void setKeyManagerAlgorithm(String keyManagerAlgorithm)
    {
        this.keyManagerAlgorithm = keyManagerAlgorithm;
    }

    public String getKeyStore()
    {
        return keyStore;
    }

    public void setKeyStore(String keyStore)
    {
        this.keyStore = keyStore;
    }

    public String getKeyPassword()
    {
        return keyPassword;
    }

    public void setKeyPassword(String keyPassword)
    {
        this.keyPassword = keyPassword;
    }

    public String getKeyStorePassword()
    {
        return keyStorePassword;
    }

    public void setKeyStorePassword(String keyStorePassword)
    {
        this.keyStorePassword = keyStorePassword;
    }

    public String getKeyStoreType()
    {
        return keyStoreType;
    }

    public void setKeyStoreType(String keyStoreType)
    {
        this.keyStoreType = keyStoreType;
    }

    public String getTrustStore()
    {
        return trustStore;
    }

    public void setTrustStore(String trustStore)
    {
        this.trustStore = trustStore;
    }

    public String getTrustStorePassword()
    {
        return trustStorePassword;
    }

    public void setTrustStorePassword(String trustStorePassword)
    {
        this.trustStorePassword = trustStorePassword;
    }

    public String getTrustStoreType()
    {
        return trustStoreType;
    }

    public void setTrustStoreType(String trustStoreType)
    {
        this.trustStoreType = trustStoreType;
    }

    public String getTrustManagerAlgorithm()
    {
        return trustManagerAlgorithm;
    }

    public void setTrustManagerAlgorithm(String trustManagerAlgorithm)
    {
        this.trustManagerAlgorithm = trustManagerAlgorithm;
    }

    /**
     * @return the explicitTrustStoreOnly
     */
    public boolean isExplicitTrustStoreOnly()
    {
        return explicitTrustStoreOnly;
    }

    /**
     * @param explicitTrustStoreOnly the explicitTrustStoreOnly to set
     */
    public void setExplicitTrustStoreOnly(boolean explicitTrustStoreOnly)
    {
        this.explicitTrustStoreOnly = explicitTrustStoreOnly;
    }

    /**
     * @return the requireClientAuthentication
     */
    public boolean isRequireClientAuthentication()
    {
        return requireClientAuthentication;
    }

    /**
     * @param requireClientAuthentication the requireClientAuthentication to set
     */
    public void setRequireClientAuthentication(boolean requireClientAuthentication)
    {
        this.requireClientAuthentication = requireClientAuthentication;
    }
    
    
}
