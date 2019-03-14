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
	//��ʼ��ť
	private JButton btnStart;
	//���ð�ť
	private JButton btnConfig;
	//���а��
	private JButton btnElimination;
	//ʱͣ��ť
	private JButton btnTimestop;
	//������ť
	private JButton btnUltimate;
	
	private GameControl gamecontrol = null;
	
	
	public PanelGame(GameControl gamecontrol,GameDto game_dto) {
		//������Ϸ������
		this.gamecontrol = gamecontrol; 
		//���ò��ֹ�����Ϊ�޲���
		this.setLayout(null);
		//��ʼ����
		this.initLayer(game_dto);
		//��ʼ�����
		this.initComponent(game_dto);
		/*��װ��ҿ�����*/
		this.addKeyListener(new PlayerControl(gamecontrol));
		
	}
	
	/*��ʼ����*/
	public void initLayer(GameDto game_dto) {
		
		try {
			/*�����Ϸ����*/
			FrameConfig cfg=GameConfig.getFrame_Config();
			/*��ò�����*/
			List<LayerConfig> layersCfg=cfg.getLayersConfig();
			/*����������*/
			layers=new ArrayList<Layer>(layersCfg.size());
			/*�������в�*/
			for(LayerConfig layerCfg:layersCfg) {
				/*���÷�����������캯��*/
				/*��������*/
				Class<?> cls=Class.forName(layerCfg.getClassName());
				/*��ù��캯��*/
				Constructor<?> ctr=cls.getConstructor(int.class,int.class,int.class,int.class);
				/*���ù��캯����������*/
				/*��Object����ǿ��ת��ΪLayer����*/
				Layer Lbox=(Layer)ctr.newInstance(
					layerCfg.getX(),
					layerCfg.getY(),
					layerCfg.getW(),
					layerCfg.getH()
				);
				/*������Ϸ���ݶ���*/
				Lbox.setGamedto(game_dto);
				/*�Ѵ�����Layer������뼯����*/
				layers.add(Lbox);
			}
		}catch(Exception e) {
			System.out.println("error");
			e.printStackTrace();
		}
		
	}
	 
//	/*��װ��ҿ�����*/
//	public void setGamecontrol(PlayerControl playercontrol) {
//		this.addKeyListener(playercontrol);
//	}
	
	/*��ʼ�����*/
	public void initComponent(GameDto game_dto) {
		
		//������ʼ��ť
		this.btnStart = new JButton(Img.btnStart);
		//���ÿ�ʼ��ť������
		this.btnStart.setBounds(GameConfig.getFrame_Config().getButtonconfig().getStartX(), 
						   GameConfig.getFrame_Config().getButtonconfig().getStartY(), 
						   btn_size_W, 
						   btn_size_H);
		//���ü����¼�����ʼ��ť��
		this.btnStart.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				gamecontrol.start();
				//���ؽ���
				requestFocus();
				
			}
			
		});
		this.add(btnStart);
		
		//������ð�ť
		this.btnConfig = new JButton(Img.btnConfig);
		//�������ð�ť������
		this.btnConfig.setBounds(GameConfig.getFrame_Config().getButtonconfig().getUserConfigX(),
							GameConfig.getFrame_Config().getButtonconfig().getUserConfigY(), 
							btn_size_W, 
							btn_size_H);
		//�����ð�ť��Ӽ�����
		this.btnConfig.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				gamecontrol.showUserConfig();
				
			}
			
		});
		this.add(btnConfig);
		
		//������ť
		this.btnElimination = new JButton(Img.btnElimination);
		
		this.btnElimination.setToolTipText("����һ�У�һ��������3�����Ͽɽ���");
		
		this.btnElimination.setBounds(GameConfig.getFrame_Config().getFuncbuttonconfig().getEliminationX(), 
					GameConfig.getFrame_Config().getFuncbuttonconfig().getEliminationY(), 
					funcbtn_size_W, 
					funcbtn_size_H);
		
		//��������ť��Ӽ�����
		this.btnElimination.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
									
				gamecontrol.Elimination();
				//����������ť
				btnElimination.setEnabled(false);
				//����������ť��״̬
				game_dto.setEliminationS(false);
				repaint();
				//���ؽ���
				requestFocus();				
			}
								
		});
		
		this.add(btnElimination);
		
		btnElimination.setContentAreaFilled(false);
		
		//ʱͣ��ť
		this.btnTimestop = new JButton(Img.btnTimestop);
		
		this.btnTimestop.setToolTipText("�ƿ�ʱ��!һ��������2�����Ͽɽ���");
		
		this.btnTimestop.setBounds(GameConfig.getFrame_Config().getFuncbuttonconfig().getTimestopX(), 
					GameConfig.getFrame_Config().getFuncbuttonconfig().getTimestopY(), 
					funcbtn_size_W, 
					funcbtn_size_H);
		
		//��ʱͣ��ť��Ӽ�����
		this.btnTimestop.addActionListener(new ActionListener() {
	
			public void actionPerformed(ActionEvent e) {
							
				gamecontrol.TimeStop();
				//����ʱͣ��ť
				btnTimestop.setEnabled(false);
				//����������ť��״̬
				game_dto.setTimestopS(false);
				//���ؽ���
				requestFocus();
			}
						
		});
		
		this.add(btnTimestop);
		
		btnTimestop.setContentAreaFilled(false);
		
		//������ť
		this.btnUltimate = new JButton(Img.btnUltimate);
		
		this.btnUltimate.setToolTipText("��ǿ��ĵ��߰�ť!!!!һ����Ϸֻ��ʹ��һ��");
		
		this.btnUltimate.setBounds(GameConfig.getFrame_Config().getFuncbuttonconfig().getUltimateX(), 
					GameConfig.getFrame_Config().getFuncbuttonconfig().getUltimateY(), 
					funcbtn_size_W, 
					funcbtn_size_H);
		
		//��������ť��Ӽ�����
		this.btnUltimate.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
									
				for(int i = 0 ; i < 17 ; i++) {
					gamecontrol.Elimination();
				}
				//����������ť
				btnUltimate.setEnabled(false);
				repaint();
				//���ؽ���
				requestFocus();
			}
								
		});
		
		this.add(btnUltimate);
		
		btnUltimate.setContentAreaFilled(false);
	}
	
	public void paintComponent(Graphics g) {
		//���û��෽��
		super.paintComponent(g);
		//������Ϸ����
		for(int i=0;i<layers.size();layers.get(i++).paint(g));
	
	}
	
	/*
	 * ���ư�ť�Ƿ���Ե��
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
