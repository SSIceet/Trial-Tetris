package J_ui;

import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import J_config.GameConfig;

public class Img {
	
	private Img() {}
	
	/*
	 * 图片路径
	 * 
	 * */
	public static final String GRAPHES_PATH = "Graphes";
	
	
	public static void setSkin(String path) {
		
		String SkinPath = GRAPHES_PATH + "/" + path; 
		
		/*
		 * 作者签名图片
		 * */
		About_Img=new ImageIcon(SkinPath+"/Other_Images/About.png").getImage();
		
		
		/*
		 * 窗口图片
		 * */
		Window_Img=new ImageIcon(SkinPath+"/Window_Images/window.png").getImage();
		
		
		/*
		 * 数字图片
		 * */
		NB_img=new ImageIcon(SkinPath+"/String_Images/Number.png").getImage();
		
		
		/*
		 * 值槽图片
		 * */
		Level_img=new ImageIcon(SkinPath+"/Other_Images/Level.png").getImage();
		
		
		/*
		 * 等级图片
		 * */
		LV_img=new ImageIcon(SkinPath+"/String_Images/LV.png").getImage();
		
		 /*
		  * 得分图片
		  * */
		 PI_2_img=new ImageIcon(SkinPath+"/String_Images/PI_2.png").getImage();
		
		
		/*
		 * 数据库图片
		 * */
		DB_img=new ImageIcon(SkinPath+"/String_Images/DB.png").getImage();
		
		
		/*
		 * 本地硬盘图片
		 * */
		DD_img=new ImageIcon(SkinPath+"/String_Images/DD.png").getImage();
		
		
		/*
		 * 方块图片
		 * */
		ACT=new ImageIcon(SkinPath+"/Square_Images/rect.png").getImage();
		
		
		/*
		 * 开始按钮图片
		 * */
		btnStart = new ImageIcon(SkinPath+"/String_Images/JButton_Start.png");
		
		
		/*
		 * 设置按钮图片
		 * */
		btnConfig = new ImageIcon(SkinPath+"/String_Images/JButton_Config.png");
		
		
		/*
		 * 消行按钮图片
		 * */
		btnElimination = new ImageIcon(SkinPath+"/String_Images/JButton_Elimination.png");
		
		
		/*
		 * 时停按钮图片
		 * */
		btnTimestop = new ImageIcon(SkinPath+"/String_Images/JButton_Timestop.png");
		
		
		/*
		 * 究极按钮图片
		 * */
		btnUltimate = new ImageIcon(SkinPath+"/String_Images/JButton_Ultimate.png");
		
		/*
		 * 阴影图片
		 * */
		Shadow=new ImageIcon(SkinPath+"/Square_Images/Shadow.png").getImage();
		
		/*
		 * 暂停图片
		 * */
		Pause = new ImageIcon(SkinPath+"/String_Images/Pause.png").getImage(); 
		
		/*
		 * 预览图片
		 * */
		//View = new ImageIcon(SkinPath+"/View.png").getImage();
		
		/*
		 * 手柄图片
		 * */
		//Handle = new ImageIcon(SkinPath+"/Other_Images/handle_2.jpg").getImage();
		
		// TODO
		// 下一个图片
		next_act = new Image[GameConfig.getSystem_Config().getTypeConfig().size()];
		for (int i = 0; i < next_act.length; i++) {
			next_act[i] = new ImageIcon(SkinPath + "/Square_Images/" + i + ".png").getImage();
		}

		// 背景图片数组
		File Bgdir = new File(SkinPath + "/Blackground");
		File[] files = Bgdir.listFiles();
		Bg_List = new ArrayList<Image>();
		// 遍历文件夹里的文件
		for (File file : files) {
			// 如果文件不是一个文件夹的话
			if (!file.isDirectory()) {
				Bg_List.add(new ImageIcon(file.getPath()).getImage());
			}
		}
		
	}
	
	/*
	 * 作者签名图片
	 * */
	public static Image About_Img=null;
	
	
	/*
	 * 窗口图片
	 * */
	public static Image Window_Img=null;
	
	
	/*
	 * 数字图片
	 * */
	public static Image NB_img=null;
	
	
	/*
	 * 值槽图片
	 * */
	public static Image Level_img=null;
	
	
	/*
	 * 等级图片
	 * */
	public static Image LV_img=null;
	
	 /*
	  * 得分图片
	  * */
	public static Image PI_2_img=null;
	
	
	/*
	 * 数据库图片
	 * */
	public static Image DB_img=null;
	
	
	/*
	 * 本地硬盘图片
	 * */
	public static Image DD_img=null;
	
	
	/*
	 * 方块图片
	 * */
	public static Image ACT=null;
	
	
	/*
	 * 开始按钮图片
	 * */
	public static ImageIcon btnStart = null;
	
	
	/*
	 * 设置按钮图片
	 * */
	public static ImageIcon btnConfig = null;
	
	/*
	 * 消行按钮图片
	 * */
	public static ImageIcon btnElimination = null;
	
	/*
	 * 时停按钮图片
	 * */
	public static ImageIcon btnTimestop = null;
	
	/*
	 * 究极按钮图片
	 * */
	public static ImageIcon btnUltimate = null;
	
	
	/*
	 * 下一个方块图片数组
	 * */
	public static Image[] next_act = null;
	
	
	/*
	 * 背景图片列表
	 * */
	public static List<Image> Bg_List = null;
	
	/*
	 * 阴影图片
	 * */
	public static Image Shadow=null;
	
	/*
	 * 暂停图片
	 * */
	public static Image Pause = null; 
	
	/*
	 * 预览图片
	 * */
	//public static Image View = null;
	
	/*
	 * 手柄图片
	 * */
	//	public static Image Handle = null;
	

	static {
		setSkin("Default");
	}
	
}
