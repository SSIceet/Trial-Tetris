package J_dao;

import java.util.List;

import J_dto.Player;

/*
 * ���ݳ־ò�ӿ�
 * */
public interface Data {
	
	//�������
	public List<Player> loadDto();
	
	//�洢����
	public void saveData(Player players);
		
}
