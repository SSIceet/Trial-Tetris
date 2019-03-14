package J_config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;

public class DataInterfaceConfig {

	private final String className;
	
	private final Map<String,String>param;
	
	public DataInterfaceConfig(Element DataInterfaceConfig) {
		
		//"attributeValue"�õ�ָ��Ԫ�ص�ָ������ֵ
		//�õ�calssNameԪ���������ֵ
		this.className = DataInterfaceConfig.attributeValue("className");
				
		this.param = new HashMap<String,String>();
		
		@SuppressWarnings("unchecked")
		List<Element> params = DataInterfaceConfig.elements("param");
		for(Element P : params) {
			//�õ�keyԪ�غ�valueԪ���������ֵ(����keyԪ�غ�valueԪ�ص�param��)
			this.param.put(P.attributeValue("key"), P.attributeValue("value"));
		}
		
	}

	public String getClassName() {
		return className;
	}

	public Map<String, String> getParam() {
		return param;
	}
	 
}
