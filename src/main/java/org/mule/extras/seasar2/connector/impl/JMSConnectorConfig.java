package org.mule.extras.seasar2.connector.impl;

import javax.jms.ConnectionFactory;
import javax.jms.Session;

import org.mule.extras.seasar2.connector.AbstractConnector;
import org.mule.transport.jms.JmsConstants;

public abstract class JMSConnectorConfig extends AbstractConnector
{	
	private int acknowledgementMode = Session.AUTO_ACKNOWLEDGE;

    private String clientId;
    
    /*
    private boolean durable;

    private boolean noLocal;

    private boolean persistentDelivery;

    private boolean honorQosHeaders;
	*/

    private boolean persistentDelivery = false;
    
    private int maxRedelivery = 0;

    private boolean cacheJmsSessions = false;

    private boolean recoverJmsConnections = true;

    private boolean eagerConsumer = true;

    public String username = null;

    public String password = null;

    private String specification = JmsConstants.JMS_SPECIFICATION_102B;

	public int getAcknowledgementMode() {
		return acknowledgementMode;
	}

	public void setAcknowledgementMode(int acknowledgementMode) {
		this.acknowledgementMode = acknowledgementMode;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	
	public boolean isPersistentDelivery() {
		return persistentDelivery;
	}

	public void setPersistentDelivery(boolean persistentDelivery) {
		this.persistentDelivery = persistentDelivery;
	}

	public int getMaxRedelivery() {
		return maxRedelivery;
	}

	public void setMaxRedelivery(int maxRedelivery) {
		this.maxRedelivery = maxRedelivery;
	}

	public boolean isCacheJmsSessions() {
		return cacheJmsSessions;
	}

	public void setCacheJmsSessions(boolean cacheJmsSessions) {
		this.cacheJmsSessions = cacheJmsSessions;
	}

	public boolean isRecoverJmsConnections() {
		return recoverJmsConnections;
	}

	public void setRecoverJmsConnections(boolean recoverJmsConnections) {
		this.recoverJmsConnections = recoverJmsConnections;
	}

	public boolean isEagerConsumer() {
		return eagerConsumer;
	}

	public void setEagerConsumer(boolean eagerConsumer) {
		this.eagerConsumer = eagerConsumer;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}
    
    

}
