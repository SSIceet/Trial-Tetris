package J_ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class LayerAbout extends Layer {
	
	
	
	private static int Windowsize=5;
	
	public LayerAbout(int x,int y,int w,int h) {
		super(x,y,w,h);
	}
	 
	public void paint(Graphics g) {
		this.DrawWindow(g);
		drawImageAtCenter(Img.About_Img,g);
	}
}
