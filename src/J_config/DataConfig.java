package J_config;

import org.dom4j.Element;

public class DataConfig {

	private final int MaxRow;
	private final DataInterfaceConfig dataA;
	private final DataInterfaceConfig dataB;
	
	public DataConfig(Element data) {
		
		//element用来构建xml中的节点
		this.MaxRow = Integer.parseInt(data.attributeValue("MaxRow"));
		this.dataA = new DataInterfaceConfig(data.element("dataA"));
		this.dataB = new DataInterfaceConfig(data.element("dataB"));
		
	}

	public DataInterfaceConfig getDataA() {
		return dataA;
	}

	public DataInterfaceConfig getDataB() {
		return dataB;
	}

	public int getMaxRow() {
		return MaxRow;
	}
	
	
}
