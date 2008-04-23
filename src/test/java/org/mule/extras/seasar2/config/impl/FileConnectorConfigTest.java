package org.mule.extras.seasar2.config.impl;

import org.mule.transport.file.FileConnector;
import org.seasar.extension.unit.S2TestCase;

public class FileConnectorConfigTest extends S2TestCase 
{
    
    private FileConnectorConfigImpl config_;
    
    public FileConnectorConfigTest(String name) 
    {
        super(name);
    }
    
    public void setUp() throws Exception 
    {
        include("FileConnectorConfigTest.dicon");
    }
    
    public void testGetConnector() throws Exception 
    {
        
        Object connector = config_.buildConnector();
        assertTrue("Connector isn't FileConnector"
                , connector instanceof FileConnector);
        
        String outputPattern = ((FileConnector) connector).getOutputPattern();
        assertEquals("Property OutputPattern isn't correct", outputPattern, "S2Mule-Test${DATE:yyyyMMdd}.txt");
        
        boolean streaming = ((FileConnector) connector).isStreaming();
        assertTrue(streaming);
    }
}
