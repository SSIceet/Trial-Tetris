package J_util;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class FrameUtil {

	public static void setFrame(JFrame JF) {
		
		/*
		 * 窗口居中
		 * */
		/*Obtain screen attribute and set window location*/
		Toolkit toolkit=Toolkit.getDefaultToolkit();
		Dimension screen=toolkit.getScreenSize();
		/*java里面先乘除后加减再位移*/
		/*右位移1表示除2，右位移2表示除4，3表示除8，左位移则表示乘*/
		int x=screen.width-JF.getWidth()>>1;
		int y=(screen.height-JF.getHeight()>>1)-10;
		JF.setLocation(x, y);
		
	}
	
}
