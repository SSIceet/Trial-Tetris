package J_ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import J_config.GameConfig;

public class LayerPoint extends Layer {

	 
	 //得分最大位数
	 private final int PointBit=5;
	 //分数X坐标
	 private final int Point_X;
	 //分数Y坐标
	 private final int Point_Y;
	 //值槽Y坐标
	 private final int expY;
	 //升级行数
	 private static final int Level_up=GameConfig.getSystem_Config().getLevel_up();
	 
	 public LayerPoint(int x,int y,int w,int h) {
		 super(x,y,w,h);
		 //初始化分数显示的X坐标
		 this.Point_X=this.w-Number_w*PointBit-Margin;
		 //初始化分数显示的Y坐标
		 this.Point_Y=Margin;
		 //初始化值槽的Y坐标
		 this.expY=Margin+PointAdd;
		
	 }
	 
	 public void paint(Graphics g) {
		 this.DrawWindow(g);
		 g.drawImage(Img.PI_2_img, this.x+Margin, this.y+Margin, null);
		 //g.drawImage(PI_img, this.x+Margin, this.y+Margin+PointAdd, null);
		 this.drawNumberLeftPad(Point_X, Point_Y, this.gamedto.getNowPoint(), PointBit, g);
		 //获取消行数
		 int rmLine=this.gamedto.getNowRemoveLine();
		 //调用父类方法绘制值槽
		 drawRect(expY,"下一级","",(double)(rmLine%Level_up)/(double)Level_up,g);
	 }
	 
}
