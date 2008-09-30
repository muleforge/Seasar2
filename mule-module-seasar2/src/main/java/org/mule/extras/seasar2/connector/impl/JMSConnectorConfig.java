/*
 * Copyright (c) Osaka Gas Information System Research Institute Co., Ltd.
 * All rights reserved.  http://www.ogis-ri.co.jp/
 * 
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.extras.seasar2.connector.impl;

import javax.jms.Session;

import org.mule.extras.seasar2.connector.AbstractConnector;
import org.mule.transport.jms.JmsConstants;

/**
 * JMSConnectorの構成情報を保持するクラスです。
 * 
 * @author Saito_Shinya@ogis-ri.co.jp
 *
 */
public abstract class JMSConnectorConfig extends AbstractConnector
{
    /** 受け取り通知のモード*/
    private int acknowledgementMode = Session.AUTO_ACKNOWLEDGE;

    /** クライアントID*/
    private String clientId;

    /** 送信処理の一部としてメッセージを保存する*/
    private boolean persistentDelivery = false;
    
    /** 再送の最大回数*/
    private int maxRedelivery = 0;

    /** JMSのセッションをキャッシュする*/
    private boolean cacheJmsSessions = false;
    
    /** JMSのコネクションをリカバーする*/
    private boolean recoverJmsConnections = true;

    /** 接続の度にConsumerを作成する*/
    private boolean eagerConsumer = true;
    
    /** ユーザ*/
    private String username = null;
    
    /** パスワード*/
    private String password = null;

    /** JMSの仕様*/
    private String specification = JmsConstants.JMS_SPECIFICATION_102B;

    public int getAcknowledgementMode() 
    {
        return acknowledgementMode;
    }

    public void setAcknowledgementMode(int acknowledgementMode) 
    {
        this.acknowledgementMode = acknowledgementMode;
    }

    public String getClientId() 
    {
        return clientId;
    }

    public void setClientId(String clientId) 
    {
        this.clientId = clientId;
    }
    
    public boolean isPersistentDelivery() 
    {
        return persistentDelivery;
    }

    public void setPersistentDelivery(boolean persistentDelivery) 
    {
        this.persistentDelivery = persistentDelivery;
    }

    public int getMaxRedelivery() 
    {
        return maxRedelivery;
    }

    public void setMaxRedelivery(int maxRedelivery) 
    {
        this.maxRedelivery = maxRedelivery;
    }

    public boolean isCacheJmsSessions() 
    {
        return cacheJmsSessions;
    }

    public void setCacheJmsSessions(boolean cacheJmsSessions) 
    {
        this.cacheJmsSessions = cacheJmsSessions;
    }

    public boolean isRecoverJmsConnections() 
    {
        return recoverJmsConnections;
    }

    public void setRecoverJmsConnections(boolean recoverJmsConnections) 
    {
        this.recoverJmsConnections = recoverJmsConnections;
    }

    public boolean isEagerConsumer() 
    {
        return eagerConsumer;
    }

    public void setEagerConsumer(boolean eagerConsumer) 
    {
        this.eagerConsumer = eagerConsumer;
    }

    public String getUsername() 
    {
        return username;
    }

    public void setUsername(String username) 
    {
        this.username = username;
    }

    public String getPassword() 
    {
        return password;
    }

    public void setPassword(String password) 
    {
        this.password = password;
    }

    public String getSpecification() 
    {
        return specification;
    }

    public void setSpecification(String specification) 
    {
        this.specification = specification;
    }
}
