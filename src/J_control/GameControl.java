package J_control;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import J_config.DataInterfaceConfig;
import J_config.GameConfig;
import J_dao.Data;
import J_dao.DataBase;
import J_dao.DataDisk;
import J_dto.GameDto;
import J_dto.Player;
import J_service.GameTetris;
import J_ui.FrameGame;
import J_ui.PanelGame;
import J_ui.windows.FrameConfig;
import J_ui.windows.FramePointSave;
import J_ui.windows.TextCtrl;

/*
 * ������Ҽ����¼�
 * ������Ϸ����
 * ������Ϸ�߼�
 */
public class GameControl {
	
	
	//���ݷ��ʽӿ�A
	private Data dataA;
	
	//���ݷ��ʽӿ�B
	private Data dataB;
	
	//��Ϸ�����
	@SuppressWarnings("unused")
	private PanelGame panelGame;
	
	//��Ϸ�������ô���
	private FrameConfig frameConfig;
	
	//��Ϸ�߼���
	@SuppressWarnings("unused")
	private GameTetris gameService;
	//��Ϸ��Ϊ
	private Map<Integer,Method> actionlist;
	
	//��Ϸ����Դ
	private GameDto gamedto = null;
	
	//��Ϸ�߳�
	private Thread gameThread = null;
	
	//��Ϸ�����������
	private FramePointSave framePointSave;
	
	public GameControl(/**PanelGame panel_Game,GameTetris game_Service**/) {
		
		//������Ϸ����Դ
		gamedto=new GameDto();
		//������Ϸ�߼����ģ�������Ϸ����Դ��
		this.gameService=new GameTetris(gamedto);
		
		//�����ݽӿ�A������ݿ�
		this.dataA=CreatDataObject(GameConfig.getData_Config().getDataA());
		//�������ݿ��¼����Ϸ
		this.gamedto.setDbRecode((dataA.loadDto()));	
		
		//�����ݽӿ�B��ñ��ش��̼�¼
		this.dataB=CreatDataObject(GameConfig.getData_Config().getDataB());
		//���ñ��ش��̼�¼����Ϸ
		this.gamedto.setDiskRecode(dataB.loadDto());
		
		//������Ϸ���
		this.panelGame=new PanelGame(this,gamedto);
		//�رչ��ܰ�ť
		this.panelGame.funcbuttonSwitch(false);
		
		//��ȡ�û����ð���
		this.setControlConfig();
		//��ʼ���û����ô���
		this.frameConfig = new FrameConfig(this);
		//��ʼ����Ϸ�������洰��
		this.framePointSave = new FramePointSave(this);
		//������Ϸ���壨��װ��Ϸ��壩
		FrameGame framegame=new FrameGame(this.panelGame);
		
	}
	
	/*
	 * ��ȡ�û��������� 
	 * */
	public void setControlConfig() {
		//��ʼ����Ϸ��Ϊ
		this.actionlist = new HashMap<Integer,Method>();
		
		try {
			//��ȡ��
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/control.dat"));
			//����HashMap���飬��֤Key���ظ�������������
			@SuppressWarnings("unchecked")
			HashMap<Integer,String> cfgSet = (HashMap<Integer,String>)ois.readObject();
			//�ر�������
			ois.close();
			//ͨ��String����ȡKey
			Set<Entry<Integer,String>> entryset = cfgSet.entrySet();
			
			for(Entry<Integer,String> e : entryset) {
				actionlist.put(e.getKey(), this.gameService.getClass().getMethod(e.getValue()));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
	
	public Data CreatDataObject(DataInterfaceConfig cfg) {
		try {
			//��������
			Class<?> cls = Class.forName(cfg.getClassName());
			//��ù�����
			Constructor<?> ctr = cls.getConstructor(HashMap.class);
			//��������
			return (Data)ctr.newInstance(cfg.getParam());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	public void test() {
		// TODO Auto-generated method stub
		this.panelGame.repaint();
	}
	
	
	//�����������(��)
	public void keyup() {
		this.gameService.keyUp();
		this.panelGame.repaint();
	}
	
	
	//�����������(��)
	public void keydown() {
		this.gameService.keyDown();
		this.panelGame.repaint();
	}
	
	
	//�����������(��)
	public void keyleft() {
		this.gameService.keyLeft();
		this.panelGame.repaint();
	}
	
	
	//�����������(��)
	public void keyright() {
		this.gameService.keyRight();
		this.panelGame.repaint();
	}
	 **/
	//������ҿ�����������Ϊ
	public void actionByKeyCode(int keyCode) {
		
		try {
			
			if(this.actionlist.containsKey(keyCode)) {
				/**
				//��÷�����
				String methodName = this.actionlist.get(keyCode);
				//��÷�������
				Method actionMethod = this.gameService.getClass().getMethod(methodName);
				//���÷���
				actionMethod.invoke(this.gameService);
				 **/
				
				/**Method���invoke(Object obj,Object args[])�������յĲ�������Ϊ����
		                                      �������Ϊ�����������ݣ�����ת��Ϊ��Ӧ�İ�װ���͵Ķ���invoke()�����ķ���ֵ���Ƕ���
		                                      ���ʵ�ʱ����õķ����ķ��������ǻ����������ݣ���ôinvoke()���������ת��Ϊ��Ӧ�İ�װ���͵Ķ���
		       	          �ٽ��䷵��**/
				
				this.actionlist.get(keyCode).invoke(this.gameService);
			}
			
			
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		this.panelGame.repaint();
	}

	/*
	 * ��ʾ��ҿ��ƴ���
	 * */
	public void showUserConfig() {
		this.frameConfig.setVisible(true);
	}

	/*
	 * �Ӵ��ڹر��¼�
	 * */
	public void setOver() {
		this.panelGame.repaint();
		this.setControlConfig();
	}
	
	/*
	 * ��ʼ��ť�¼�
	 * */
	public void start() {
		//��ť����Ϊ�����Ե��
		this.panelGame.buttonSwitch(false);
		//���ù��ܰ�ť���Ե��
		this.panelGame.funcbuttonSwitch(true);
		//�ر���Ϸ���ô���
		this.frameConfig.setVisible(false);
		//�رշ����洢����
		this.framePointSave.setVisible(false);
		//��ʼ������
		this.gameService.startGame();		
		
		//�����߳�
		this.gameThread = new MThread();
		//�����߳�
		this.gameThread.start();
		//���ؽ���
		this.panelGame.repaint();
	}
	
	/*
	 * ���й����¼�
	 * */
	public void Elimination() {
		this.gameService.removeLine(17, gamedto.getGameMap());
		this.panelGame.repaint();
	}
	
	/*
	 * �ж����й��ܰ�ť�Ƿ���Խ���
	 * */
	public void SetElimination() {
		if(this.gamedto.isEliminationS()) {
			this.panelGame.EliminationSwitch(true);
		}
	}
	
	/*
	 * �ж�ʱͣ���ܰ�ť�Ƿ���Խ���
	 * */
	public void SetTimestop() {
		if(this.gamedto.isTimestopS()) {
			this.panelGame.TimestopSwitch(true);
		}
	}
	
	/*
	 * ʱͣ�����¼�
	 * */
	public void TimeStop() {
		gamedto.setTimeY();
	}
	
	/*
	 * �������
	 * */
	public void savePoint(String txname) {
		//�������Ϣ���ɼ�¼
		Player pls = new Player(txname,this.gamedto.getNowPoint());
		//�����¼�����ݿ�	
		this.dataA.saveData(pls);
		//�����¼�������ļ�
		this.dataB.saveData(pls);
		
		//�������ݿ��¼����Ϸ
		this.gamedto.setDbRecode((dataA.loadDto()));	
		//���ñ��ش��̼�¼����Ϸ
		this.gamedto.setDiskRecode(dataB.loadDto());
		
		//ˢ����Ϸ����
		panelGame.repaint();
	}
	
	/*
	 * ˢ�»���
	 * */
	public void repaint() {
		this.panelGame.repaint();
	}
	
	/*
	 * ʧ��֮��Ĵ���
	 * */
	public void afterLose() {
		if(!this.gamedto.isCheat()) {
			//��ʾ����÷ִ���
			this.framePointSave.show(this.gamedto.getNowPoint()); 
		}
		
		//ʹ��ť���Ե��
		this.panelGame.buttonSwitch(true);
		//�رչ��ܰ�ť
		this.panelGame.funcbuttonSwitch(false);
	}
	
	public class MThread extends Thread{
		public void run() {
			//���ؽ���
			panelGame.repaint();
			while(gamedto.isStart()) {
				
//				if(!gamedto.isStart()) {
//					afterLose();
//					break;
//				}
				try {
					//ͣ��ʱ��
					Thread.sleep(gamedto.getSleeptime());
					//�������ͣ״̬����һֱѭ��ͣ��
					if(gamedto.isPause()) {
						continue;
					}
					//������Ϸ����Ϊ
					gameService.mainAction();
					//���ؽ���
					panelGame.repaint();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//�жϹ��ܰ�ť״̬
				SetElimination();
				SetTimestop();
			}
			afterLose();
		}
	}
	
}
