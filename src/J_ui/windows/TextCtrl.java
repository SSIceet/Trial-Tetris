package J_ui.windows;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

public class TextCtrl extends JTextField{
	
	private int KeyCode;
	
	private final String MethodName;

	public TextCtrl(int x,int y,int w,int h,String MethodName) {
		
		//设置按钮位置
		this.setBounds(x,y,w,h);
		//字体居中显示
		setHorizontalAlignment(0);
		//初始化方法名
		this.MethodName = MethodName;
		//添加事件监听(键盘监听)
		this.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {	
				setText(null);
				//字体居中显示
				setHorizontalAlignment(0);
			}
			
			public void keyPressed(KeyEvent e) {
				setText(null);
				//字体居中显示
				setHorizontalAlignment(0);
			}

			//键盘松开的时候触发
			public void keyReleased(KeyEvent e) {
				//setText(KeyEvent.getKeyText(e.getKeyCode()));
				setKeyCode(e.getKeyCode());
				//字体居中显示
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
