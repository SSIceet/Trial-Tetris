package J_ui;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import J_config.FrameConfig;
import J_config.GameConfig;
import J_config.LayerConfig;
import J_control.GameControl;
import J_control.PlayerControl;
import J_dto.GameDto;
import J_service.GameTetris;

@SuppressWarnings({ "serial", "unused" })
public class PanelGame extends JPanel{
	
	private static final int btn_size_W = GameConfig.getFrame_Config().getButtonconfig().getButtonW();
	
	private static final int btn_size_H = GameConfig.getFrame_Config().getButtonconfig().getButtonH();
	
	private static final int funcbtn_size_W = GameConfig.getFrame_Config().getFuncbuttonconfig().getButtonW();
	
	private static final int funcbtn_size_H = GameConfig.getFrame_Config().getFuncbuttonconfig().getButtonH();
	
	private List<Layer> layers=null;
	//开始按钮
	private JButton btnStart;
	//设置按钮
	private JButton btnConfig;
	//消行安妞
	private JButton btnElimination;
	//时停按钮
	private JButton btnTimestop;
	//究极按钮
	private JButton btnUltimate;
	
	private GameControl gamecontrol = null;
	
	
	public PanelGame(GameControl gamecontrol,GameDto game_dto) {
		//连接游戏控制器
		this.gamecontrol = gamecontrol; 
		//设置布局管理器为无布局
		this.setLayout(null);
		//初始化层
		this.initLayer(game_dto);
		//初始化组件
		this.initComponent(game_dto);
		/*安装玩家控制器*/
		this.addKeyListener(new PlayerControl(gamecontrol));
		
	}
	
	/*初始化层*/
	public void initLayer(GameDto game_dto) {
		
		try {
			/*获得游戏配置*/
			FrameConfig cfg=GameConfig.getFrame_Config();
			/*获得层配置*/
			List<LayerConfig> layersCfg=cfg.getLayersConfig();
			/*创建层数组*/
			layers=new ArrayList<Layer>(layersCfg.size());
			/*创建所有层*/
			for(LayerConfig layerCfg:layersCfg) {
				/*利用反射获得类对象构造函数*/
				/*获得类对象*/
				Class<?> cls=Class.forName(layerCfg.getClassName());
				/*获得构造函数*/
				Constructor<?> ctr=cls.getConstructor(int.class,int.class,int.class,int.class);
				/*调用构造函数创建对象*/
				/*把Object类型强制转换为Layer类型*/
				Layer Lbox=(Layer)ctr.newInstance(
					layerCfg.getX(),
					layerCfg.getY(),
					layerCfg.getW(),
					layerCfg.getH()
				);
				/*设置游戏数据对象*/
				Lbox.setGamedto(game_dto);
				/*把创建的Layer对象放入集合中*/
				layers.add(Lbox);
			}
		}catch(Exception e) {
			System.out.println("error");
			e.printStackTrace();
		}
		
	}
	 
//	/*安装玩家控制器*/
//	public void setGamecontrol(PlayerControl playercontrol) {
//		this.addKeyListener(playercontrol);
//	}
	
	/*初始化组件*/
	public void initComponent(GameDto game_dto) {
		
		//创建开始按钮
		this.btnStart = new JButton(Img.btnStart);
		//设置开始按钮的属性
		this.btnStart.setBounds(GameConfig.getFrame_Config().getButtonconfig().getStartX(), 
						   GameConfig.getFrame_Config().getButtonconfig().getStartY(), 
						   btn_size_W, 
						   btn_size_H);
		//设置监听事件（开始按钮）
		this.btnStart.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				gamecontrol.start();
				//返回焦点
				requestFocus();
				
			}
			
		});
		this.add(btnStart);
		
		//添加设置按钮
		this.btnConfig = new JButton(Img.btnConfig);
		//设置设置按钮的属性
		this.btnConfig.setBounds(GameConfig.getFrame_Config().getButtonconfig().getUserConfigX(),
							GameConfig.getFrame_Config().getButtonconfig().getUserConfigY(), 
							btn_size_W, 
							btn_size_H);
		//给设置按钮添加监听器
		this.btnConfig.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				gamecontrol.showUserConfig();
				
			}
			
		});
		this.add(btnConfig);
		
		//消除按钮
		this.btnElimination = new JButton(Img.btnElimination);
		
		this.btnElimination.setToolTipText("消除一行，一次性消除3行以上可解锁");
		
		this.btnElimination.setBounds(GameConfig.getFrame_Config().getFuncbuttonconfig().getEliminationX(), 
					GameConfig.getFrame_Config().getFuncbuttonconfig().getEliminationY(), 
					funcbtn_size_W, 
					funcbtn_size_H);
		
		//给消除按钮添加监听器
		this.btnElimination.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
									
				gamecontrol.Elimination();
				//锁定消除按钮
				btnElimination.setEnabled(false);
				//设置消除按钮的状态
				game_dto.setEliminationS(false);
				repaint();
				//返回焦点
				requestFocus();				
			}
								
		});
		
		this.add(btnElimination);
		
		btnElimination.setContentAreaFilled(false);
		
		//时停按钮
		this.btnTimestop = new JButton(Img.btnTimestop);
		
		this.btnTimestop.setToolTipText("掌控时间!一次性消除2行以上可解锁");
		
		this.btnTimestop.setBounds(GameConfig.getFrame_Config().getFuncbuttonconfig().getTimestopX(), 
					GameConfig.getFrame_Config().getFuncbuttonconfig().getTimestopY(), 
					funcbtn_size_W, 
					funcbtn_size_H);
		
		//给时停按钮添加监听器
		this.btnTimestop.addActionListener(new ActionListener() {
	
			public void actionPerformed(ActionEvent e) {
							
				gamecontrol.TimeStop();
				//锁定时停按钮
				btnTimestop.setEnabled(false);
				//设置消除按钮的状态
				game_dto.setTimestopS(false);
				//返回焦点
				requestFocus();
			}
						
		});
		
		this.add(btnTimestop);
		
		btnTimestop.setContentAreaFilled(false);
		
		//究极按钮
		this.btnUltimate = new JButton(Img.btnUltimate);
		
		this.btnUltimate.setToolTipText("最强大的道具按钮!!!!一场游戏只能使用一次");
		
		this.btnUltimate.setBounds(GameConfig.getFrame_Config().getFuncbuttonconfig().getUltimateX(), 
					GameConfig.getFrame_Config().getFuncbuttonconfig().getUltimateY(), 
					funcbtn_size_W, 
					funcbtn_size_H);
		
		//给究极按钮添加监听器
		this.btnUltimate.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
									
				for(int i = 0 ; i < 17 ; i++) {
					gamecontrol.Elimination();
				}
				//锁死究极按钮
				btnUltimate.setEnabled(false);
				repaint();
				//返回焦点
				requestFocus();
			}
								
		});
		
		this.add(btnUltimate);
		
		btnUltimate.setContentAreaFilled(false);
	}
	
	public void paintComponent(Graphics g) {
		//调用基类方法
		super.paintComponent(g);
		//绘制游戏界面
		for(int i=0;i<layers.size();layers.get(i++).paint(g));
	
	}
	
	/*
	 * 控制按钮是否可以点击
	 * */
	public void buttonSwitch(boolean off) {
		this.btnStart.setEnabled(off);
		this.btnConfig.setEnabled(off);
	}
	
	public void funcbuttonSwitch(boolean off) {
		this.btnElimination.setEnabled(off);
		this.btnTimestop.setEnabled(off);
		this.btnUltimate.setEnabled(off);
	}
	
	public void EliminationSwitch(boolean off) {
		this.btnElimination.setEnabled(off);
	}
	
	public void TimestopSwitch(boolean off) {
		this.btnTimestop.setEnabled(off);
	}
	
//	public void setGamecontrol(GameControl gamecontrol) {
//		this.gamecontrol = gamecontrol;
//	}
	
}
