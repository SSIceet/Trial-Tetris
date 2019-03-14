package J_config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;

public class DataInterfaceConfig {

	private final String className;
	
	private final Map<String,String>param;
	
	public DataInterfaceConfig(Element DataInterfaceConfig) {
		
		//"attributeValue"得到指定元素的指定属性值
		//得到calssName元素里的属性值
		this.className = DataInterfaceConfig.attributeValue("className");
				
		this.param = new HashMap<String,String>();
		
		@SuppressWarnings("unchecked")
		List<Element> params = DataInterfaceConfig.elements("param");
		for(Element P : params) {
			//得到key元素和value元素里的属性值(设置key元素和value元素到param里)
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
