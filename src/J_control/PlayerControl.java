package J_control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/*
 * 接口 KeyListener用于接收键盘事件（击键）的侦听器接口。
 * 旨在处理键盘事件的类要么实现此接口（及其包含的所有方法），要么扩展抽象 KeyAdapter 类（仅重写有用的方法）。
 * 
 * */

@SuppressWarnings("unused")
public class PlayerControl extends KeyAdapter {

	@SuppressWarnings("unused")
	private GameControl gameControl;	
	
	public PlayerControl(GameControl game_Control) {
		this.gameControl=game_Control;
	}
	
	/*键盘按下事件*/
	@Override
	public void keyPressed(KeyEvent e) {
		
		//System.out.print(e.getKeyCode()+"  ");
		this.gameControl.actionByKeyCode(e.getKeyCode());
		
	}

	
}
