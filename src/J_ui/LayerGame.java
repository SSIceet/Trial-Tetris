package J_ui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

import J_config.GameConfig;
import J_entity.GameAct;

@SuppressWarnings("unused")
public class LayerGame extends Layer {
	
	//方块颜色编号
	 private int ActNumber;
	 //32的位移量是5(2的5次方)
	 private static int ACT_Size=GameConfig.getFrame_Config().getACT_Size();
	 //左边距
	 private static final int LeftSize=GameConfig.getSystem_Config().getMAX_X();
	 //右边距
	 private static final int RightSize=GameConfig.getSystem_Config().getMIN_X();
	 
	 private static final int windowsize = GameConfig.getFrame_Config().getWindowsize();
	 
	 private static final int Lost_idx = GameConfig.getFrame_Config().getLoseIdx();
	
	 public LayerGame(int x,int y,int w,int h) {
		 super(x,y,w,h);
	 }
	 
	 public void paint(Graphics g) {
		 this.DrawWindow(g);
		 GameAct act = this.gamedto.getGameAct();
		 if(act!=null) {
			//获得方块对象
			 Point[] points=act.getPointAct();
			 //绘制阴影
			 drawShadow(points,g);
			 //绘制活动方块
			 this.drawMainAct(points,g); 
		 }
		 //绘制主地图
		 this.drawMap(g);
		 
		 if(this.gamedto.isPause()) {
			this.drawImageAtCenter(Img.Pause,g);
		 }
		 
	 }
	 
	 /*
	  * 绘制地图
	  * */
	 private void drawMap(Graphics g) {
		 //绘制地图
		 boolean[][] map=this.gamedto.getGameMap();
		 //计算当前堆积颜色
		 ActNumber = this.gamedto.getNowLevel()==0 ? 0 : (this.gamedto.getNowLevel()-1)%7+1;
		 //TODO 如果游戏失败 ActNumber=8
		 if(!this.gamedto.isStart()) {
			 ActNumber = Lost_idx;
		 }
		 for(int x=0;x<map.length;x++) {
			 for(int y=0;y<map[x].length;y++) {
				 if(map[x][y]) {
					 DrawActByPoint(x,y,ActNumber,g);
				 } 
			 }
		 }
		
	}

	 /*
	  * 绘制活动方块
	  * */
	private void drawMainAct(Point[] points,Graphics g) {
		 //获得方块编号
		 int typecode=this.gamedto.getGameAct().getTypecode();
		 
		 //绘制方块
		 for(int i=0;i<points.length;i++) {
			 DrawActByPoint(points[i].x,points[i].y,typecode+1,g);
		 }
	}

	/*
	  * 绘制阴影方法
	  * */
	 private void drawShadow(Point[] points,Graphics g) {
		 //判断开关是否开启
		 if(this.gamedto.isShowShadow()) {
			 return;
		 }
		 
		int leftX=LeftSize;
		int rightX=RightSize;
		for(Point p : points) {
			leftX = p.x < leftX ? p.x : leftX;
			rightX = p.x > rightX ? p.x : rightX;
		}
		
		g.drawImage(Img.Shadow,this.x+Size+(leftX<<ACT_Size),this.y+Size,(rightX-leftX+1)<<ACT_Size,this.h-(Size<<1),null);
		//==============测试用方法================
		//System.out.println(leftX+" "+rightX);
	}

	
	private void DrawActByPoint(int x,int y,int ImageDx,Graphics g) {
		
		ImageDx = this.gamedto.isStart() ? ImageDx : Lost_idx;
		
		 g.drawImage(Img.ACT, this.x+(x<<ACT_Size)+windowsize,
				 this.y+windowsize+(y<<ACT_Size),
				 this.x+(x<<ACT_Size)+(1<<ACT_Size)+windowsize, 
				 this.y+(y<<ACT_Size)+(1<<ACT_Size)+windowsize,
				 ImageDx<<ACT_Size, 0, ((ImageDx+1)<<ACT_Size),1<<ACT_Size, null);
	 }
	 
}
