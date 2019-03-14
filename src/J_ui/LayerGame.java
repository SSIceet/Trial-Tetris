package J_ui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

import J_config.GameConfig;
import J_entity.GameAct;

@SuppressWarnings("unused")
public class LayerGame extends Layer {
	
	//������ɫ���
	 private int ActNumber;
	 //32��λ������5(2��5�η�)
	 private static int ACT_Size=GameConfig.getFrame_Config().getACT_Size();
	 //��߾�
	 private static final int LeftSize=GameConfig.getSystem_Config().getMAX_X();
	 //�ұ߾�
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
			//��÷������
			 Point[] points=act.getPointAct();
			 //������Ӱ
			 drawShadow(points,g);
			 //���ƻ����
			 this.drawMainAct(points,g); 
		 }
		 //��������ͼ
		 this.drawMap(g);
		 
		 if(this.gamedto.isPause()) {
			this.drawImageAtCenter(Img.Pause,g);
		 }
		 
	 }
	 
	 /*
	  * ���Ƶ�ͼ
	  * */
	 private void drawMap(Graphics g) {
		 //���Ƶ�ͼ
		 boolean[][] map=this.gamedto.getGameMap();
		 //���㵱ǰ�ѻ���ɫ
		 ActNumber = this.gamedto.getNowLevel()==0 ? 0 : (this.gamedto.getNowLevel()-1)%7+1;
		 //TODO �����Ϸʧ�� ActNumber=8
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
	  * ���ƻ����
	  * */
	private void drawMainAct(Point[] points,Graphics g) {
		 //��÷�����
		 int typecode=this.gamedto.getGameAct().getTypecode();
		 
		 //���Ʒ���
		 for(int i=0;i<points.length;i++) {
			 DrawActByPoint(points[i].x,points[i].y,typecode+1,g);
		 }
	}

	/*
	  * ������Ӱ����
	  * */
	 private void drawShadow(Point[] points,Graphics g) {
		 //�жϿ����Ƿ���
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
		//==============�����÷���================
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
