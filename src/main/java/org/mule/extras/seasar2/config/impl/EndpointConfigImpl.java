package org.mule.extras.seasar2.config.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.TransactionManager;

import org.mule.api.MuleContext;
import org.mule.api.MuleException;
import org.mule.api.endpoint.EndpointBuilder;
import org.mule.api.transaction.TransactionConfig;
import org.mule.api.transformer.Transformer;
import org.mule.api.transport.Connector;
import org.mule.transaction.MuleTransactionConfig;
import org.mule.transaction.XaTransactionFactory;
import org.mule.util.ObjectNameHelper;
import org.mule.endpoint.EndpointURIEndpointBuilder;
import org.mule.endpoint.URIBuilder;
import org.mule.extras.seasar2.config.ConnectorConfig;
import org.mule.extras.seasar2.config.EndpointConfig;
import org.mule.extras.seasar2.exception.S2MuleConfigurationException;
import org.mule.extras.seasar2.exception.S2MuleRuntimeException;
import org.seasar.framework.log.Logger;


/**
 * Endpointの構成情報を保持するクラスです。
 * 
 * @author Saito_Shinya@ogis-ri.co.jp
 *
 */
public class EndpointConfigImpl extends AbstractConfig implements EndpointConfig {
	
	/** Endpointのuri*/
	private String uri;

	/** トランスフォーマ*/
	private List transformers;
	
	/** コネクタ*/
	private ConnectorConfig connectorConfig;
	
	// TODO Filterの設定
//	/** フィルター*/
//	private Object filterConfig;
	
	/** logger*/
    private static final Logger logger = Logger
        .getLogger(EndpointConfigImpl.class);
	
    /** コンストラクタ*/
	public EndpointConfigImpl() 
	{
	
	}

	/**
	 * @see org.mule.extras.seasar2.config.EndpointConfig#buildEndpointBuilder() 
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
	             throw new S2MuleConfigurationException("ESML0002",new Object[]{"outboundUri"});
	         }
	         
	         if (connectorConfig != null) 
	         {
	             //Connector の設定
	             Connector connector = (Connector)connectorConfig.buildConnector();
	             connector.setName(ObjectNameHelper.getConnectorName(connector));
	             muleContext.getRegistry().registerConnector(connector);
	             endpointBuilder.setConnector(connector);
	             if (connectorConfig instanceof AxisConnectorConfigImpl)
	             {
	                 properties.putAll(connectorConfig.getProperties());
	             } 
	             else if(connectorConfig instanceof ActiveMQXAJmsConnectorConfigImpl) 
	             {
	            	TransactionConfig transactionConfig = new MuleTransactionConfig();
	            	transactionConfig.setAction(TransactionConfig.ACTION_BEGIN_OR_JOIN);
	            	transactionConfig.setFactory(new XaTransactionFactory());
	            	endpointBuilder.setTransactionConfig(transactionConfig);
	             }
	             logger.debug("Connectorを作成しました:" + connector);
	         }
			
	         if (transformers != null) 
	         {
	             endpointBuilder.setTransformers(transformers);
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
            transformers.add(newTransformer);
        }
        else
        {
            transformers.add(newTransformer);
        }
    }

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public ConnectorConfig getConnectorConfig() {
		return connectorConfig;
	}

	public void setConnectorConfig(ConnectorConfig connectorConfig) {
		this.connectorConfig = connectorConfig;
	}
	
}
