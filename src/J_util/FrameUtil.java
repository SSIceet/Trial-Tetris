package J_util;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class FrameUtil {

	public static void setFrame(JFrame JF) {
		
		/*
		 * ���ھ���
		 * */
		/*Obtain screen attribute and set window location*/
		Toolkit toolkit=Toolkit.getDefaultToolkit();
		Dimension screen=toolkit.getScreenSize();
		/*java�����ȳ˳���Ӽ���λ��*/
		/*��λ��1��ʾ��2����λ��2��ʾ��4��3��ʾ��8����λ�����ʾ��*/
		int x=screen.width-JF.getWidth()>>1;
		int y=(screen.height-JF.getHeight()>>1)-10;
		JF.setLocation(x, y);
		
	}
	
}
