package J_util;

public class GameFunction {
	
	/*
	 * 计算线程睡眠时间
	 * */
	public static long getSleepTime(int Lever) {
			
		long Sleep = (long) (1750/(Lever+2.5));
	
		return Sleep;
	}
	
}
