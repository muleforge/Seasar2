package org.mule.extras.seasar2.sender.axis;

import org.mule.tck.FunctionalTestCase;

public class MuleAxisEcho extends FunctionalTestCase {

	@Override
	protected String getConfigResources() {
		
		return "org/mule/config/echo-axis-config.xml";
	}
	
	public void testMule() {
		while(true){
			
		}
	}

}
