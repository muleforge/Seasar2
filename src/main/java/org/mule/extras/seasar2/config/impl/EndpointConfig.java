package org.mule.extras.seasar2.config.impl;

import java.util.ArrayList;
import java.util.List;

import org.mule.api.transformer.Transformer;
import org.mule.extras.seasar2.config.ComponentConfig;
import org.mule.transformer.AbstractTransformer;

public class EndpointConfig extends AbstractConfig {
	
	/** Endpointのuri*/
	private String uri;

	private List transformers;
	
	private ComponentConfig connectorConfig;
	
	private ComponentConfig filterConfig;
	
	public Object buildComponent() {
		// TODO Auto-generated method stub
		return null;
	}

	public EndpointConfig() {
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
