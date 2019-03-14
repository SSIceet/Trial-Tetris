package J_config;


public class LayerConfig {
	
	private String ClassName;
	private int x,y,w,h;
	
	public LayerConfig(String ClassName,int x,int y,int w,int h) {
		super();
		this.ClassName=ClassName;
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
	}
	
	public String getClassName() {
		return ClassName;
	}

	public int getX() {
		return x;
	}

	
	public int getY() {
		return y;
	}


	public int getW() {
		return w;
	}


	public int getH() {
		return h;
	}

}
