package org.mule.extras.seasar2.config;

import org.mule.api.transport.Connector;
/**
 * Connector�̍\������ێ�����N���X�̃C���^�t�F�[�X
 * 
 * @author Saito_Shinya@ogis-ri.co.jp
 *
 */
public interface ConnectorConfig {
	/**
	 * UMOConnector��Ԃ�
	 * 
	 * @return UMOConnector
	 */
	Connector getConnector();
}
