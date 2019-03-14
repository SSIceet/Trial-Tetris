package J_dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import J_config.GameConfig;
import J_entity.GameAct;
import J_util.GameFunction;

public class GameDto {

	public static final int GameZoneW = GameConfig.getSystem_Config().getMAX_X() + 1;
	
	public static final int GameZoneH = GameConfig.getSystem_Config().getMAX_Y() + 1;
	
	private List<Player> dbRecode;
	
	private List<Player> diskRecode;
	
	private boolean[][] gameMap;
	
	private GameAct gameAct;
	
	private int next;
	
	private int nowLevel;
	
	private int nowPoint;
	
	private int nowRemoveLine;
	
	//线程等待时间
	private long sleeptime;

	//游戏是否是开始状态 
	private boolean start;
	//是否显示阴影
	private boolean ShowShadow;
	//暂停
	private boolean pause;
	//是否使用作弊键
	private boolean isCheat;
	//是否在时停道具期间
	private boolean TimeY;
	//消行按钮状态
	private boolean EliminationS;
	//时停按钮状态
	private boolean TimestopS;
	
	public GameDto() {
		Initdto();
	}
	/*dto初始化*/
	public void Initdto() {
		//初始化所有游戏对象
		this.gameMap = new boolean[GameZoneW][GameZoneH];
		this.nowLevel = 0;
		this.nowPoint = 0;
		this.nowRemoveLine = 0;
		this.pause = false;
		this.isCheat = false;
		this.sleeptime = GameFunction.getSleepTime(this.nowLevel);
		this.TimeY = false;
	}
	
	public List<Player> getDbRecode() {
		return dbRecode;
	}

	public void setDbRecode(List<Player> dbRecode) {
		this.dbRecode = Datasupplement(dbRecode);
	}

	public List<Player> getDiskRecode() {
		return diskRecode;
	}

	public void setDiskRecode(List<Player> diskRecode) {
		this.diskRecode = Datasupplement(diskRecode);
	}

	private List<Player> Datasupplement(List<Player> players) {
		//如果没有数据，则创建
		if(players==null){
			players=new ArrayList<Player>();
		}
		//如果玩家数据小于5条，则补满5条
		while(players.size()<5) {
			players.add(new Player("NO Data",0));
		}
		//排序
		Collections.sort(players);
		return players;
	}
	
	public boolean[][] getGameMap() {
		return gameMap;
	}

	public void setGameMap(boolean[][] gameMap) {
		this.gameMap = gameMap;
	}

	public GameAct getGameAct() {
		return gameAct;
	}

	public void setGameAct(GameAct gameAct) {
		this.gameAct = gameAct;
	}

	public int getNext() {
		return next;
	}

	public void setNext(int next) {
		this.next = next;
	}

	public int getNowLevel() {
		return nowLevel;
	}

	public void setNowLevel(int nowLevel) {
		this.nowLevel = nowLevel;
		
		//计算线程等待时间（下落速度）
		this.sleeptime = GameFunction.getSleepTime(this.nowLevel);
	}

	public int getNowPoint() {
		return nowPoint;
	}

	public void setNowPoint(int nowPoint) {
		this.nowPoint = nowPoint;
	}

	public int getNowRemoveLine() {
		return nowRemoveLine;
	}

	public void setNowRemoveLine(int nowRemoveLine) {
		this.nowRemoveLine = nowRemoveLine;
	}
	
	public void setStart(boolean start) {
		this.start = start;
	}
	
	public boolean isStart() {
		return start;
	}
	public boolean isShowShadow() {
		return ShowShadow;
	}
	public void changShowShadow() {
		this.ShowShadow = !this.ShowShadow;
	}
	public boolean isPause() {
		return pause;
	}
	public void changePause() {
		this.pause = !this.pause;
	}
	
	//返回作弊状态
	public boolean isCheat() {
		return isCheat;
	}
	
	//设置作弊状态
	public void setCheat(boolean isCheat) {
		this.isCheat = isCheat;
	}
	
	public long getSleeptime() {
		return sleeptime;
	}
	public boolean isTimeY() {
		return TimeY;
	}
	public void setTimeY() {
		TimeY = !this.TimeY;
	}
	public void setTimeF() {
		TimeY = false;
	}
	public boolean isEliminationS() {
		return EliminationS;
	}
	public void setEliminationS(boolean off) {
		EliminationS = off;
	}
	public boolean isTimestopS() {
		return TimestopS;
	}
	public void setTimestopS(boolean off) {
		TimestopS = off;
	}
	
	
}
