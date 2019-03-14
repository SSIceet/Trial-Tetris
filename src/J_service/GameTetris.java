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
	
	//�����������
	private Random random=new Random();
	
	//�����������
	private static final int Max_Type=GameConfig.getSystem_Config().getTypeConfig().size();
	
	//��������
	private static final int Level_up=GameConfig.getSystem_Config().getLevel_up();
	
	//����������
	private static final Map<Integer,Integer> Plus_Point = GameConfig.getSystem_Config().getPlusPoint();
	
	//private static int exp;
	
	public GameTetris(GameDto game_dto) {
		this.gamedto=game_dto;
	}
	
	/*��ת*/
	public boolean keyUp() {
		
		if(!this.gamedto.isStart()) {
			return true;
		}
		
		if(this.gamedto.isPause()) {
			return true;
		}
		//�߳�������֤�̶߳�ռ
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
			//���������ƶ����ж��ܷ��ƶ�
			if(this.gamedto.getGameAct().Move(0, 1,this.gamedto.getGameMap())) {
				return false;
			}
			//�����Ϸ��ͼ����
			boolean[][] map=this.gamedto.getGameMap();
			//��÷�������
			Point[] act=this.gamedto.getGameAct().getPointAct();
			//������ѻ�����ͼ����
			for(int i=0;i<act.length;i++) {
				map[act[i].x][act[i].y]=true;
			}
			//�ж����У������㾭��ֵ
			int exp = this.plusExp();
			//������������¼�
			if(exp>0) {
				//����ֵ����
				this.plusPoint(exp);
				//���������ڵ���2ʱ������ʱͣ��ť
				if(exp>=2) {
					this.gamedto.setTimestopS(true);
				}
				//���������ڵ���3ʱ���������а�ť
				if(exp>=3) {
					this.gamedto.setEliminationS(true);
				}
				
			}
			//TODO ˢ��һ���µķ���
			//������һ������
			this.gamedto.getGameAct().InitAct(this.gamedto.getNext());
			//�����������һ������
			this.gamedto.setNext(random.nextInt(Max_Type));
			if(this.checkLose()) {
				//System.out.println("��Ϸ����");
				this.gamedto.setStart(false);
			}
			this.gamedto.setTimeF();
			
		}
		
		return true;
		
	}

//	private void afterLose() {
//		//������Ϸ��ʼ״̬Ϊ�ر�
//		this.gamedto.setStart(false);
//		//TODO �ر���Ϸ���߳�
//	}

	/*
	 * �ж���Ϸ�Ƿ�ʧ��
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
	 * ���Ӿ���ֵ
	 * */
	private void plusPoint(int exp) {
		
		int level = this.gamedto.getNowLevel();
		int rmLine = this.gamedto.getNowRemoveLine();
		int point = this.gamedto.getNowPoint();
		
		//��ֹ�ʽ
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
	 * ���в���
	 * */
	private int plusExp() {
		//�����Ϸ��ͼ
		boolean map[][] = this.gamedto.getGameMap();
		int exp = 0;
		//������Ϸ��ͼ���鿴�Ƿ��������
		for(int y = 0 ; y < GameDto.GameZoneH ; y++) {
			//�ж��Ƿ��������
			if(isCanRemoveLine(y,map)) {
				//�������������
				this.removeLine(y,map);
				//�õ�����ֵ
				exp++;
			}
		}
		return exp;
	}
	
	/*
	 * ���д���
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
	 * �ж��Ƿ��������
	 * */
	private boolean isCanRemoveLine(int y,boolean map[][]) {
		//���������ڵ�ÿһ����Ԫ��
		for(int x = 0 ; x < GameDto.GameZoneW ; x++) {
			if(!map[x][y]) {
				//�����һ������Ϊfalseֱ��������һ��
				return false;
			}
		}
		
		return true;
	}	

	//���׵÷ֽ�
	public boolean keyFunUp() {
		
		//��������(��ȥ���һ��)
		//this.removeLine(17,this.gamedto.getGameMap());
		
		//======================================����ר�÷���(���װ�ť)======================================
		
		this.gamedto.setCheat(true);
		
		//��ϷΪ��ʼ״̬ʱ,���׹��ܿ���ʹ��
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

	//˲������
	public boolean keyFunDown() {
		if(this.gamedto.isPause()) {
			return true;
		}
		while(!this.keyDown());
		this.gamedto.setTimeF();
		return true;
	}

	//��Ӱ����
	public boolean keyFunLeft() {
		this.gamedto.changShowShadow();
		return true;
	}

	//��ͣ
	public boolean keyFunRight() {
		if(this.gamedto.isStart()) {
			this.gamedto.changePause();
		}
		return true;
	}
	
	//��ʼ��Ϸ
	public void startGame() {
		//���������һ������
		this.gamedto.setNext(random.nextInt(Max_Type));
		//������ɵ�һ������
		GameAct act=new GameAct(random.nextInt(Max_Type));//Max_Type�ǲ��������ڵģ�ֻ����0~Max_Type-1֮�������
		this.gamedto.setGameAct(act);
		//������ϷΪ��ʼ״̬
		this.gamedto.setStart(true);
		//��������DTO�����ݣ�
		this.gamedto.Initdto();
	} 
	
	public void mainAction() {
		while(gamedto.isTimeY()) {
			return;
		}
		this.keyDown();
	}
	
}
