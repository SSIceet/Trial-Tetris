package J_config;

import org.dom4j.Element;

public class ButtonConfig {

	private final int buttonW;
	
	private final int buttonH;
	
	private final int startX;
	
	private final int startY;
	
	private final int userConfigX;
	
	private final int userConfigY;

	public ButtonConfig(Element button) {
		//获取按钮的宽
		this.buttonW = Integer.parseInt(button.attributeValue("w"));
		//获取按钮的高
		this.buttonH = Integer.parseInt(button.attributeValue("h"));
		//获取“开始”按钮的X位置
		this.startX =  Integer.parseInt(button.element("start").attributeValue("x"));
		//获取“开始”按钮的Y位置
		this.startY =  Integer.parseInt(button.element("start").attributeValue("y"));
		//获取“控制”按钮的X位置
		this.userConfigX = Integer.parseInt(button.element("userConfig").attributeValue("x"));
		//获取“控制”按钮的Y位置
		this.userConfigY = Integer.parseInt(button.element("userConfig").attributeValue("y"));
		
	}

	public int getButtonW() {
		return buttonW;
	}

	public int getButtonH() {
		return buttonH;
	}

	public int getStartX() {
		return startX;
	}

	public int getStartY() {
		return startY;
	}

	public int getUserConfigX() {
		return userConfigX;
	}

	public int getUserConfigY() {
		return userConfigY;
	}
	
}
 