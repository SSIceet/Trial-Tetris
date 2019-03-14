package J_dao;

import java.util.List;

import J_dto.Player;

/*
 * 数据持久层接口
 * */
public interface Data {
	
	//获得数据
	public List<Player> loadDto();
	
	//存储数据
	public void saveData(Player players);
		
}
