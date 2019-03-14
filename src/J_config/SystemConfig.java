package J_config;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;

public class SystemConfig {
	
	private final int MIN_X;
	
	private final int MAX_X;
	
	private final int MIN_Y;
	
	private final int MAX_Y;
	
	private final int Level_up;
	
	private final List<Point[]> typeConfig;
	
	private final List<Boolean> typeRound;
	
	private final Map<Integer,Integer> plusPoint; 
	
	@SuppressWarnings("unchecked")
	public SystemConfig(Element system) {
		
		this.MIN_X = Integer.parseInt(system.attributeValue("MIN_X"));
		this.MAX_X = Integer.parseInt(system.attributeValue("MAX_X"));
		this.MIN_Y = Integer.parseInt(system.attributeValue("MIN_Y"));
		this.MAX_Y = Integer.parseInt(system.attributeValue("MAX_Y"));
		this.Level_up = Integer.parseInt(system.attributeValue("Level_up"));
		
		//创建rect节点
		List<Element> rects = system.elements("rect");
		//创建typeConfig列表来容纳rect里的Point数组元素
		this.typeConfig = new ArrayList<Point[]>(rects.size());
		//创建typeRound列表来容纳rect的round元素
		this.typeRound = new ArrayList<Boolean>(rects.size());
		//遍历rect节点
		for(Element rect : rects) {
			//获取round元素内容
			this.typeRound.add(Boolean.parseBoolean(rect.attributeValue("round")));
			//创建Point节点
			List<Element> pointConfig = rect.elements("Point");
			//创建points数组来容纳Point节点里的元素
			Point[] points = new Point[pointConfig.size()];
			//遍历points数组
			for(int i = 0 ; i < points.length ; i++) {
				//获取point节点里的元素x,y
				int X = Integer.parseInt(pointConfig.get(i).attributeValue("x"));
				int Y = Integer.parseInt(pointConfig.get(i).attributeValue("y"));
				
				points[i] = new Point(X,Y);
			}
			//将获取的一组point元素加入typeConfig列表里
			typeConfig.add(points);
		}
		//获得连消加分配置
		this.plusPoint = new HashMap<Integer,Integer>(); 
		List<Element> plusPointCfg = system.elements("plusPoint");
		for(Element cfg : plusPointCfg) {
			int rm = Integer.parseInt(cfg.attributeValue("rm"));
			int point = Integer.parseInt(cfg.attributeValue("point"));
			this.plusPoint.put(rm, point);
		}
		
	}

	public Map<Integer, Integer> getPlusPoint() {
		return plusPoint;
	}

	public int getLevel_up() {
		return Level_up;
	}

	public int getMIN_X() {
		return MIN_X;
	}

	public int getMAX_X() {
		return MAX_X;
	}

	public int getMIN_Y() {
		return MIN_Y;
	}

	public int getMAX_Y() {
		return MAX_Y;
	}

	public List<Point[]> getTypeConfig() {
		return typeConfig;
	}

	public List<Boolean> getTypeRound() {
		return typeRound;
	}
	
}
