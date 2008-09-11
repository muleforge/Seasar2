package org.mule.extras.seasar2.examples.helloworld.soap.axis;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.S2ContainerFactory;
import org.seasar.framework.exception.ResourceNotFoundRuntimeException;

public class AxisReceiver 
{
	private static final String CONFIGURE_PATH
		= "helloworld-receive-axis.dicon";
	
	public static void main(String[] args)
	{
		try
		{
			S2Container container = S2ContainerFactory.create(CONFIGURE_PATH);
			container.init();
		} 
		catch (ResourceNotFoundRuntimeException e)
        {
            System.out.println("dicon ファイルが見つかりません： " + CONFIGURE_PATH);
        }
	}
	
}
