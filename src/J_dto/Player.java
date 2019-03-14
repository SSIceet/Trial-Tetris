package J_dto;

import java.io.Serializable;

public class Player implements Comparable<Player>,Serializable{
	
	private String name;
	
	private int point;
	
	public Player(String name_,int point_) {
		super();
		this.name=name_;
		this.point=point_;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	@Override
	public int compareTo(Player Ply) {
		return Ply.point-this.point;
	}
	
	
}
