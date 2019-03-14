package J_ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class LayerDataDisk extends LayerData {
	 
	 public LayerDataDisk(int x,int y,int w,int h) {
		 super(x,y,w,h);
	 }
	 
	 public void paint(Graphics g) {
		 this.DrawWindow(g);
		 this.showData(Img.DD_img, this.gamedto.getDiskRecode(), g);
	 }
}
