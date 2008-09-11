
package org.mule.example.echo;

import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractTransformer;

public class StringToHolder extends AbstractTransformer {

	@Override
	protected Object doTransform(Object arg0, String arg1)
			throws TransformerException {
//		if(arg0 instanceof String)
//		{
//			String src = (String)arg0;
//			 javax.xml.ws.Holder<java.lang.String> 
//			 	response = new javax.xml.ws.Holder<java.lang.String>(src);
//			 return response;
//		}
//		
//		
//		return null;
//		String src = arg0.toString();
		String src = "Test";
		javax.xml.ws.Holder<java.lang.String> 
		 	response = new javax.xml.ws.Holder<java.lang.String>(src);
		return response;
	}

}
