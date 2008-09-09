package org.mule.extras.seasar2.endpoint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.TransactionManager;

import org.mule.api.MuleContext;
import org.mule.api.MuleException;
import org.mule.api.endpoint.EndpointBuilder;
import org.mule.api.routing.filter.Filter;
import org.mule.api.transaction.TransactionConfig;
import org.mule.api.transformer.Transformer;
import org.mule.api.transport.Connector;
import org.mule.transaction.MuleTransactionConfig;
import org.mule.transaction.XaTransactionFactory;
import org.mule.util.ObjectNameHelper;
import org.mule.endpoint.EndpointURIEndpointBuilder;
import org.mule.endpoint.URIBuilder;
import org.mule.extras.seasar2.connector.AbstractConnector;
import org.mule.extras.seasar2.connector.ConnectorConfig;
import org.mule.extras.seasar2.exception.S2MuleConfigurationException;
import org.mule.extras.seasar2.exception.S2MuleRuntimeException;
import org.seasar.framework.log.Logger;


/**
 * Endpointの構成情報を保持するクラスです。
 * 
 * @author Saito_Shinya@ogis-ri.co.jp
 *
 */
/**
 * @author Administrator
 *
 */
public abstract class AbstractEndpoint implements EndpointConfig {
	
	/**  Endpointのuri */
	protected String uri;

	/** トランスフォーマ */
	protected List transformers;
	
	/** コネクタ*/
	protected ConnectorConfig connectorConfig;
	
	/** プロパティ */
	protected Map properties;
	
	/** フィルター*/
	protected Filter filter;
	
	protected Boolean remoteSync = false;
	
    protected Integer remoteSyncTimeout;
	
	/** logger*/
    private static final Logger logger = Logger
        .getLogger(AbstractEndpoint.class);
	
    /** デフォルトコンストラクタ*/
	public AbstractEndpoint() 
	{
	}
	
	public AbstractEndpoint(String uri)
	{
		this.uri = uri;
	}
	

	/**
	 * @see org.mule.extras.seasar2.endpoint.EndpointConfig#buildEndpointBuilder() 
	 */
	public EndpointBuilder buildEndpointBuilder(MuleContext muleContext) {
		try
		{
			 EndpointBuilder endpointBuilder = null;
	         if (uri!=null) 
	         {
	             URIBuilder uriBuilder = new URIBuilder(uri);
	             endpointBuilder = new EndpointURIEndpointBuilder(uriBuilder,muleContext);
	         } 
	         else
	         {
	             throw new S2MuleConfigurationException("ESML0002",new Object[]{"uri"});
	         }
	         
	         if (connectorConfig != null) 
	         {
	             //Connector の設定
	             Connector connector = (Connector)connectorConfig.buildConnector();

	             muleContext.getRegistry().registerConnector(connector);
	             endpointBuilder.setConnector(connector);
	             
	             if(connectorConfig.isTransacted()) 
	             {
	            	TransactionConfig transactionConfig = new MuleTransactionConfig();
	            	transactionConfig.setAction(TransactionConfig.ACTION_BEGIN_OR_JOIN);
	            	transactionConfig.setFactory(new XaTransactionFactory());
	            	endpointBuilder.setTransactionConfig(transactionConfig);
	             }
	             logger.debug("Connectorを作成しました:" + connector);
	         }
			
	         if (filter != null)
	         {
	        	 endpointBuilder.setFilter(filter);
	         }
	         
	         if (transformers != null) 
	         {
	             endpointBuilder.setTransformers(transformers);
	         }
	         
	         if (properties != null)
	         {
	        	 endpointBuilder.setProperties(properties);
	         }
	         
	         endpointBuilder.setRemoteSync(remoteSync);
	         
	         if (remoteSyncTimeout != null)
	         {
	        	 endpointBuilder.setRemoteSyncTimeout(remoteSyncTimeout);
	         }
	         
			return endpointBuilder;
		} catch(MuleException e) {
			   throw new S2MuleRuntimeException("ESML0003", new Object[]{e}, e);
		}
	}

	
	   /**
     * トランスフォーマを追加する
     * 
     * @param newTransformer
     */
    public void addTransformer(Transformer newTransformer) 
    {
        if(transformers == null) 
        {
            transformers = new ArrayList();
        }
        transformers.add(newTransformer);
    }

    /**
     * プロパティを追加する
     */
    public void setProperty(String key, Object value) 
    {
    	if(properties == null)
    	{
    		properties = new HashMap();
    	} 
		properties.put(key, value);
    }
    
    
	public void setFilter(Filter filter) {
		this.filter = filter;
	}

	public void setProperties(Map properties)
	{
		this.properties = properties;
	}

	public Map getProperties() 
	{
		return properties;
	}

	public void setTransformers(List transformers) 
	{
		this.transformers = transformers;
	}

	public String getUri() 
	{
		return uri;
	}

	public void setUri(String uri) 
	{
		this.uri = uri;
	}

	public ConnectorConfig getConnectorConfig() 
	{
		return connectorConfig;
	}

	public void setConnectorConfig(ConnectorConfig connectorConfig) 
	{
		this.connectorConfig = connectorConfig;
	}

	public void setRemoteSync(Boolean remoteSync) 
	{
		this.remoteSync = remoteSync;
	}

	public void setRemoteSyncTimeout(Integer remoteSyncTimeout) 
	{
		this.remoteSyncTimeout = remoteSyncTimeout;
	}
	
	
	
//	public String getUriScheme()
//	{
//		int index = uri.indexOf(":/");
//		return uri.substring(0,index);
//	}
	
}
