package J_config;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

public class FrameConfig {
	
	//窗口宽度
	private final int width;
	//窗口高度
	private final int height;
	//窗口间距
	private final int Margin;
	//边框厚度
	private final int windowsize;
	//拔高
	private final int PointAdd;
	//方块尺寸左位移偏移量
	private final int ACT_Size;
	//失败判标
	private final int loseIdx;
	//窗体属性
	private final List<LayerConfig> layersConfig;
	//按钮属性
	private final ButtonConfig buttonconfig;
	//功能按钮属性
	private final FuncButtonConfig funcbuttonconfig; 
	
	public FrameConfig(Element frame) {
		
		/*获得frame的属性*/
		this.width=Integer.parseInt(frame.attributeValue("width"));
		this.height=Integer.parseInt(frame.attributeValue("height"));
		this.Margin=Integer.parseInt(frame.attributeValue("Margin"));
		this.windowsize=Integer.parseInt(frame.attributeValue("windowsize"));
		this.PointAdd=Integer.parseInt(frame.attributeValue("PointAdd"));
		this.loseIdx=Integer.parseInt(frame.attributeValue("loseIdx"));
		this.ACT_Size=Integer.parseInt(frame.attributeValue("ACT_Size"));
		
		/*创建一个用来存储layer的Element列表*/
		@SuppressWarnings("unchecked")
		List<Element> layers=frame.elements("layer");
		layersConfig=new ArrayList<LayerConfig>();
		for(Element layer:layers) {
			LayerConfig box=new LayerConfig(
				layer.attributeValue("className"),
				Integer.parseInt(layer.attributeValue("x")),
				Integer.parseInt(layer.attributeValue("y")),
				Integer.parseInt(layer.attributeValue("w")),
				Integer.parseInt(layer.attributeValue("h"))
			);
			layersConfig.add(box);
		}
		
		buttonconfig = new ButtonConfig(frame.element("button"));
		
		funcbuttonconfig = new FuncButtonConfig(frame.element("functionButton"));
		
	}

	public int getACT_Size() {
		return ACT_Size;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getMargin() {
		return Margin;
	}

	public int getWindowsize() {
		return windowsize;
	}

	public int getPointAdd() {
		return PointAdd;
	}

	public List<LayerConfig> getLayersConfig() {
		return layersConfig;
	}

	public int getLoseIdx() {
		return loseIdx;
	}

	public ButtonConfig getButtonconfig() {
		return buttonconfig;
	}

	public FuncButtonConfig getFuncbuttonconfig() {
		return funcbuttonconfig;
	}
		
}
	
