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
import org.mule.extras.seasar2.connector.AbstractConnector;
import org.mule.extras.seasar2.connector.MessageDispatcher;
import org.mule.util.ObjectNameHelper;
import org.omg.CosTransactions.otid_t;
import org.seasar.framework.beans.util.BeanUtil;

/**
 * org.mule.transport.tcp.TcpConnectorの構成情報を保持するクラス
 * 
 * @author Saito_Shinya@ogis-ri.co.jp
 *
 */
public class TcpConnector extends AbstractConnector
{
    /** クライアントのソケットタイムアウト*/
    private int clientSoTimeout 
        = org.mule.transport.tcp.TcpConnector.DEFAULT_SOCKET_TIMEOUT;

    /** サーバのソケットタイムアウト*/
    private int serverSoTimeout 
        = org.mule.transport.tcp.TcpConnector.DEFAULT_SOCKET_TIMEOUT;
    
    /** 送信用のバッファサイズ*/
    private int sendBufferSize 
        = org.mule.transport.tcp.TcpConnector.DEFAULT_BUFFER_SIZE;
    
    /** 受信用のバッファサイズ*/
    private int receiveBufferSize
        = org.mule.transport.tcp.TcpConnector.DEFAULT_BUFFER_SIZE;
    
    /** サーバ側でacceptされるまでの間、接続要求を保持する個数*/
    private int receiveBacklog 
        = org.mule.transport.tcp.TcpConnector.DEFAULT_BACKLOG;
    
    /** バッファサイズを超えていない場合に遅延を無効にする*/
    private boolean sendTcpNoDelay;
    
    /** ソケットにキューイングされたメッセージの居残り設定*/
    private int socketSoLinger 
        = org.mule.transport.tcp.TcpConnector.DEFAULT_SO_LINGER;
    
    /** 複数ディスパッチ用にソケットを開いておくか*/
    private boolean keepSendSocketOpen = false;
    
    /** 接続確認のための通信*/
    private boolean keepAlive = false;
    
    /** 接続確認のための通信のタイムアウト*/
    private int keepAliveTimeout = 0;
    
    /**
     * @see org.mule.extras.seasar2.connector.ConnectorConfig#buildConnector()
     */
    public Connector buildConnector()
    {
        org.mule.transport.tcp.TcpConnector connector
            = new org.mule.transport.tcp.TcpConnector();
        setName(ObjectNameHelper.getConnectorName(connector));
        BeanUtil.copyProperties(this, connector);
        
        return connector;
    }    
    
    public int getClientSoTimeout()
    {
        return clientSoTimeout;
    }

    public void setClientSoTimeout(int clientSoTimeout)
    {
        this.clientSoTimeout = clientSoTimeout;
    }

    public int getServerSoTimeout()
    {
        return serverSoTimeout;
    }

    public void setServerSoTimeout(int serverSoTimeout)
    {
        this.serverSoTimeout = serverSoTimeout;
    }

    public int getSendBufferSize()
    {
        return sendBufferSize;
    }

    public void setSendBufferSize(int sendBufferSize)
    {
        this.sendBufferSize = sendBufferSize;
    }

    public int getReceiveBufferSize()
    {
        return receiveBufferSize;
    }

    public void setReceiveBufferSize(int receiveBufferSize)
    {
        this.receiveBufferSize = receiveBufferSize;
    }

    public int getReceiveBacklog()
    {
        return receiveBacklog;
    }

    public void setReceiveBacklog(int receiveBacklog)
    {
        this.receiveBacklog = receiveBacklog;
    }

    public boolean isSendTcpNoDelay()
    {
        return sendTcpNoDelay;
    }

    public void setSendTcpNoDelay(boolean sendTcpNoDelay)
    {
        this.sendTcpNoDelay = sendTcpNoDelay;
    }

    public int getSocketSoLinger()
    {
        return socketSoLinger;
    }

    public void setSocketSoLinger(int socketSoLinger)
    {
        this.socketSoLinger = socketSoLinger;
    }

    public boolean isKeepSendSocketOpen()
    {
        return keepSendSocketOpen;
    }

    public void setKeepSendSocketOpen(boolean keepSendSocketOpen)
    {
        this.keepSendSocketOpen = keepSendSocketOpen;
    }

    public boolean isKeepAlive()
    {
        return keepAlive;
    }

    public void setKeepAlive(boolean keepAlive)
    {
        this.keepAlive = keepAlive;
    }

    public int getKeepAliveTimeout()
    {
        return keepAliveTimeout;
    }

    public void setKeepAliveTimeout(int keepAliveTimeout)
    {
        this.keepAliveTimeout = keepAliveTimeout;
    }

    
}
