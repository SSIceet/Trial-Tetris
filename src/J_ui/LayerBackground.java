package J_ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class LayerBackground extends Layer {

	//private Image BG_img=new ImageIcon("Graphes/Blackground/5.jpg").getImage();
	public LayerBackground(int x,int y,int w,int h) {
		super(x,y,w,h);
	}
	@Override
	public void paint(Graphics g) {
		
		int ImgNumber=this.gamedto.getNowLevel()%Img.Bg_List.size();
		g.drawImage(Img.Bg_List.get(ImgNumber),0,0,null);
		
	}

}
