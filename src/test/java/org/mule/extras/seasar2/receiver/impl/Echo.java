package org.mule.extras.seasar2.receiver.impl;

public class Echo 
{
    public void echo(Object src) 
    {
        System.out.println("Message : " + src);
    }
}
