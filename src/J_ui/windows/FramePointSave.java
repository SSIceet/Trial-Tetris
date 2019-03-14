package J_ui.windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import J_control.GameControl;
import J_util.FrameUtil;

public class FramePointSave extends JFrame{

	private JLabel JLPoint = null;

	private JTextField TxName = null; 
	
	private JButton buttonOk = null;
	
	private JLabel errMsg = null;
	
	private static GameControl gameControl = null;
	
	public FramePointSave(GameControl gameControl) {
		
		this.gameControl = gameControl;
		
		this.setTitle("分数保存");
		this.setSize(312, 128);
		FrameUtil.setFrame(this);
		this.setLayout(new BorderLayout());
		this.setResizable(false);
		this.creatcom();
		this.creatAction();
		
	}
	
	/*
	 * 创建事件监听
	 * */
	private void creatAction() {
		this.buttonOk.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String Txname = TxName.getText();
				if(Txname.length() > 16 || Txname == null || Txname.equals("")) {
					errMsg.setText("名字格式错误");
				}else {
					setVisible(false);
					gameControl.savePoint(Txname);
				}
				
			}
			
		});
	}

	
	/*
	 * 显示窗口
	 * */
	public void show(int point) {
		this.JLPoint.setText("你的得分为:"+point);
		this.setVisible(true);
	}
	
	/*
	 * 初始化组件
	 * */
	private void creatcom() {
		
		//创建北部面板（流式布局）
		JPanel North = new JPanel(new FlowLayout(FlowLayout.CENTER));
		//创建标签
		this.JLPoint = new JLabel("你的得分为:");
		//将按钮加入北部面板
		North.add(JLPoint);
		
		this.errMsg = new JLabel();
		this.errMsg.setForeground(Color.RED);
		North.add(this.errMsg);
		
		//将北部面板加入主面板
		this.add(North,BorderLayout.NORTH); 
		
		//创建中部面板（流式布局）
		JPanel Center = new JPanel(new FlowLayout(FlowLayout.CENTER));
		//创建文本框
		this.TxName = new JTextField(10);
		//设置文本框元素居中显示
		TxName.setHorizontalAlignment(0);
		//添加标签到中部面板
		Center.add(new JLabel("你的名字是:"));
		//添加文本框到中部面板
		Center.add(this.TxName);
		//添加中部面板到主面板
		this.add(Center,BorderLayout.CENTER);
		
		//创建按钮
		this.buttonOk = new JButton("确定");
		//创建南部面板（流式布局）
		JPanel South = new JPanel(new FlowLayout(FlowLayout.CENTER));
		//将按钮加入南部面板
		South.add(buttonOk);
		//将南部面板加入主面板
		this.add(South,BorderLayout.SOUTH);
		
		
	}
	
}
