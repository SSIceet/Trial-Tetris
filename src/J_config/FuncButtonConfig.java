package J_config;

import org.dom4j.Element;

public class FuncButtonConfig {

	private final int buttonW;
	
	private final int buttonH;
	
	private final int eliminationX;
	
	private final int eliminationY;
	
	private final int timestopX;
	
	private final int timestopY;
	
	private final int ultimateX;
	
	private final int ultimateY;
	
	public FuncButtonConfig(Element functionButton) {
		//获取按钮的宽
		this.buttonW = Integer.parseInt(functionButton.attributeValue("w"));
		//获取按钮的高
		this.buttonH = Integer.parseInt(functionButton.attributeValue("h"));
		 
		this.eliminationX = Integer.parseInt(functionButton.element("elimination").attributeValue("x"));
		
		this.eliminationY = Integer.parseInt(functionButton.element("elimination").attributeValue("y"));
		
		this.timestopX = Integer.parseInt(functionButton.element("timestop").attributeValue("x"));
		
		this.timestopY = Integer.parseInt(functionButton.element("timestop").attributeValue("y"));
		
		this.ultimateX = Integer.parseInt(functionButton.element("ultimate").attributeValue("x"));
		
		this.ultimateY = Integer.parseInt(functionButton.element("ultimate").attributeValue("y"));
		//y=524为正中
	}

	public int getButtonW() {
		return buttonW;
	}

	public int getButtonH() {
		return buttonH;
	}

	public int getEliminationX() {
		return eliminationX;
	}

	public int getEliminationY() {
		return eliminationY;
	}

	public int getTimestopX() {
		return timestopX;
	}

	public int getTimestopY() {
		return timestopY;
	}

	public int getUltimateX() {
		return ultimateX;
	}

	public int getUltimateY() {
		return ultimateY;
	}
	
}
