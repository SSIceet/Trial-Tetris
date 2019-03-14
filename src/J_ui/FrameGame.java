package J_ui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import J_config.FrameConfig;
import J_config.GameConfig;
import J_util.FrameUtil;

@SuppressWarnings("serial")
public class FrameGame extends JFrame {
	public FrameGame(PanelGame Game) {

		FrameConfig Fcfg=GameConfig.getFrame_Config();
		
		this.setTitle("¶íÂÞË¹·½¿é");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(Fcfg.getWidth(),Fcfg.getHeight());
		this.setResizable(false);
		//¾ÓÖÐ
		FrameUtil.setFrame(this);
		
		this.setVisible(true);
		 
		this.setContentPane(Game);
		
	}
}
