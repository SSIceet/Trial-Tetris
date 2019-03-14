package J_ui.windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import J_control.GameControl;
import J_ui.Img;
import J_util.FrameUtil;

public class FrameConfig extends JFrame{
	//确定按钮
	private JButton OK = new JButton("确定");
	//取消按钮
	private JButton cancel = new JButton("取消");
	//应用按钮
	private JButton application = new JButton("应用");
	//手柄图片
	private final static Image Img_handle = new ImageIcon("Graphes/Default/Other_Images/handle_2.jpg").getImage();
	//八个按键数组
	private TextCtrl keytext[] = new TextCtrl[8];
	//预览图列表
	private Image[] skinViewList = null;
	//八个按键的功能位置
	private final static String[] Method_Name = {
		
		"keyUp","keyLeft","keyDown","keyRight","keyFunLeft","keyFunUp","keyFunRight","keyFunDown"
		
	};
	//游戏设置文件路径
	private final static String Path="data/control.dat";
	
	//错误框
	private JLabel error = new JLabel();
	
	//皮肤列表
	private JList skinList = null;
	
	//皮肤预览图面板
	private JPanel skinView = null;
	
	//列表元素
	private DefaultListModel skinData = new DefaultListModel();
	
	private GameControl gameControl;
	
	public FrameConfig(GameControl gameControl) {
		//获得游戏控制器对象
		this.gameControl = gameControl;
		//设置标题
		this.setTitle("按键设置");
		//设置布局管理器为边界布局
		this.setLayout(new BorderLayout()); 
		//使窗口的小叉无效化
		//this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		//隐藏标题栏
		this.setUndecorated(true);
		//初始化八个按键的显示位置
		this.initKeyText();
		//添加主面板
		this.add(this.creatMainPanel(),BorderLayout.CENTER);
		//添加按钮面板
		this.add(this.creatButtonPanel(),BorderLayout.SOUTH);
		//锁定窗口大小
		this.setResizable(false);
		//窗口大小
		this.setSize(800,455);
		//居中
		FrameUtil.setFrame(this);
		
	}

	/*
	 * 创建按钮面板
	 * */
	private JPanel creatButtonPanel() {
		
		JPanel JBut = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		error.setForeground(Color.red);
		JBut.add(error);
		
		this.OK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(writeConfig()) {
					setVisible(false);
					gameControl.setOver();
				}
				
			}
		});
		
		JBut.add(OK);
		
		this.cancel.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				gameControl.setOver();
			}
			
		});
		
		JBut.add(cancel);
		
		this.application.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				writeConfig();
				gameControl.repaint();
			}
		});
		
		JBut.add(application);
		
		return JBut;
	}

	/*
	 * 写入游戏配置(应用按钮)
	 * */
	protected boolean writeConfig() {
		//创建哈希对应数组
		HashMap<Integer,String> keySet = new HashMap<Integer,String>();
		//设置八个按键对应的功能位置
		for(int i = 0 ; i < keytext.length ; i++) {
			
			int keycode = this.keytext[i].getKeyCode();
			if(keycode == 0) {
				this.error.setText("无效按键");
				return false;
			}
			keySet.put(keycode,this.keytext[i].getMethodName());
		}
		
		if(keySet.size() != 8) {
			this.error.setText("重复按键");
			return false;
		}
		
		try {
			
			Img.setSkin(this.skinData.get(this.skinList.getSelectedIndex()).toString());
			
			
			//写入控制配置
			//创建数据流
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(Path));
			//写入数据流
			oos.writeObject(keySet);
			//关闭数据流
			oos.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			this.error.setText(e.getMessage());
			return false;
		}
		this.error.setText("");
		return true;
	}
	
	/*
	 * 初始化按键设置
	 * */
	private void initKeyText() {
		
		int x = 160; int y = 45; int w = 60; int h = 20;
		
		for(int i = 0 ; i < 4 ; i++) {
			keytext[i] = new TextCtrl(x,y,w,h,Method_Name[i]);
			y+=35;
		}
		
		keytext[4] = new TextCtrl(600,10,60,20,Method_Name[4]);

		int x1 = 600; int y1 = 50; int w1 = 60; int h1 = 20;
		for(int i = 5 ; i < 8 ; i++) {
			keytext[i] = new TextCtrl(x1,y1,w1,h1,Method_Name[i]);
			y1+=32;
		}
		
		try {
			//创建读取流
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Path));
			//创建HashMap数组，保证Key不重复，并导入数据(读取流)
			HashMap<Integer,String> cfgSet = (HashMap<Integer,String>)ois.readObject();
			//关闭数据流
			ois.close();
			//通过String来获取Key
			Set<Entry<Integer,String>> entryset = cfgSet.entrySet();
			//设置按键
			for(Entry<Integer,String> e : entryset) {
				for(TextCtrl TC : keytext) {
					if(TC.getMethodName().equals(e.getValue())) {
						TC.setKeyCode(e.getKey());
					}
				}
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	/*
	 * 创建主面板(选项卡面板)
	 * */
	private JTabbedPane creatMainPanel() {
		
		JTabbedPane JTP = new JTabbedPane();
		
		JTP.addTab("控制", this.createContolPanel());
		JTP.addTab("皮肤", this.createSkinPanel());
		
		return JTP;
	}
	
	
	/*
	 * 玩家皮肤面板
	 * */
	private JPanel createSkinPanel() {
		//创建预览图panel
		JPanel panel = new JPanel(new BorderLayout()); 
		
		File dir = new File(Img.GRAPHES_PATH);
		File[] files = dir.listFiles();
		this.skinViewList = new Image[files.length];
		
		for(int i = 0 ; i < files.length ; i++) {
			//添加选项
			this.skinData.addElement(files[i].getName());
			//添加预览图
			this.skinViewList[i] = new ImageIcon(files[i].getPath()+"\\View.png").getImage();
		}
		//在列表中添加列表数据
		this.skinList = new JList(this.skinData);
		//设置默认选中第一个
		this.skinList.setSelectedIndex(0);
		this.skinList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				repaint();
				
			}
			
		});
		this.skinView = new JPanel() {
			
			public void paintComponent(Graphics g) {
				Image showImg = skinViewList[skinList.getSelectedIndex()];
				int x = (this.getWidth()-showImg.getWidth(null))>>1;
				int y = (this.getHeight()-showImg.getHeight(null))>>1;
				g.drawImage(showImg, x, y, null);
			}
			
		};
		
		//JScrollPane 安装滚动条，滚动条里安装列表面板(skinList);
		panel.add(new JScrollPane(this.skinList),BorderLayout.WEST);
		
		panel.add(this.skinView,BorderLayout.CENTER);
		
		return panel;
	}

	/*
	 *	玩家控制设置面板 
	 * */
	private JPanel createContolPanel() {
		JPanel CCP = new JPanel() {

			public void paintComponent(Graphics g) {
				g.drawImage(Img_handle,0,0,null);
			}
			
		};
		
		//不适用布局管理器
		CCP.setLayout(null);

		for(int i = 0 ; i < keytext.length ; i++) {
			CCP.add(keytext[i]);
		}
		
		return CCP;
	}

	
}
