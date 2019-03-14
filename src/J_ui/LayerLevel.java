package J_ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class LayerLevel extends Layer {
	
	//µÈ¼¶Í¼Æ¬¿í¶È
	private static final int LV_image_width=Img.LV_img.getWidth(null);
	
	public LayerLevel(int x,int y,int w,int h) {
		super(x,y,w,h);
	}
	@Override
	public void paint(Graphics g) {
		this.DrawWindow(g);
		int Xcenter=(this.w-LV_image_width>>1);
		g.drawImage(Img.LV_img,this.x+Xcenter, this.y+Margin, null);
		this.drawNumberLeftPad(Xcenter, 65, this.gamedto.getNowLevel(), 2, g);
	}
	
}
