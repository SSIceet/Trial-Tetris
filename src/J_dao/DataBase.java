package J_dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import J_dto.Player;

public class DataBase implements Data {
	
	//�����ַ���
	private static String DRIVER;
	//������
	private String DB_URL;
	//���ݿ��û�����
	private String DB_USE;
	//���ݿ�����
	private String DB_PWD;
	
	private static String LODE_SQL="SELECT TOP 5 User_name,Point FROM User_point WHERE Type_id=1 ORDER BY Point DESC";
	
	private static String SAVE_SQL="INSERT INTO User_point(User_name,Point,Type_id) VALUES (?,?,?)";
	
	public DataBase(HashMap<String,String> param) {
		
		//��ȡ�����ļ����Ԫ��
		this.DB_URL=param.get("DB_URL");
		this.DB_USE=param.get("DB_USE");
		this.DB_PWD=param.get("DB_PWD");
		try {
			//����DriverManager��registerDriver����ע��һ��sql server��JDBC������Driver��
			Class.forName(param.get("DRIVER"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	//�����ݿ���ȡ������
	@Override
	public List<Player> loadDto() {
		//�����������ݿ�
		Connection conn=null;
		//�������ݿ�
		PreparedStatement stmt=null;
		ResultSet rs=null;
		List<Player> players =new ArrayList<Player>();
		
		try {
			//�������ݿ�
			conn=DriverManager.getConnection(DB_URL,DB_USE,DB_PWD);
			//��ȡ���ݿ����Ԫ��
			stmt=conn.prepareStatement(LODE_SQL);
			//���ݿ��ѯ(ִ�в�ѯ��䣬����䷵�ص���ResultSet����)
			rs=stmt.executeQuery();
			
			while(rs.next()) {
				//��������ݹ�����players��
				players.add(new Player(rs.getString(1),rs.getInt(2)));
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			try {
				//�ر���
				if(conn!=null) conn.close();
				if(stmt!=null) stmt.close();
				if(rs!=null) rs.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
	 
		return players;
	}

	//�������ݵ����ݿ�
	@Override
	public void saveData(Player players) {
		//�����������ݿ�
		Connection conn=null;
		//�������ݿ�
		PreparedStatement stmt=null;
		
		try {
			//�������ݿ�
			conn=DriverManager.getConnection(DB_URL,DB_USE,DB_PWD);
			//������д�����ݿ�
			stmt=conn.prepareStatement(SAVE_SQL);
			
			//д����ҵ�����
			stmt.setObject(1,players.getName());
			//д����ҵķ���
			stmt.setObject(2,players.getPoint());
			//д����
			stmt.setObject(3,1);
			//ִ���������(���Է��ض�����)
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				//�ر���
				if(conn!=null) conn.close();
				if(stmt!=null) stmt.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
