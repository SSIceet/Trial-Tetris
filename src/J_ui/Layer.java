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
	//值槽上行距离
	protected static final int PointAdd=Fcfg.getPointAdd();
	//贴图距窗口距离
	protected static final int Margin=Fcfg.getMargin();
	//窗口厚度
	protected static final int Size=Fcfg.getWindowsize();
	//窗口图片宽度
	private static int Window_w=Img.Window_Img.getWidth(null);
	//窗口图片高度
	private static int Window_h=Img.Window_Img.getHeight(null);
	
	
	//单个数字图片的宽度
	protected static final int Number_w= Img.NB_img.getWidth(null)/10;
	//数字图片的高度
	protected static final int Number_h=Img.NB_img.getHeight(null);
	
	
	
	//求值槽图片的高度
	protected static final int  Level_H=Img.Level_img.getHeight(null);
	//求值槽图片的宽度
	protected static final int  Level_W=Img.Level_img.getWidth(null);
	//值槽的宽度
	private final int expW;
	
	
	//初始化数据库
	protected GameDto gamedto=null;
	
	protected Layer(int x,int y,int w,int h) {
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
		//初始化值槽的宽度
		this.expW=this.w-(Margin<<1);
	}
	
	protected void DrawWindow(Graphics g) {
		//g.drawImage(Window_Img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, null);
		/*左上角*/
		g.drawImage(Img.Window_Img, x, y, x+Size, y+Size, 0, 0, Size, Size, null);
		/*上边框*/
		g.drawImage(Img.Window_Img, x+Size, y, w-Size+x, y+Size, Size, 0, Window_w-Size, Size, null);
		/*右上角*/
		g.drawImage(Img.Window_Img, w-Size+x, y, w+x, y+Size, Window_w-Size, 0, Window_w, Size, null);
		/*左边框*/
		g.drawImage(Img.Window_Img, x, y+Size, x+Size, h-Size+y, 0, Size, Size, Window_h-Size, null);
		/*中间部分*/
		g.drawImage(Img.Window_Img, x+Size, y+Size, w-Size+x, h-Size+y, Size, Size, Window_w-Size, Window_h-Size, null);
		/*右边框*/
		g.drawImage(Img.Window_Img, w-Size+x, y+Size, w+x, h-Size+y, Window_w-Size, Size, Window_w, Window_h-Size, null);
		/*左下角*/
		g.drawImage(Img.Window_Img, x, h-Size+y, x+Size, h+y, 0, Window_h-Size, Size, Window_h, null);
		/*下边框*/
		g.drawImage(Img.Window_Img, x+Size, h-Size+y, w-Size+x, h+y, Size, Window_h-Size, Window_w-Size, Window_h, null);
		/*右下角*/
		g.drawImage(Img.Window_Img, w-Size+x, h-Size+y, w+x, h+y, Window_w-Size, Window_h-Size, Window_w, Window_h, null);
	}
	
	abstract public void paint(Graphics g);
	
	
	public void setGamedto(GameDto gamedto) {
		this.gamedto = gamedto;
	}
	
	
	/*
	 * x 左上角X坐标
	 * y 左上角Y坐标
	 * num 要显示的数字
	 * g 画笔对象
	 * */
	protected void drawNumberLeftPad(int x,int y,int num,int maxBit,Graphics g) {
		//把要打印的数字转换成字符串
		String strNum=Integer.toString(num);
		//循环绘制数值右对齐（左填充）
		for(int i=0;i<maxBit;i++) {
			//判断是否满足绘制条件
			if(maxBit-i<=strNum.length()) {
				//获得数字在字符串中的下标
				int idx=i-maxBit+strNum.length();
				//把数字number中的每一位取出
				int bit=strNum.charAt(idx)-'0';
				//绘制数字
				g.drawImage(Img.NB_img, this.x+x+Number_w*i, this.y+y, this.x+x+Number_w*(i+1), this.y+y+Number_h
						, bit*Number_w, 0, (bit+1)*Number_w, Number_h, null);
			}
		}
		
	}
	
	//绘制值槽
	 public void drawRect(int y,String title,String number,double ratio,Graphics g) {
		 int expX=this.x+Margin;
		 int expY=this.y+y;
		 int expH=30;
		 //分数间距
		 int Numspacing=7;
		 
		 //绘制值槽背景
		 g.setColor(Color.black);
		 g.fillRect(expX, expY, expW, expH);
		 g.setColor(Color.white);
		 g.fillRect(expX+1, expY+1, expW-2, expH-2);
		 g.setColor(Color.black);
		 g.fillRect(expX+2, expY+2, expW-4, expH-4);
		 //绘制值槽
		 //求出宽度
		 int EXW=(int)(ratio*(expW-4));
		 //求出颜色
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
	 
	 
	 //正中绘图
	 protected void drawImageAtCenter(Image img,Graphics g) {
			int img_width=img.getWidth(null);
			int img_height=img.getHeight(null);
			int img_x=this.w-img_width>>1;
			int img_y=this.h-img_height>>1;
			g.drawImage(img, this.x+img_x, this.y+img_y, null);
		}

}
