package J_entity;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import J_config.GameConfig;

public class GameAct {

	private Point[] pointActs;
	
	//������
	private int Typecode;
	
	private final static int MIN_X = GameConfig.getSystem_Config().getMIN_X();
	private final static int MAX_X = GameConfig.getSystem_Config().getMAX_X();
	private final static int MIN_Y = GameConfig.getSystem_Config().getMIN_Y();
	private final static int MAX_Y = GameConfig.getSystem_Config().getMAX_Y();
	
	private final static List<Point[]> Type_config = GameConfig.getSystem_Config().getTypeConfig();
	
	private final static List<Boolean> Type_Round = GameConfig.getSystem_Config().getTypeRound();
	
	public GameAct(int Typecode) {
		this.InitAct(Typecode);
	}
	
	public void InitAct(int Typecode) {
		//TODO ����Actcode��ֵ��ˢ�·���
		this.Typecode=Typecode;
		Point[] points=Type_config.get(Typecode);
		pointActs=new Point[points.length]; 
				for(int i=0;i<points.length;i++) {
					pointActs[i]=new Point(points[i].x,points[i].y);
				}
	}

	public Point[] getPointAct() {
		return pointActs;
	}
	
	public boolean Move(int moveX,int moveY,boolean[][] gameMap) {
		//�жϷ����Ƿ����ƶ�
		for(int i=0;i<pointActs.length;i++) {
			int newX=pointActs[i].x+moveX;
			int newY=pointActs[i].y+moveY;
			if(isOverMap(newX,newY,gameMap)) {
				return false;
			}	
		}
		for(int i=0;i<pointActs.length;i++) {
			pointActs[i].x+=moveX;
			pointActs[i].y+=moveY;
		}
		return true;
	}
	
	/*
	 * ������ת
	 * (�ѿ�������ϵ��ת����ת�ĽǶ�Ϊ90��)
	 * ˳ʱ��
	 * A.x=0.y+0.x-B.y
	 * A.y=0.y-0.x+B.x
	 * */
	public void rotate(boolean[][] gameMap) {
		if(!Type_Round.get(this.Typecode)) {
			return;
		}
		//�жϷ����Ƿ�����ת
		for(int i=1;i<pointActs.length;i++) {
			int newX=pointActs[0].y+pointActs[0].x-pointActs[i].y;
			int newY=pointActs[0].y-pointActs[0].x+pointActs[i].x;
			if(isOverMap(newX,newY,gameMap)) {
				return;
			}
		}
		for(int i=1;i<pointActs.length;i++) {
			int BoxX=pointActs[0].y+pointActs[0].x-pointActs[i].y;
			int BoxY=pointActs[0].y-pointActs[0].x+pointActs[i].x;
			pointActs[i].x=BoxX;
			pointActs[i].y=BoxY;
		}
	}
	
	
	private boolean isOverMap(int x,int y,boolean[][] gameMap) {
		return x<MIN_X||x>MAX_X||y<MIN_Y||y>MAX_Y||gameMap[x][y];
	}
	
	public int getTypecode() {
		return Typecode;
	}
	
}
