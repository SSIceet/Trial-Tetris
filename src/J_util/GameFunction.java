package J_util;

public class GameFunction {
	
	/*
	 * �����߳�˯��ʱ��
	 * */
	public static long getSleepTime(int Lever) {
			
		long Sleep = (long) (1750/(Lever+2.5));
	
		return Sleep;
	}
	
}
