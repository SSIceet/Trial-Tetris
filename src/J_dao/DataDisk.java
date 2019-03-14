package J_dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import J_dto.Player;

public class DataDisk implements Data {

	//创建I/O保存文件
	private final String FILE_PATH;
	
	//获取配置文件里的元素
	public DataDisk(HashMap<String,String> param) {
		this.FILE_PATH = param.get("FILE_PATH");
	}
	
	@Override
	//读取数据
	public List<Player> loadDto() {
		
		ObjectInputStream ois =null;
		List<Player> players=null;
		
		try {
			ois=new ObjectInputStream(new FileInputStream(FILE_PATH));
			players=(List<Player>)ois.readObject();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				ois.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		
		return players;
	}

	@Override
	//写入数据
	public void saveData(Player player) {
		//取出本地文件数据
		List<Player> players=this.loadDto();
		//追加新的数据
		players.add(player);
		//重新写入
		ObjectOutputStream oos=null;
		try {
			oos=new ObjectOutputStream(new FileOutputStream(FILE_PATH));
			oos.writeObject(players);
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				oos.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		
	}
	
}


