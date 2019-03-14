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
 * 接受玩家键盘事件
 * 控制游戏界面
 * 控制游戏逻辑
 */
public class GameControl {
	
	
	//数据访问接口A
	private Data dataA;
	
	//数据访问接口B
	private Data dataB;
	
	//游戏界面层
	@SuppressWarnings("unused")
	private PanelGame panelGame;
	
	//游戏控制设置窗口
	private FrameConfig frameConfig;
	
	//游戏逻辑层
	@SuppressWarnings("unused")
	private GameTetris gameService;
	//游戏行为
	private Map<Integer,Method> actionlist;
	
	//游戏数据源
	private GameDto gamedto = null;
	
	//游戏线程
	private Thread gameThread = null;
	
	//游戏保存分数窗口
	private FramePointSave framePointSave;
	
	public GameControl(/**PanelGame panel_Game,GameTetris game_Service**/) {
		
		//创建游戏数据源
		gamedto=new GameDto();
		//创建游戏逻辑核心（链接游戏数据源）
		this.gameService=new GameTetris(gamedto);
		
		//从数据接口A获得数据库
		this.dataA=CreatDataObject(GameConfig.getData_Config().getDataA());
		//设置数据库记录到游戏
		this.gamedto.setDbRecode((dataA.loadDto()));	
		
		//从数据接口B获得本地磁盘记录
		this.dataB=CreatDataObject(GameConfig.getData_Config().getDataB());
		//设置本地磁盘记录到游戏
		this.gamedto.setDiskRecode(dataB.loadDto());
		
		//创建游戏面板
		this.panelGame=new PanelGame(this,gamedto);
		//关闭功能按钮
		this.panelGame.funcbuttonSwitch(false);
		
		//获取用户设置按键
		this.setControlConfig();
		//初始化用户设置窗口
		this.frameConfig = new FrameConfig(this);
		//初始化游戏分数保存窗口
		this.framePointSave = new FramePointSave(this);
		//创建游戏窗体（安装游戏面板）
		FrameGame framegame=new FrameGame(this.panelGame);
		
	}
	
	/*
	 * 读取用户控制设置 
	 * */
	public void setControlConfig() {
		//初始化游戏行为
		this.actionlist = new HashMap<Integer,Method>();
		
		try {
			//读取流
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/control.dat"));
			//创建HashMap数组，保证Key不重复，并导入数据
			@SuppressWarnings("unchecked")
			HashMap<Integer,String> cfgSet = (HashMap<Integer,String>)ois.readObject();
			//关闭数据流
			ois.close();
			//通过String来获取Key
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
			//获得类对象
			Class<?> cls = Class.forName(cfg.getClassName());
			//获得构造器
			Constructor<?> ctr = cls.getConstructor(HashMap.class);
			//创建对象
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
	
	
	//控制器方向键(上)
	public void keyup() {
		this.gameService.keyUp();
		this.panelGame.repaint();
	}
	
	
	//控制器方向键(下)
	public void keydown() {
		this.gameService.keyDown();
		this.panelGame.repaint();
	}
	
	
	//控制器方向键(左)
	public void keyleft() {
		this.gameService.keyLeft();
		this.panelGame.repaint();
	}
	
	
	//控制器方向键(右)
	public void keyright() {
		this.gameService.keyRight();
		this.panelGame.repaint();
	}
	 **/
	//根据玩家控制来决定行为
	public void actionByKeyCode(int keyCode) {
		
		try {
			
			if(this.actionlist.containsKey(keyCode)) {
				/**
				//获得方法名
				String methodName = this.actionlist.get(keyCode);
				//获得方法对象
				Method actionMethod = this.gameService.getClass().getMethod(methodName);
				//调用方法
				actionMethod.invoke(this.gameService);
				 **/
				
				/**Method类的invoke(Object obj,Object args[])方法接收的参数必须为对象，
		                                      如果参数为基本类型数据，必须转换为相应的包装类型的对象。invoke()方法的返回值总是对象，
		                                      如果实际被调用的方法的返回类型是基本类型数据，那么invoke()方法会把它转换为相应的包装类型的对象，
		       	          再将其返回**/
				
				this.actionlist.get(keyCode).invoke(this.gameService);
			}
			
			
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		this.panelGame.repaint();
	}

	/*
	 * 显示玩家控制窗口
	 * */
	public void showUserConfig() {
		this.frameConfig.setVisible(true);
	}

	/*
	 * 子窗口关闭事件
	 * */
	public void setOver() {
		this.panelGame.repaint();
		this.setControlConfig();
	}
	
	/*
	 * 开始按钮事件
	 * */
	public void start() {
		//按钮设置为不可以点击
		this.panelGame.buttonSwitch(false);
		//设置功能按钮可以点击
		this.panelGame.funcbuttonSwitch(true);
		//关闭游戏设置窗口
		this.frameConfig.setVisible(false);
		//关闭分数存储窗口
		this.framePointSave.setVisible(false);
		//初始化数据
		this.gameService.startGame();		
		
		//创建线程
		this.gameThread = new MThread();
		//启动线程
		this.gameThread.start();
		//返回焦点
		this.panelGame.repaint();
	}
	
	/*
	 * 消行功能事件
	 * */
	public void Elimination() {
		this.gameService.removeLine(17, gamedto.getGameMap());
		this.panelGame.repaint();
	}
	
	/*
	 * 判断消行功能按钮是否可以解锁
	 * */
	public void SetElimination() {
		if(this.gamedto.isEliminationS()) {
			this.panelGame.EliminationSwitch(true);
		}
	}
	
	/*
	 * 判断时停功能按钮是否可以解锁
	 * */
	public void SetTimestop() {
		if(this.gamedto.isTimestopS()) {
			this.panelGame.TimestopSwitch(true);
		}
	}
	
	/*
	 * 时停功能事件
	 * */
	public void TimeStop() {
		gamedto.setTimeY();
	}
	
	/*
	 * 保存分数
	 * */
	public void savePoint(String txname) {
		//将玩家信息生成记录
		Player pls = new Player(txname,this.gamedto.getNowPoint());
		//保存记录到数据库	
		this.dataA.saveData(pls);
		//保存记录到本地文件
		this.dataB.saveData(pls);
		
		//设置数据库记录到游戏
		this.gamedto.setDbRecode((dataA.loadDto()));	
		//设置本地磁盘记录到游戏
		this.gamedto.setDiskRecode(dataB.loadDto());
		
		//刷新游戏画面
		panelGame.repaint();
	}
	
	/*
	 * 刷新画面
	 * */
	public void repaint() {
		this.panelGame.repaint();
	}
	
	/*
	 * 失败之后的处理
	 * */
	public void afterLose() {
		if(!this.gamedto.isCheat()) {
			//显示保存得分窗口
			this.framePointSave.show(this.gamedto.getNowPoint()); 
		}
		
		//使按钮可以点击
		this.panelGame.buttonSwitch(true);
		//关闭功能按钮
		this.panelGame.funcbuttonSwitch(false);
	}
	
	public class MThread extends Thread{
		public void run() {
			//返回焦点
			panelGame.repaint();
			while(gamedto.isStart()) {
				
//				if(!gamedto.isStart()) {
//					afterLose();
//					break;
//				}
				try {
					//停顿时间
					Thread.sleep(gamedto.getSleeptime());
					//如果是暂停状态，则一直循环停顿
					if(gamedto.isPause()) {
						continue;
					}
					//调用游戏主行为
					gameService.mainAction();
					//返回焦点
					panelGame.repaint();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//判断功能按钮状态
				SetElimination();
				SetTimestop();
			}
			afterLose();
		}
	}
	
}
