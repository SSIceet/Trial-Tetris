package J_service;

import java.awt.Point;
import java.util.List;
import java.util.Map;
import java.util.Random;

import J_config.GameConfig;
import J_dto.GameDto;
import J_dto.Player;
import J_entity.GameAct;

public class GameTetris implements GameService{

	@SuppressWarnings("unused")
	private GameDto gamedto;
	
	//随机数生成器
	private Random random=new Random();
	
	//方块种类个数
	private static final int Max_Type=GameConfig.getSystem_Config().getTypeConfig().size();
	
	//升级行数
	private static final int Level_up=GameConfig.getSystem_Config().getLevel_up();
	
	//连消分数表
	private static final Map<Integer,Integer> Plus_Point = GameConfig.getSystem_Config().getPlusPoint();
	
	//private static int exp;
	
	public GameTetris(GameDto game_dto) {
		this.gamedto=game_dto;
	}
	
	/*旋转*/
	public boolean keyUp() {
		
		if(!this.gamedto.isStart()) {
			return true;
		}
		
		if(this.gamedto.isPause()) {
			return true;
		}
		//线程锁，保证线程独占
		synchronized(this.gamedto) {
			this.gamedto.getGameAct().rotate(this.gamedto.getGameMap());
		}
		return true;
	}
	
	public boolean keyDown() {
		
		if(!this.gamedto.isStart()) {
			return true;
		}
		
		if(this.gamedto.isPause()) {
			return true;
		}
		synchronized(this.gamedto) {
			//方块向下移动，判断能否移动
			if(this.gamedto.getGameAct().Move(0, 1,this.gamedto.getGameMap())) {
				return false;
			}
			//获得游戏地图对象
			boolean[][] map=this.gamedto.getGameMap();
			//获得方法对象
			Point[] act=this.gamedto.getGameAct().getPointAct();
			//将方块堆积到地图数组
			for(int i=0;i<act.length;i++) {
				map[act[i].x][act[i].y]=true;
			}
			//判断消行，并计算经验值
			int exp = this.plusExp();
			//如果发生消行事件
			if(exp>0) {
				//经验值增加
				this.plusPoint(exp);
				//消行数大于等于2时，解锁时停按钮
				if(exp>=2) {
					this.gamedto.setTimestopS(true);
				}
				//消行数大于等于3时，解锁消行按钮
				if(exp>=3) {
					this.gamedto.setEliminationS(true);
				}
				
			}
			//TODO 刷新一个新的方块
			//创建下一个方块
			this.gamedto.getGameAct().InitAct(this.gamedto.getNext());
			//随机生成再下一个方块
			this.gamedto.setNext(random.nextInt(Max_Type));
			if(this.checkLose()) {
				//System.out.println("游戏结束");
				this.gamedto.setStart(false);
			}
			this.gamedto.setTimeF();
			
		}
		
		return true;
		
	}

//	private void afterLose() {
//		//设置游戏开始状态为关闭
//		this.gamedto.setStart(false);
//		//TODO 关闭游戏主线程
//	}

	/*
	 * 判断游戏是否失败
	 * */
	private boolean checkLose() {
		Point[] actPoint = this.gamedto.getGameAct().getPointAct();
		boolean[][] map = this.gamedto.getGameMap();
		for(int i = 0 ; i < actPoint.length ; i++) {
			if(map[actPoint[i].x][actPoint[i].y]) {
				return true;
			}
		}
		return false;
	}

	/*
	 * 增加经验值
	 * */
	private void plusPoint(int exp) {
		
		int level = this.gamedto.getNowLevel();
		int rmLine = this.gamedto.getNowRemoveLine();
		int point = this.gamedto.getNowPoint();
		
		//算分公式
		/**
		if(exp == 1) {
			point += exp*10;
		}else {
			point += exp*10+(Math.pow(2,exp-2)*10);
		}
		**/
		
		
		if(rmLine % Level_up + exp >= Level_up) {
			this.gamedto.setNowLevel(++level);
		}
		
		this.gamedto.setNowPoint(point+Plus_Point.get(exp));
		this.gamedto.setNowRemoveLine(rmLine+exp);
		
		
	}

	public boolean keyLeft() {
		
		if(!this.gamedto.isStart()) {
			return true;
		}
		
		if(this.gamedto.isPause()) {
			return true;
		}
		synchronized(this.gamedto) {
			this.gamedto.getGameAct().Move(-1, 0,this.gamedto.getGameMap());
		}
		return true;
	}
	
	public boolean keyRight() {
		
		if(!this.gamedto.isStart()) {
			return true;
		}
		
		if(this.gamedto.isPause()) {
			return true;
		}
		synchronized(this.gamedto) {
			this.gamedto.getGameAct().Move(1, 0,this.gamedto.getGameMap());
		}
		return true;
	}
	
	/*
	 * 消行操作
	 * */
	private int plusExp() {
		//获得游戏地图
		boolean map[][] = this.gamedto.getGameMap();
		int exp = 0;
		//遍历游戏地图，查看是否可有消行
		for(int y = 0 ; y < GameDto.GameZoneH ; y++) {
			//判断是否可以消行
			if(isCanRemoveLine(y,map)) {
				//如果可消则消行
				this.removeLine(y,map);
				//得到经验值
				exp++;
			}
		}
		return exp;
	}
	
	/*
	 * 消行处理
	 * */
	public void removeLine(int rowNumber, boolean[][] map) {
		
		for(int x = 0 ; x < GameDto.GameZoneW ; x++) {
			for(int y = rowNumber ; y > 0 ; y--) {
				map[x][y] = map[x][y-1];
			}
			map[x][0] = false;
		} 
		
	}

	/*
	 * 判断是否可以消行
	 * */
	private boolean isCanRemoveLine(int y,boolean map[][]) {
		//遍历单行内的每一个单元格
		for(int x = 0 ; x < GameDto.GameZoneW ; x++) {
			if(!map[x][y]) {
				//如果有一个方格为false直接跳到下一行
				return false;
			}
		}
		
		return true;
	}	

	//作弊得分健
	public boolean keyFunUp() {
		
		//作弊消行(消去最后一行)
		//this.removeLine(17,this.gamedto.getGameMap());
		
		//======================================测试专用方法(作弊按钮)======================================
		
		this.gamedto.setCheat(true);
		
		//游戏为开始状态时,作弊功能可以使用
		if(this.gamedto.isStart()) {
			int point=this.gamedto.getNowPoint();
			int rmLine=this.gamedto.getNowRemoveLine();
			int lv=this.gamedto.getNowLevel();
			point+=10;
			rmLine+=1;
			if(rmLine % 20 == 0) {
				lv+=1;
			}
			this.gamedto.setNowPoint(point);
			this.gamedto.setNowRemoveLine(rmLine);
			this.gamedto.setNowLevel(lv);
		}
		
		return true;
		
	}

	//瞬间下落
	public boolean keyFunDown() {
		if(this.gamedto.isPause()) {
			return true;
		}
		while(!this.keyDown());
		this.gamedto.setTimeF();
		return true;
	}

	//阴影开关
	public boolean keyFunLeft() {
		this.gamedto.changShowShadow();
		return true;
	}

	//暂停
	public boolean keyFunRight() {
		if(this.gamedto.isStart()) {
			this.gamedto.changePause();
		}
		return true;
	}
	
	//开始游戏
	public void startGame() {
		//随机生成下一个方块
		this.gamedto.setNext(random.nextInt(Max_Type));
		//随机生成第一个方块
		GameAct act=new GameAct(random.nextInt(Max_Type));//Max_Type是不包含在内的，只产生0~Max_Type-1之间的数。
		this.gamedto.setGameAct(act);
		//设置游戏为开始状态
		this.gamedto.setStart(true);
		//重新设置DTO（数据）
		this.gamedto.Initdto();
	} 
	
	public void mainAction() {
		while(gamedto.isTimeY()) {
			return;
		}
		this.keyDown();
	}
	
}
