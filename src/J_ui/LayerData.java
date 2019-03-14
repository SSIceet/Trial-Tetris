package J_ui;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import J_config.GameConfig;
import J_dto.Player;

public abstract class LayerData extends Layer {

	//TODO�����ļ�
	//���������
	private static final int MaxRow=GameConfig.getData_Config().getMaxRow();
	//��ʼY����
	private static int StayY=0;
			
	private static final int Rect_H=Level_H+4;
	//���
	private static int SPA=0;
			
			
	
	protected LayerData(int x, int y, int w, int h) {
		super(x, y, w, h);
		SPA=(this.h-Rect_H*5-(Margin<<1)-Img.DB_img.getHeight(null))/MaxRow;
		StayY=Margin+Img.DB_img.getHeight(null)+SPA;
	}

	
	/*
	 * ��������ֵ��
	 * imgTitle:����ͼƬ
	 * players:����Դ
	 * Graphics g:����
	 * */
	
	public void showData(Image imgTitle, List<Player> players, Graphics g) {
		//���Ʊ���
		g.drawImage(imgTitle,this.x+Margin,this.y+Margin,null);
		//������ڷ���
		int nowpoint=this.gamedto.getNowPoint();
		//ѭ�����Ƽ�¼
		for(int i=0;i<MaxRow;i++) {
			//���һ����Ҽ�¼
			Player p=players.get(i);
			//��øü�¼����
			int recodePoint=p.getPoint();
			//�������ڷ������¼�����ı�ֽ
			double ratio =(double)nowpoint/recodePoint;
			//����Ƽ�¼�����ֵ��Ϊ100%
			ratio = ratio>1 ? 1.0 : ratio;
			String strPoint = recodePoint==0? null : Integer.toString(recodePoint);
			//���Ƶ�����¼
			drawRect(StayY+i*(Rect_H + SPA),p.getName(),strPoint,ratio,g);
		}
		
	}
	
	@Override
	//���󷽷�
	abstract public void paint(Graphics g);

}
