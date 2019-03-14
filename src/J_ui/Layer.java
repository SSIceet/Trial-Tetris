package J_ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import J_config.FrameConfig;
import J_config.GameConfig;
import J_dto.GameDto;

public abstract class Layer {
	
	static FrameConfig Fcfg=GameConfig.getFrame_Config();
	
	protected int x,y,w,h;
	//ֵ�����о���
	protected static final int PointAdd=Fcfg.getPointAdd();
	//��ͼ�ര�ھ���
	protected static final int Margin=Fcfg.getMargin();
	//���ں��
	protected static final int Size=Fcfg.getWindowsize();
	//����ͼƬ���
	private static int Window_w=Img.Window_Img.getWidth(null);
	//����ͼƬ�߶�
	private static int Window_h=Img.Window_Img.getHeight(null);
	
	
	//��������ͼƬ�Ŀ��
	protected static final int Number_w= Img.NB_img.getWidth(null)/10;
	//����ͼƬ�ĸ߶�
	protected static final int Number_h=Img.NB_img.getHeight(null);
	
	
	
	//��ֵ��ͼƬ�ĸ߶�
	protected static final int  Level_H=Img.Level_img.getHeight(null);
	//��ֵ��ͼƬ�Ŀ��
	protected static final int  Level_W=Img.Level_img.getWidth(null);
	//ֵ�۵Ŀ��
	private final int expW;
	
	
	//��ʼ�����ݿ�
	protected GameDto gamedto=null;
	
	protected Layer(int x,int y,int w,int h) {
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
		//��ʼ��ֵ�۵Ŀ��
		this.expW=this.w-(Margin<<1);
	}
	
	protected void DrawWindow(Graphics g) {
		//g.drawImage(Window_Img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, null);
		/*���Ͻ�*/
		g.drawImage(Img.Window_Img, x, y, x+Size, y+Size, 0, 0, Size, Size, null);
		/*�ϱ߿�*/
		g.drawImage(Img.Window_Img, x+Size, y, w-Size+x, y+Size, Size, 0, Window_w-Size, Size, null);
		/*���Ͻ�*/
		g.drawImage(Img.Window_Img, w-Size+x, y, w+x, y+Size, Window_w-Size, 0, Window_w, Size, null);
		/*��߿�*/
		g.drawImage(Img.Window_Img, x, y+Size, x+Size, h-Size+y, 0, Size, Size, Window_h-Size, null);
		/*�м䲿��*/
		g.drawImage(Img.Window_Img, x+Size, y+Size, w-Size+x, h-Size+y, Size, Size, Window_w-Size, Window_h-Size, null);
		/*�ұ߿�*/
		g.drawImage(Img.Window_Img, w-Size+x, y+Size, w+x, h-Size+y, Window_w-Size, Size, Window_w, Window_h-Size, null);
		/*���½�*/
		g.drawImage(Img.Window_Img, x, h-Size+y, x+Size, h+y, 0, Window_h-Size, Size, Window_h, null);
		/*�±߿�*/
		g.drawImage(Img.Window_Img, x+Size, h-Size+y, w-Size+x, h+y, Size, Window_h-Size, Window_w-Size, Window_h, null);
		/*���½�*/
		g.drawImage(Img.Window_Img, w-Size+x, h-Size+y, w+x, h+y, Window_w-Size, Window_h-Size, Window_w, Window_h, null);
	}
	
	abstract public void paint(Graphics g);
	
	
	public void setGamedto(GameDto gamedto) {
		this.gamedto = gamedto;
	}
	
	
	/*
	 * x ���Ͻ�X����
	 * y ���Ͻ�Y����
	 * num Ҫ��ʾ������
	 * g ���ʶ���
	 * */
	protected void drawNumberLeftPad(int x,int y,int num,int maxBit,Graphics g) {
		//��Ҫ��ӡ������ת�����ַ���
		String strNum=Integer.toString(num);
		//ѭ��������ֵ�Ҷ��루����䣩
		for(int i=0;i<maxBit;i++) {
			//�ж��Ƿ������������
			if(maxBit-i<=strNum.length()) {
				//����������ַ����е��±�
				int idx=i-maxBit+strNum.length();
				//������number�е�ÿһλȡ��
				int bit=strNum.charAt(idx)-'0';
				//��������
				g.drawImage(Img.NB_img, this.x+x+Number_w*i, this.y+y, this.x+x+Number_w*(i+1), this.y+y+Number_h
						, bit*Number_w, 0, (bit+1)*Number_w, Number_h, null);
			}
		}
		
	}
	
	//����ֵ��
	 public void drawRect(int y,String title,String number,double ratio,Graphics g) {
		 int expX=this.x+Margin;
		 int expY=this.y+y;
		 int expH=30;
		 //�������
		 int Numspacing=7;
		 
		 //����ֵ�۱���
		 g.setColor(Color.black);
		 g.fillRect(expX, expY, expW, expH);
		 g.setColor(Color.white);
		 g.fillRect(expX+1, expY+1, expW-2, expH-2);
		 g.setColor(Color.black);
		 g.fillRect(expX+2, expY+2, expW-4, expH-4);
		 //����ֵ��
		 //������
		 int EXW=(int)(ratio*(expW-4));
		 //�����ɫ
		 int subIdx=(int)(ratio*Level_W)-1;
		 g.drawImage(Img.Level_img, 
				 expX+2,
				 expY+2, 
				 expX+EXW+2,
				 expY+expH-2, 
				 subIdx, 0, subIdx+1, Level_H, 
				 null);
		 
		 g.setColor(Color.white);
		 g.drawString(title, expX+6, expY+19);
		 
		 if(number!=null) {
			 for(int i=0;i<5;i++) {
				 if(5-i>number.length());
				 else {
					 g.drawString(number,expX+265+i*Numspacing, expY+19);
					 break;
				 }
			 }
			  
		 }
		 
	 }
	 
	 
	 //���л�ͼ
	 protected void drawImageAtCenter(Image img,Graphics g) {
			int img_width=img.getWidth(null);
			int img_height=img.getHeight(null);
			int img_x=this.w-img_width>>1;
			int img_y=this.h-img_height>>1;
			g.drawImage(img, this.x+img_x, this.y+img_y, null);
		}

}
