package J_ui;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;

import J_dto.Player;

public class LayerDataBase extends LayerData {
	
	public LayerDataBase(int x,int y,int w,int h) {
		super(x,y,w,h);
	}
	 
	public void paint(Graphics g) {
		this.DrawWindow(g);
		this.showData(Img.DB_img, this.gamedto.getDbRecode(), g);
	}
}
