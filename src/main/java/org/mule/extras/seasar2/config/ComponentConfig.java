package org.mule.extras.seasar2.config;

/**
 * Mule Component�̍\������ێ�����N���X�̃C���^�t�F�[�X
 * 
 * @author Saito_Shinya@ogis-ri.co.jp
 *
 */
public interface ComponentConfig {
	/**
	 * Mule Component��Ԃ�
	 * 
	 * @return Object Mule Component
	 */
	Object buildComponent();

}
