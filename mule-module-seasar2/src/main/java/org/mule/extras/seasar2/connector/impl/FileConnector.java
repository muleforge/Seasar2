/*
 * Copyright (c) Osaka Gas Information System Research Institute Co., Ltd.
 * All rights reserved.  http://www.ogis-ri.co.jp/
 * 
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.extras.seasar2.connector.impl;

import java.io.FileOutputStream;

import org.mule.api.transport.Connector;
import org.mule.extras.seasar2.connector.AbstractConnector;
import org.mule.extras.seasar2.connector.MessageDispatcher;
import org.mule.util.ObjectNameHelper;
import org.seasar.framework.beans.util.BeanUtil;

/**
 * org.mule.transport.file.FileConnectorの構成情報を保持するクラスです。
 * 
 * @author Saito_Shinya@ogis-ri.co.jp
 *
 */
public class FileConnector extends AbstractConnector 
{
    /** ポーリングの間隔 Receiverのみ有効*/
    private long pollingFrequency = 0;

    /** 移動後の出力名のパターン Receiverのみ有効*/
    private String moveToPattern = null;

    /** 移動先のディレクトリ Receiverのみ有効*/
    private String moveToDirectory = null;

    /** 出力ファイル名のパターン Senderのみ有効*/
    private String outputPattern = null;

    /** 同名のファイルが存在していたら最後に追加する Senderのみ有効*/
    private boolean outputAppend = false;

    /** 読み込んだファイルを削除する Receiverのみ有効*/
    private boolean autoDelete = true;

    /** FileAgeをチェックする*/
    private boolean checkFileAge = false;

    /** ファイルの履歴番号*/
    private long fileAge = 0;

    /** OutputStream Senderのみ有効*/
    private FileOutputStream outputStream = null;
    
    /** オブジェクトをシリアライズする Senderのみ有効*/
    private boolean serialiseObjects = false;

    /** ストリーミングにする*/
    private boolean streaming = true;
    
    /**
     * インスタンスを生成する
     */
    public FileConnector() 
    {
        //blank
    }
    
    /**
     * @see org.mule.extras.seasar2.connector.ConnectorConfig#getConnector()
     */
    public Connector buildConnector() 
    {
        org.mule.transport.file.FileConnector connector 
            = new org.mule.transport.file.FileConnector();
        setName(ObjectNameHelper.getConnectorName(connector));
        BeanUtil.copyProperties(this, connector);

        return connector;
    }
    
    public MessageDispatcher getMessageDispatcher() 
    {
    	if (messageDispatcher == null)
    	{
    		messageDispatcher = new FileMessageDispatcherImpl();
    	}
    	return messageDispatcher;
    }

    public long getPollingFrequency() 
    {
        return pollingFrequency;
    }

    public void setPollingFrequency(long pollingFrequency) 
    {
        this.pollingFrequency = pollingFrequency;
    }

    public String getMoveToPattern() 
    {
        return moveToPattern;
    }

    public void setMoveToPattern(String moveToPattern) 
    {
        this.moveToPattern = moveToPattern;
    }

    public String getMoveToDirectory() 
    {
        return moveToDirectory;
    }

    public void setMoveToDirectory(String moveToDirectory) 
    {
        this.moveToDirectory = moveToDirectory;
    }

    public String getOutputPattern() 
    {
        return outputPattern;
    }

    public void setOutputPattern(String outputPattern) 
    {
        this.outputPattern = outputPattern;
    }

    public boolean isOutputAppend() 
    {
        return outputAppend;
    }

    public void setOutputAppend(boolean outputAppend) 
    {
        this.outputAppend = outputAppend;
    }

    public boolean isAutoDelete() 
    {
        return autoDelete;
    }

    public void setAutoDelete(boolean autoDelete) 
    {
        this.autoDelete = autoDelete;
    }

    public boolean isCheckFileAge() 
    {
        return checkFileAge;
    }

    public void setCheckFileAge(boolean checkFileAge) 
    {
        this.checkFileAge = checkFileAge;
    }

    public long getFileAge() 
    {
        return fileAge;
    }

    public void setFileAge(long fileAge) 
    {
        this.fileAge = fileAge;
    }

    public FileOutputStream getOutputStream() 
    {
        return outputStream;
    }

    public void setOutputStream(FileOutputStream outputStream) 
    {
        this.outputStream = outputStream;
    }

    public boolean isSerialiseObjects() 
    {
        return serialiseObjects;
    }

    public void setSerialiseObjects(boolean serialiseObjects) 
    {
        this.serialiseObjects = serialiseObjects;
    }

    public boolean isStreaming() 
    {
        return streaming;
    }

    public void setStreaming(boolean streaming) 
    {
        this.streaming = streaming;
    }

    
    
}
