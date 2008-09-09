package org.mule.extras.seasar2.receiver.impl;

import java.util.ArrayList;
import java.util.List;

import org.mule.extras.seasar2.endpoint.EndpointConfig;
import org.mule.extras.seasar2.endpoint.impl.EndpointConfigBuilderImpl;
import org.mule.api.MuleException;
import org.mule.extras.seasar2.endpoint.EndpointConfigBuilder;
import org.seasar.framework.util.ClassUtil;

/**
 * 
 * @author Saito_Shinya@ogis-ri.co.jp
 *
 */
public class S2MuleConfiguration 
{
    
    /**
     * 複数のinboundのEndpoint
     */
    private List inboundEndpoints = new ArrayList();
	
	
    /**
     * Messageを受け取るクラス
     */
    private Object umoImpl;
    
    /**
     * MuleDesciptorの名前になる
     */
    private String name;
    
    /**
     * デフォルトコンストラクタ
     * @throws UMOException
     */
    public S2MuleConfiguration() throws MuleException 
    {
        
    }
        
    /**
     * InboundEndpointを追加する
     * @param endpoint 
     */
    public void addInbounEndpoint(EndpointConfig endpoint)
    {
    	inboundEndpoints.add(endpoint);
    }
    
    /**
     * InboudEndpointを追加する
     * @return endpointUri
     */
    public void addInboudEndpoint(String endpointUri)
    {
    	EndpointConfigBuilder builder 
    		= new EndpointConfigBuilderImpl(endpointUri);
    	inboundEndpoints.add(builder.build());
    }
    
    
    public Object getUmoImpl() 
    {
        return umoImpl;
    }

    public void setUmoImpl(Object umoImpl) 
    {
        this.umoImpl = umoImpl;
    }

    public String getName() 
    {
    	if (name == null && umoImpl != null) 
    	{
    		return ClassUtil.getShortClassName(umoImpl.getClass());
    	} 
    	else 
    	{
    		return name;
    	} 
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public List getInboundEndpoints() 
    {
        return inboundEndpoints;
    }
}
