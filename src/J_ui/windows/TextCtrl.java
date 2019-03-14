package J_ui.windows;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

public class TextCtrl extends JTextField{
	
	private int KeyCode;
	
	private final String MethodName;

	public TextCtrl(int x,int y,int w,int h,String MethodName) {
		
		//���ð�ťλ��
		this.setBounds(x,y,w,h);
		//���������ʾ
		setHorizontalAlignment(0);
		//��ʼ��������
		this.MethodName = MethodName;
		//����¼�����(���̼���)
		this.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {	
				setText(null);
				//���������ʾ
				setHorizontalAlignment(0);
			}
			
			public void keyPressed(KeyEvent e) {
				setText(null);
				//���������ʾ
				setHorizontalAlignment(0);
			}

			//�����ɿ���ʱ�򴥷�
			public void keyReleased(KeyEvent e) {
				//setText(KeyEvent.getKeyText(e.getKeyCode()));
				setKeyCode(e.getKeyCode());
				//���������ʾ
				setHorizontalAlignment(0);
			}
			
		});
		
		
	}

	public int getKeyCode() {
		return KeyCode;
	}

	public String getMethodName() {
		return MethodName;
	}

	public void setKeyCode(int keyCode) {
		KeyCode = keyCode;
		setText(KeyEvent.getKeyText(this.KeyCode));
	}
	
}
