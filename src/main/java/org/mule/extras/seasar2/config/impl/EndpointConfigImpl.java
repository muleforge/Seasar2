package org.mule.extras.seasar2.config.impl;

import java.util.ArrayList;
import java.util.List;

import org.mule.api.endpoint.InboundEndpoint;
import org.mule.api.transformer.Transformer;
import org.mule.transformer.AbstractTransformer;
import org.mule.extras.seasar2.config.ConnectorConfig;
import org.mule.extras.seasar2.config.EndpointConfig;


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
	
	/** フィルター*/
	private Object filterConfig;
	
	public InboundEndpoint buildInboundEndpoint() {
		// TODO Auto-generated method stub
		return null;
	}

	public EndpointConfigImpl() {
		// TODO Auto-generated constructor stub
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
            int index = transformers.size()-1;
            AbstractTransformer currentTransformer 
                = (AbstractTransformer)transformers.get(index);
            //currentTransformer.(newTransformer);
            transformers.add(newTransformer);
        }
    }
	
}
