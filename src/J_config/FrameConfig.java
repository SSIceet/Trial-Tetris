package J_config;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Element;

public class FrameConfig {
	
	//���ڿ��
	private final int width;
	//���ڸ߶�
	private final int height;
	//���ڼ��
	private final int Margin;
	//�߿���
	private final int windowsize;
	//�θ�
	private final int PointAdd;
	//����ߴ���λ��ƫ����
	private final int ACT_Size;
	//ʧ���б�
	private final int loseIdx;
	//��������
	private final List<LayerConfig> layersConfig;
	//��ť����
	private final ButtonConfig buttonconfig;
	//���ܰ�ť����
	private final FuncButtonConfig funcbuttonconfig; 
	
	public FrameConfig(Element frame) {
		
		/*���frame������*/
		this.width=Integer.parseInt(frame.attributeValue("width"));
		this.height=Integer.parseInt(frame.attributeValue("height"));
		this.Margin=Integer.parseInt(frame.attributeValue("Margin"));
		this.windowsize=Integer.parseInt(frame.attributeValue("windowsize"));
		this.PointAdd=Integer.parseInt(frame.attributeValue("PointAdd"));
		this.loseIdx=Integer.parseInt(frame.attributeValue("loseIdx"));
		this.ACT_Size=Integer.parseInt(frame.attributeValue("ACT_Size"));
		
		/*����һ�������洢layer��Element�б�*/
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
	
