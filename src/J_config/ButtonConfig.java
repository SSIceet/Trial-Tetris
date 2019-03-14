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
		//��ȡ��ť�Ŀ�
		this.buttonW = Integer.parseInt(button.attributeValue("w"));
		//��ȡ��ť�ĸ�
		this.buttonH = Integer.parseInt(button.attributeValue("h"));
		//��ȡ����ʼ����ť��Xλ��
		this.startX =  Integer.parseInt(button.element("start").attributeValue("x"));
		//��ȡ����ʼ����ť��Yλ��
		this.startY =  Integer.parseInt(button.element("start").attributeValue("y"));
		//��ȡ�����ơ���ť��Xλ��
		this.userConfigX = Integer.parseInt(button.element("userConfig").attributeValue("x"));
		//��ȡ�����ơ���ť��Yλ��
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
 