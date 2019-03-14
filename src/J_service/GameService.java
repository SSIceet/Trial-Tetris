package J_service;

import java.util.List;

import J_dto.Player;

public interface GameService {
	  
	public boolean keyUp();
	
	public boolean keyDown();
	
	public boolean keyLeft();
	
	public boolean keyRight();
	
	public boolean keyFunUp();
	
	public boolean keyFunDown();
	
	public boolean keyFunLeft();
	
	public boolean keyFunRight();
	
	//�������߳�
	public void startGame();
	
	//��Ϸ��Ҫ��Ϊ
	public void mainAction();
}
