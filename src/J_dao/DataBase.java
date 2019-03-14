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
	
	//连接字符串
	private static String DRIVER;
	//驱动包
	private String DB_URL;
	//数据库用户名字
	private String DB_USE;
	//数据库密码
	private String DB_PWD;
	
	private static String LODE_SQL="SELECT TOP 5 User_name,Point FROM User_point WHERE Type_id=1 ORDER BY Point DESC";
	
	private static String SAVE_SQL="INSERT INTO User_point(User_name,Point,Type_id) VALUES (?,?,?)";
	
	public DataBase(HashMap<String,String> param) {
		
		//获取配置文件里的元素
		this.DB_URL=param.get("DB_URL");
		this.DB_USE=param.get("DB_USE");
		this.DB_PWD=param.get("DB_PWD");
		try {
			//调用DriverManager的registerDriver方法注册一个sql server的JDBC驱动（Driver）
			Class.forName(param.get("DRIVER"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	//从数据库里取出数据
	@Override
	public List<Player> loadDto() {
		//用于连接数据库
		Connection conn=null;
		//操作数据库
		PreparedStatement stmt=null;
		ResultSet rs=null;
		List<Player> players =new ArrayList<Player>();
		
		try {
			//连接数据库
			conn=DriverManager.getConnection(DB_URL,DB_USE,DB_PWD);
			//读取数据库里的元素
			stmt=conn.prepareStatement(LODE_SQL);
			//数据库查询(执行查询语句，该语句返回单个ResultSet对象)
			rs=stmt.executeQuery();
			
			while(rs.next()) {
				//将玩家数据挂载至players上
				players.add(new Player(rs.getString(1),rs.getInt(2)));
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			try {
				//关闭流
				if(conn!=null) conn.close();
				if(stmt!=null) stmt.close();
				if(rs!=null) rs.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
	 
		return players;
	}

	//存入数据到数据库
	@Override
	public void saveData(Player players) {
		//用于连接数据库
		Connection conn=null;
		//操作数据库
		PreparedStatement stmt=null;
		
		try {
			//连接数据库
			conn=DriverManager.getConnection(DB_URL,DB_USE,DB_PWD);
			//将数据写入数据库
			stmt=conn.prepareStatement(SAVE_SQL);
			
			//写入玩家的姓名
			stmt.setObject(1,players.getName());
			//写入玩家的分数
			stmt.setObject(2,players.getPoint());
			//写入标号
			stmt.setObject(3,1);
			//执行以上语句(可以返回多个结果)
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				//关闭流
				if(conn!=null) conn.close();
				if(stmt!=null) stmt.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
