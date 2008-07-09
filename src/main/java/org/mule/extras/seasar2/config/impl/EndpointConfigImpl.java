package org.mule.extras.seasar2.config.impl;

import java.util.ArrayList;
import java.util.List;

import org.mule.api.MuleContext;
import org.mule.api.endpoint.EndpointBuilder;
import org.mule.api.transformer.Transformer;
import org.mule.api.transport.Connector;
import org.mule.util.ObjectNameHelper;
import org.mule.endpoint.EndpointURIEndpointBuilder;
import org.mule.endpoint.URIBuilder;
import org.mule.extras.seasar2.config.ConnectorConfig;
import org.mule.extras.seasar2.config.EndpointConfig;
import org.mule.extras.seasar2.exception.S2MuleConfigurationException;
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
	
	public EndpointConfigImpl() {
		// TODO Auto-generated constructor stub
	}

	public EndpointBuilder buildEndpointBuilder(MuleContext muleContext) {
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
             endpointBuilder.setConnector(connector);
             if (connectorConfig instanceof AxisConnectorConfigImpl)
             {
                 properties.putAll(connectorConfig.getProperties());
             }
             logger.debug("Connectorを作成しました:" + connector);
         }
		
         if (transformers != null) 
         {
             endpointBuilder.setTransformers(transformers);
         }
		return endpointBuilder;
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
