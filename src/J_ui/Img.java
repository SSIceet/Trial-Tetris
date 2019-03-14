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
	 * ͼƬ·��
	 * 
	 * */
	public static final String GRAPHES_PATH = "Graphes";
	
	
	public static void setSkin(String path) {
		
		String SkinPath = GRAPHES_PATH + "/" + path; 
		
		/*
		 * ����ǩ��ͼƬ
		 * */
		About_Img=new ImageIcon(SkinPath+"/Other_Images/About.png").getImage();
		
		
		/*
		 * ����ͼƬ
		 * */
		Window_Img=new ImageIcon(SkinPath+"/Window_Images/window.png").getImage();
		
		
		/*
		 * ����ͼƬ
		 * */
		NB_img=new ImageIcon(SkinPath+"/String_Images/Number.png").getImage();
		
		
		/*
		 * ֵ��ͼƬ
		 * */
		Level_img=new ImageIcon(SkinPath+"/Other_Images/Level.png").getImage();
		
		
		/*
		 * �ȼ�ͼƬ
		 * */
		LV_img=new ImageIcon(SkinPath+"/String_Images/LV.png").getImage();
		
		 /*
		  * �÷�ͼƬ
		  * */
		 PI_2_img=new ImageIcon(SkinPath+"/String_Images/PI_2.png").getImage();
		
		
		/*
		 * ���ݿ�ͼƬ
		 * */
		DB_img=new ImageIcon(SkinPath+"/String_Images/DB.png").getImage();
		
		
		/*
		 * ����Ӳ��ͼƬ
		 * */
		DD_img=new ImageIcon(SkinPath+"/String_Images/DD.png").getImage();
		
		
		/*
		 * ����ͼƬ
		 * */
		ACT=new ImageIcon(SkinPath+"/Square_Images/rect.png").getImage();
		
		
		/*
		 * ��ʼ��ťͼƬ
		 * */
		btnStart = new ImageIcon(SkinPath+"/String_Images/JButton_Start.png");
		
		
		/*
		 * ���ð�ťͼƬ
		 * */
		btnConfig = new ImageIcon(SkinPath+"/String_Images/JButton_Config.png");
		
		
		/*
		 * ���а�ťͼƬ
		 * */
		btnElimination = new ImageIcon(SkinPath+"/String_Images/JButton_Elimination.png");
		
		
		/*
		 * ʱͣ��ťͼƬ
		 * */
		btnTimestop = new ImageIcon(SkinPath+"/String_Images/JButton_Timestop.png");
		
		
		/*
		 * ������ťͼƬ
		 * */
		btnUltimate = new ImageIcon(SkinPath+"/String_Images/JButton_Ultimate.png");
		
		/*
		 * ��ӰͼƬ
		 * */
		Shadow=new ImageIcon(SkinPath+"/Square_Images/Shadow.png").getImage();
		
		/*
		 * ��ͣͼƬ
		 * */
		Pause = new ImageIcon(SkinPath+"/String_Images/Pause.png").getImage(); 
		
		/*
		 * Ԥ��ͼƬ
		 * */
		//View = new ImageIcon(SkinPath+"/View.png").getImage();
		
		/*
		 * �ֱ�ͼƬ
		 * */
		//Handle = new ImageIcon(SkinPath+"/Other_Images/handle_2.jpg").getImage();
		
		// TODO
		// ��һ��ͼƬ
		next_act = new Image[GameConfig.getSystem_Config().getTypeConfig().size()];
		for (int i = 0; i < next_act.length; i++) {
			next_act[i] = new ImageIcon(SkinPath + "/Square_Images/" + i + ".png").getImage();
		}

		// ����ͼƬ����
		File Bgdir = new File(SkinPath + "/Blackground");
		File[] files = Bgdir.listFiles();
		Bg_List = new ArrayList<Image>();
		// �����ļ�������ļ�
		for (File file : files) {
			// ����ļ�����һ���ļ��еĻ�
			if (!file.isDirectory()) {
				Bg_List.add(new ImageIcon(file.getPath()).getImage());
			}
		}
		
	}
	
	/*
	 * ����ǩ��ͼƬ
	 * */
	public static Image About_Img=null;
	
	
	/*
	 * ����ͼƬ
	 * */
	public static Image Window_Img=null;
	
	
	/*
	 * ����ͼƬ
	 * */
	public static Image NB_img=null;
	
	
	/*
	 * ֵ��ͼƬ
	 * */
	public static Image Level_img=null;
	
	
	/*
	 * �ȼ�ͼƬ
	 * */
	public static Image LV_img=null;
	
	 /*
	  * �÷�ͼƬ
	  * */
	public static Image PI_2_img=null;
	
	
	/*
	 * ���ݿ�ͼƬ
	 * */
	public static Image DB_img=null;
	
	
	/*
	 * ����Ӳ��ͼƬ
	 * */
	public static Image DD_img=null;
	
	
	/*
	 * ����ͼƬ
	 * */
	public static Image ACT=null;
	
	
	/*
	 * ��ʼ��ťͼƬ
	 * */
	public static ImageIcon btnStart = null;
	
	
	/*
	 * ���ð�ťͼƬ
	 * */
	public static ImageIcon btnConfig = null;
	
	/*
	 * ���а�ťͼƬ
	 * */
	public static ImageIcon btnElimination = null;
	
	/*
	 * ʱͣ��ťͼƬ
	 * */
	public static ImageIcon btnTimestop = null;
	
	/*
	 * ������ťͼƬ
	 * */
	public static ImageIcon btnUltimate = null;
	
	
	/*
	 * ��һ������ͼƬ����
	 * */
	public static Image[] next_act = null;
	
	
	/*
	 * ����ͼƬ�б�
	 * */
	public static List<Image> Bg_List = null;
	
	/*
	 * ��ӰͼƬ
	 * */
	public static Image Shadow=null;
	
	/*
	 * ��ͣͼƬ
	 * */
	public static Image Pause = null; 
	
	/*
	 * Ԥ��ͼƬ
	 * */
	//public static Image View = null;
	
	/*
	 * �ֱ�ͼƬ
	 * */
	//	public static Image Handle = null;
	

	static {
		setSkin("Default");
	}
	
}
