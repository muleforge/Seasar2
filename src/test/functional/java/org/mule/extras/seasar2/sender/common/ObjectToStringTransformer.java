package org.mule.extras.seasar2.sender.common;

import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractTransformer;

public class ObjectToStringTransformer extends AbstractTransformer {

	public ObjectToStringTransformer() {
		super();
		this.returnClass = String.class;
	}
	
	@Override
	protected Object doTransform(Object src, String encoding)
			throws TransformerException {
		return src.toString();
	}

}
