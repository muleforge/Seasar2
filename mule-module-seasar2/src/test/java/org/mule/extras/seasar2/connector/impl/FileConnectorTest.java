package org.mule.extras.seasar2.connector.impl;

import org.mule.extras.seasar2.connector.impl.FileConnector;
import org.seasar.extension.unit.S2TestCase;

public class FileConnectorTest extends S2TestCase 
{
    
    private FileConnector config_;
    
    public FileConnectorTest(String name) 
    {
        super(name);
    }
    
    public void setUp() throws Exception 
    {
        include("FileConnectorTest.dicon");
    }
    
    public void testGetConnector() throws Exception 
    {
        
        Object connector = config_.buildConnector();
        assertTrue("Connector isn't FileConnector"
                , connector instanceof org.mule.transport.file.FileConnector);
        
        String outputPattern = ((org.mule.transport.file.FileConnector) connector).getOutputPattern();
        assertEquals("Property OutputPattern isn't correct", outputPattern, "S2Mule-Test${DATE:yyyyMMdd}.txt");
        
        long pollingFrequency = ((org.mule.transport.file.FileConnector) connector).getPollingFrequency();
        assertEquals("Property PollingFrequency isn't correct",pollingFrequency , 10);

        boolean outputAppend = ((org.mule.transport.file.FileConnector) connector).isOutputAppend();
        assertTrue("Property OutputPattern isn't correct", outputAppend);
        
        boolean streaming = ((org.mule.transport.file.FileConnector) connector).isStreaming();
        assertTrue("Property Streaming isn't correct",streaming);
        
        int numberOfConcurrentTransactedReceivers  
        	= ((org.mule.transport.file.FileConnector) connector).getNumberOfConcurrentTransactedReceivers();
        assertEquals("Property numberOfConcurrentTransactedReceivers isn't correct",
        			numberOfConcurrentTransactedReceivers , 1);
    }
}
