package J_config;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

@SuppressWarnings("unused")
public class GameConfig {
	
	private static FrameConfig Frame_Config;
	
	private static DataConfig Data_Config;
	
	private static SystemConfig System_Config;
	
	static {
		
		try {
			/*创建XML读取器*/
			SAXReader reader=new SAXReader();
			/*获得整个一个。XML文件*/
			Document doc = reader.read("data/config/cfg.xml");
			/*获得.XML文件下的game目录*/
			Element game=doc.getRootElement();
			/*创建界面配置对象*/
			Frame_Config = new FrameConfig(game.element("frame"));
			/*创建系统配置对象*/
			Data_Config = new DataConfig(game.element("data"));
			/*创建数据访问配置对象*/
			System_Config = new SystemConfig(game.element("system"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/*构造器私有化*/
	private GameConfig() {
		
	}

	public static FrameConfig getFrame_Config() {
		return Frame_Config;
	}

	public static DataConfig getData_Config() {
		return Data_Config;
	}

	public static SystemConfig getSystem_Config() {
		return System_Config;
	}
	
	
	
}
