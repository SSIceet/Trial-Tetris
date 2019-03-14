package J_ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import J_config.GameConfig;

public class LayerPoint extends Layer {

	 
	 //�÷����λ��
	 private final int PointBit=5;
	 //����X����
	 private final int Point_X;
	 //����Y����
	 private final int Point_Y;
	 //ֵ��Y����
	 private final int expY;
	 //��������
	 private static final int Level_up=GameConfig.getSystem_Config().getLevel_up();
	 
	 public LayerPoint(int x,int y,int w,int h) {
		 super(x,y,w,h);
		 //��ʼ��������ʾ��X����
		 this.Point_X=this.w-Number_w*PointBit-Margin;
		 //��ʼ��������ʾ��Y����
		 this.Point_Y=Margin;
		 //��ʼ��ֵ�۵�Y����
		 this.expY=Margin+PointAdd;
		
	 }
	 
	 public void paint(Graphics g) {
		 this.DrawWindow(g);
		 g.drawImage(Img.PI_2_img, this.x+Margin, this.y+Margin, null);
		 //g.drawImage(PI_img, this.x+Margin, this.y+Margin+PointAdd, null);
		 this.drawNumberLeftPad(Point_X, Point_Y, this.gamedto.getNowPoint(), PointBit, g);
		 //��ȡ������
		 int rmLine=this.gamedto.getNowRemoveLine();
		 //���ø��෽������ֵ��
		 drawRect(expY,"��һ��","",(double)(rmLine%Level_up)/(double)Level_up,g);
	 }
	 
}
