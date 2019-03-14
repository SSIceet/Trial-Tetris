package J_control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/*
 * �ӿ� KeyListener���ڽ��ռ����¼������������������ӿڡ�
 * ּ�ڴ�������¼�����Ҫôʵ�ִ˽ӿڣ�������������з�������Ҫô��չ���� KeyAdapter �ࣨ����д���õķ�������
 * 
 * */

@SuppressWarnings("unused")
public class PlayerControl extends KeyAdapter {

	@SuppressWarnings("unused")
	private GameControl gameControl;	
	
	public PlayerControl(GameControl game_Control) {
		this.gameControl=game_Control;
	}
	
	/*���̰����¼�*/
	@Override
	public void keyPressed(KeyEvent e) {
		
		//System.out.print(e.getKeyCode()+"  ");
		this.gameControl.actionByKeyCode(e.getKeyCode());
		
	}

	
}
