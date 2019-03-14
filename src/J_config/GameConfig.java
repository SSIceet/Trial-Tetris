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
			/*����XML��ȡ��*/
			SAXReader reader=new SAXReader();
			/*�������һ����XML�ļ�*/
			Document doc = reader.read("data/config/cfg.xml");
			/*���.XML�ļ��µ�gameĿ¼*/
			Element game=doc.getRootElement();
			/*�����������ö���*/
			Frame_Config = new FrameConfig(game.element("frame"));
			/*����ϵͳ���ö���*/
			Data_Config = new DataConfig(game.element("data"));
			/*�������ݷ������ö���*/
			System_Config = new SystemConfig(game.element("system"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/*������˽�л�*/
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
