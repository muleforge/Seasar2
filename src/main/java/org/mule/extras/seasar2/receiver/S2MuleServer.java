package org.mule.extras.seasar2.receiver;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mule.extras.seasar2.receiver.builder.S2MuleComponentBuilder;
import org.mule.umo.UMOException;
import org.mule.umo.UMOManagementContext;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.S2ContainerFactory;

/**
 * �R�}���h���C������--dicon diconfile���w�肷��ƁA
 * diocn�t�@�C���ɏ�����Ă���MuleComponent�̏�������
 * MuleServer���N������N���X�ł��B
 * �R�}���h���C���������ȗ������app.dicon�ɂȂ�܂��B
 * 
 * @author Saito_Shinya@ogis-ri.co.jp
 *
 */
public class S2MuleServer {
	/**
	 * �f�t�H���g��dicon�t�@�C����
	 */
	private static final String DEFAULT_DICON_FILE = "app.dicon";
	
	private static final Log logger = LogFactory.getLog( S2MuleServer.class );
	
	/**
	 * S2Container
	 */
	private S2Container container;
	
	/**
	 * Mule�̃}�l�W�����g�R���e�L�X�g
	 */
	private UMOManagementContext managementContext;
	
	 /**
	  *  �V���b�g�_�E���t�b�N�Ɠ�������邽�߂̃��b�`
	  */
    protected static CountDownLatch latch = new CountDownLatch(1);

	
	/**
	 * Main
	 */
	public static void main( String[] args ) throws Exception {
		S2MuleServer server = new S2MuleServer(args);
		server.run();
	}
	
	public S2MuleServer( String[] args ) {
		init(args);
	}
	
	/**
	 * ���������s��
	 * 
	 * @param args
	 */
	public void init( String[] args ) {
		String dicon = getDicon(args);
		S2Container container = createS2Container(dicon);
		setContainer(container);
	}
	
	/**
	 * MuleServer���N�����܂��B
	 *
	 */
	public void run() throws UMOException {
		//S2ComponentBuilder���擾����
		S2MuleComponentBuilder builder = 
			(S2MuleComponentBuilder)container.getComponent(S2MuleComponentBuilder.class);
//		 managementContext = builder.configure();
//		 
//		 managementContext.start();		 
		
		 Runtime.getRuntime().addShutdownHook(new Thread() {
	            @Override
	            public void run() {
	               destoryS2Container();
	                latch.countDown();
	            }
	        });
		    
		    try {
		    	//�e�X�g
//		    	Thread.sleep(3000);
//		    	try {
//		    		builder.destroy();
//		    	} catch (Exception e) {
//		    		
//		    	}
		    	//�e�X�g�I���
		    	latch.await();
		    } catch( InterruptedException e ) {
	            destoryS2Container();
			} 
	}
	
	/**
	 *  S2Container���쐬���܂��B
	 * @param dicon dicon�t�@�C����
	 * @return
	 */
	private S2Container createS2Container( String dicon ) {
		S2Container container = S2ContainerFactory.create(dicon);
		return container;
	}
	
	/**
	 * S2Container��j�����܂��B
	 */
	private void destoryS2Container() {
		this.container.destroy();
	}
	
	
    /**
     * �R�s�[ org.seasar.jms.server.Main#getDicon
     * 
     * �R�}���h���C�������Ŏw�肳�ꂽdicon�t�@�C���̃p�X����Ԃ��܂��B
     * <p>
     * �R�}���h���C�������Ńp�X���w�肳��Ȃ������ꍇ�̓f�t�H���g��<code>app.dicon</code>��Ԃ��܂��B
     * </p>
     * 
     * @param args
     *            �R�}���h���C������
     * @return dicon�t�@�C���̃p�X��
     * @throws IllegalArgumentException
     *             �R�}���h���C���������s���̏ꍇ�ɃX���[����܂�
     */
    private String getDicon(final String[] args) throws IllegalArgumentException {
        final String dicon = getArg("--dicon", args);
        return dicon.equals("") ? DEFAULT_DICON_FILE : dicon;
    }
	
	 /**
	  * �R�s�[ org.seasar.jms.server.Main#getArg
	  * 
     * �R�}���h���C����������w�肳�ꂽ�L�[�ɑΉ�����l��Ԃ��܂��B
     * 
     * @param name
     *            �R�}���h���C�������̖��O
     * @param args
     *            �R�}���h���C������
     * @return �R�}���h���C������
     */
    private String getArg(final String name, final String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals(name)) {
                if (i + 1 < args.length) {
                    return args[i + 1];
                }
                throw new IllegalArgumentException(Arrays.toString(args));
            }
        }
        return "";
    }

	public void setContainer(S2Container container) {
		this.container = container;
	}

	
}
