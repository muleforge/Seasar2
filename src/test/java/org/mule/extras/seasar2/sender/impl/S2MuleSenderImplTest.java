package org.mule.extras.seasar2.sender.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.Map;

import javax.transaction.TransactionManager;

import org.mule.extras.seasar2.config.ComponentConfig;
import org.mule.extras.seasar2.sender.S2MuleSender;
import org.seasar.extension.unit.S2TestCase;

public class S2MuleSenderImplTest extends S2TestCase 
{
    
    private S2MuleSender sender_;

    private File outputFile = new File("C:/temp/S2Mule-HelloWorldTestFile.txt");
    
    public S2MuleSenderImplTest(String name) 
    {
        super(name);
    }
    
    public void setUp() throws Exception 
    {
        include("SenderImplTest.dicon");
    }
    
    public void testProperties() throws Exception 
    {
        Class c = sender_.getClass();

        Field outboundUriField = c.getDeclaredField("outboundUri");
        outboundUriField.setAccessible(true);
        String outboundUri = (String) outboundUriField.get(sender_);
        assertEquals("Outbound URI isn't set correctly.", "file:///C:/temp", outboundUri);
        
        Field connectorConfigField = c.getDeclaredField("connectorConfig");
        connectorConfigField.setAccessible(true);
        ComponentConfig connectionConfig = (ComponentConfig) connectorConfigField.get(sender_);
        assertNotNull(connectionConfig);
    }
    
    public void testDispatch() throws Exception 
    {
        sender_.dispatch("Hello World Test!");
        assertTrue("Output file doesn't exist.", outputFile.exists());
        
        BufferedReader reader = new BufferedReader(new FileReader(outputFile));
        assertEquals("Output content isn't correct.", "Hello World Test!", reader.readLine());
    }
    
    public void tearDownDispatch() throws Exception 
    {
        outputFile.delete();
    }

}
