package J_ui;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import J_config.GameConfig;
import J_dto.Player;

public abstract class LayerData extends Layer {

	//TODO配置文件
	//最大数据行
	private static final int MaxRow=GameConfig.getData_Config().getMaxRow();
	//起始Y坐标
	private static int StayY=0;
			
	private static final int Rect_H=Level_H+4;
	//间距
	private static int SPA=0;
			
			
	
	protected LayerData(int x, int y, int w, int h) {
		super(x, y, w, h);
		SPA=(this.h-Rect_H*5-(Margin<<1)-Img.DB_img.getHeight(null))/MaxRow;
		StayY=Margin+Img.DB_img.getHeight(null)+SPA;
	}

	
	/*
	 * 绘制所有值槽
	 * imgTitle:标题图片
	 * players:数据源
	 * Graphics g:画笔
	 * */
	
	public void showData(Image imgTitle, List<Player> players, Graphics g) {
		//绘制标题
		g.drawImage(imgTitle,this.x+Margin,this.y+Margin,null);
		//获得现在分数
		int nowpoint=this.gamedto.getNowPoint();
		//循环绘制记录
		for(int i=0;i<MaxRow;i++) {
			//获得一条玩家记录
			Player p=players.get(i);
			//获得该记录分数
			int recodePoint=p.getPoint();
			//计算现在分数与记录分数的壁纸
			double ratio =(double)nowpoint/recodePoint;
			//如果破记录，则比值设为100%
			ratio = ratio>1 ? 1.0 : ratio;
			String strPoint = recodePoint==0? null : Integer.toString(recodePoint);
			//绘制单条记录
			drawRect(StayY+i*(Rect_H + SPA),p.getName(),strPoint,ratio,g);
		}
		
	}
	
	@Override
	//抽象方法
	abstract public void paint(Graphics g);

}
