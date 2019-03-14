package J_ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class LayerNext extends Layer {
	
	public LayerNext(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	public void paint(Graphics g) {
		this.DrawWindow(g);
		//如果游戏是开始状态则显示下一个方块
		if(this.gamedto.isStart()) {
			this.drawImageAtCenter(Img.next_act[this.gamedto.getNext()],g);
		}
	}
	
}
