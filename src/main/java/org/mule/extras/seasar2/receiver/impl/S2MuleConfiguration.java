package org.mule.extras.seasar2.receiver.impl;

import java.util.ArrayList;
import java.util.List;

import org.mule.endpoint.DefaultInboundEndpoint;
import org.mule.endpoint.DefaultOutboundEndpoint;
import org.mule.endpoint.URIBuilder;
//import org.mule.endpoint.MuleEndpoint;
import org.mule.api.MuleException;

/**
 * 
 * @author Saito_Shinya@ogis-ri.co.jp
 *
 */
public class S2MuleConfiguration 
{
    
    /**
     * 複数のinboundのEndpoint
     * 
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
     * 初期化されたかどうかのフラグ
     */
    private boolean isInitalize = false;
    
    /**
     * デフォルトコンストラクタ
     * @throws UMOException
     */
    public S2MuleConfiguration() throws MuleException 
    {
        
    }
        
    /**
     * InboundEndpointを追加する
     */
    public void addInboundEndpointUri(String uri) 
    {
        inboundEndpoints.add(uri);
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
        return name;
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
