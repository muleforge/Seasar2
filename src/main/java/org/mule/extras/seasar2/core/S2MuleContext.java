package org.mule.extras.seasar2.core;

import org.mule.api.MuleContext;
import org.mule.api.MuleException;
import org.mule.context.DefaultMuleContextFactory;
import org.mule.extras.seasar2.exception.S2MuleConfigurationException;

//TODO コメント
public class S2MuleContext 
{
	private MuleContext muleContext;
	
	public void createMuleContext() 
	{
		try{
			DefaultMuleContextFactory muleContextFactory
				= new DefaultMuleContextFactory();
			setMuleContext(muleContextFactory.createMuleContext());
		} catch(MuleException ex) {
			//TODO 例外メッセージ
			throw new S2MuleConfigurationException("",null,ex);
		}
	}

	public MuleContext getMuleContext() {
		return muleContext;
	}

	public void setMuleContext(MuleContext muleContext) {
		this.muleContext = muleContext;
	}
}
