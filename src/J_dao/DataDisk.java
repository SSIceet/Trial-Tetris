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

	//����I/O�����ļ�
	private final String FILE_PATH;
	
	//��ȡ�����ļ����Ԫ��
	public DataDisk(HashMap<String,String> param) {
		this.FILE_PATH = param.get("FILE_PATH");
	}
	
	@Override
	//��ȡ����
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
	//д������
	public void saveData(Player player) {
		//ȡ�������ļ�����
		List<Player> players=this.loadDto();
		//׷���µ�����
		players.add(player);
		//����д��
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


