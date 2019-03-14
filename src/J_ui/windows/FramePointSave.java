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
		
		this.setTitle("��������");
		this.setSize(312, 128);
		FrameUtil.setFrame(this);
		this.setLayout(new BorderLayout());
		this.setResizable(false);
		this.creatcom();
		this.creatAction();
		
	}
	
	/*
	 * �����¼�����
	 * */
	private void creatAction() {
		this.buttonOk.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String Txname = TxName.getText();
				if(Txname.length() > 16 || Txname == null || Txname.equals("")) {
					errMsg.setText("���ָ�ʽ����");
				}else {
					setVisible(false);
					gameControl.savePoint(Txname);
				}
				
			}
			
		});
	}

	
	/*
	 * ��ʾ����
	 * */
	public void show(int point) {
		this.JLPoint.setText("��ĵ÷�Ϊ:"+point);
		this.setVisible(true);
	}
	
	/*
	 * ��ʼ�����
	 * */
	private void creatcom() {
		
		//����������壨��ʽ���֣�
		JPanel North = new JPanel(new FlowLayout(FlowLayout.CENTER));
		//������ǩ
		this.JLPoint = new JLabel("��ĵ÷�Ϊ:");
		//����ť���뱱�����
		North.add(JLPoint);
		
		this.errMsg = new JLabel();
		this.errMsg.setForeground(Color.RED);
		North.add(this.errMsg);
		
		//�����������������
		this.add(North,BorderLayout.NORTH); 
		
		//�����в���壨��ʽ���֣�
		JPanel Center = new JPanel(new FlowLayout(FlowLayout.CENTER));
		//�����ı���
		this.TxName = new JTextField(10);
		//�����ı���Ԫ�ؾ�����ʾ
		TxName.setHorizontalAlignment(0);
		//��ӱ�ǩ���в����
		Center.add(new JLabel("���������:"));
		//����ı����в����
		Center.add(this.TxName);
		//����в���嵽�����
		this.add(Center,BorderLayout.CENTER);
		
		//������ť
		this.buttonOk = new JButton("ȷ��");
		//�����ϲ���壨��ʽ���֣�
		JPanel South = new JPanel(new FlowLayout(FlowLayout.CENTER));
		//����ť�����ϲ����
		South.add(buttonOk);
		//���ϲ������������
		this.add(South,BorderLayout.SOUTH);
		
		
	}
	
}
