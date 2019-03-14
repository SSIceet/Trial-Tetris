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
	
	//�̵߳ȴ�ʱ��
	private long sleeptime;

	//��Ϸ�Ƿ��ǿ�ʼ״̬ 
	private boolean start;
	//�Ƿ���ʾ��Ӱ
	private boolean ShowShadow;
	//��ͣ
	private boolean pause;
	//�Ƿ�ʹ�����׼�
	private boolean isCheat;
	//�Ƿ���ʱͣ�����ڼ�
	private boolean TimeY;
	//���а�ť״̬
	private boolean EliminationS;
	//ʱͣ��ť״̬
	private boolean TimestopS;
	
	public GameDto() {
		Initdto();
	}
	/*dto��ʼ��*/
	public void Initdto() {
		//��ʼ��������Ϸ����
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
		//���û�����ݣ��򴴽�
		if(players==null){
			players=new ArrayList<Player>();
		}
		//����������С��5��������5��
		while(players.size()<5) {
			players.add(new Player("NO Data",0));
		}
		//����
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
		
		//�����̵߳ȴ�ʱ�䣨�����ٶȣ�
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
	
	//��������״̬
	public boolean isCheat() {
		return isCheat;
	}
	
	//��������״̬
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
