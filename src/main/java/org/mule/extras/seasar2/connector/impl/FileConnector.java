package org.mule.extras.seasar2.connector.impl;

import java.io.FileOutputStream;

import org.mule.api.transport.Connector;
import org.mule.extras.seasar2.connector.AbstractConnector;
import org.mule.extras.seasar2.connector.ConnectorConfig;
import org.mule.util.ObjectNameHelper;
import org.seasar.framework.beans.util.BeanUtil;

/**
 * FileConnectorの構成情報を保持するクラスです。
 * 
 * @author Saito_Shinya@ogis-ri.co.jp
 *
 */
public class FileConnector extends AbstractConnector 
{
	private long pollingFrequency = 0;

    private String moveToPattern = null;

    private String moveToDirectory = null;

    private String outputPattern = null;

    private boolean outputAppend = false;

    private boolean autoDelete = true;

    private boolean checkFileAge = false;

    private long fileAge = 0;

    private FileOutputStream outputStream = null;

    private boolean serialiseObjects = false;

    private boolean streaming = true;
	
    /**
     * インスタンスを生成する
     */
    public FileConnector() 
    {
        
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
