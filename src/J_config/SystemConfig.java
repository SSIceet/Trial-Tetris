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
		
		//����rect�ڵ�
		List<Element> rects = system.elements("rect");
		//����typeConfig�б�������rect���Point����Ԫ��
		this.typeConfig = new ArrayList<Point[]>(rects.size());
		//����typeRound�б�������rect��roundԪ��
		this.typeRound = new ArrayList<Boolean>(rects.size());
		//����rect�ڵ�
		for(Element rect : rects) {
			//��ȡroundԪ������
			this.typeRound.add(Boolean.parseBoolean(rect.attributeValue("round")));
			//����Point�ڵ�
			List<Element> pointConfig = rect.elements("Point");
			//����points����������Point�ڵ����Ԫ��
			Point[] points = new Point[pointConfig.size()];
			//����points����
			for(int i = 0 ; i < points.length ; i++) {
				//��ȡpoint�ڵ����Ԫ��x,y
				int X = Integer.parseInt(pointConfig.get(i).attributeValue("x"));
				int Y = Integer.parseInt(pointConfig.get(i).attributeValue("y"));
				
				points[i] = new Point(X,Y);
			}
			//����ȡ��һ��pointԪ�ؼ���typeConfig�б���
			typeConfig.add(points);
		}
		//��������ӷ�����
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
