package J_ui.windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import J_control.GameControl;
import J_ui.Img;
import J_util.FrameUtil;

public class FrameConfig extends JFrame{
	//ȷ����ť
	private JButton OK = new JButton("ȷ��");
	//ȡ����ť
	private JButton cancel = new JButton("ȡ��");
	//Ӧ�ð�ť
	private JButton application = new JButton("Ӧ��");
	//�ֱ�ͼƬ
	private final static Image Img_handle = new ImageIcon("Graphes/Default/Other_Images/handle_2.jpg").getImage();
	//�˸���������
	private TextCtrl keytext[] = new TextCtrl[8];
	//Ԥ��ͼ�б�
	private Image[] skinViewList = null;
	//�˸������Ĺ���λ��
	private final static String[] Method_Name = {
		
		"keyUp","keyLeft","keyDown","keyRight","keyFunLeft","keyFunUp","keyFunRight","keyFunDown"
		
	};
	//��Ϸ�����ļ�·��
	private final static String Path="data/control.dat";
	
	//�����
	private JLabel error = new JLabel();
	
	//Ƥ���б�
	private JList skinList = null;
	
	//Ƥ��Ԥ��ͼ���
	private JPanel skinView = null;
	
	//�б�Ԫ��
	private DefaultListModel skinData = new DefaultListModel();
	
	private GameControl gameControl;
	
	public FrameConfig(GameControl gameControl) {
		//�����Ϸ����������
		this.gameControl = gameControl;
		//���ñ���
		this.setTitle("��������");
		//���ò��ֹ�����Ϊ�߽粼��
		this.setLayout(new BorderLayout()); 
		//ʹ���ڵ�С����Ч��
		//this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		//���ر�����
		this.setUndecorated(true);
		//��ʼ���˸���������ʾλ��
		this.initKeyText();
		//��������
		this.add(this.creatMainPanel(),BorderLayout.CENTER);
		//��Ӱ�ť���
		this.add(this.creatButtonPanel(),BorderLayout.SOUTH);
		//�������ڴ�С
		this.setResizable(false);
		//���ڴ�С
		this.setSize(800,455);
		//����
		FrameUtil.setFrame(this);
		
	}

	/*
	 * ������ť���
	 * */
	private JPanel creatButtonPanel() {
		
		JPanel JBut = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		error.setForeground(Color.red);
		JBut.add(error);
		
		this.OK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(writeConfig()) {
					setVisible(false);
					gameControl.setOver();
				}
				
			}
		});
		
		JBut.add(OK);
		
		this.cancel.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				gameControl.setOver();
			}
			
		});
		
		JBut.add(cancel);
		
		this.application.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				writeConfig();
				gameControl.repaint();
			}
		});
		
		JBut.add(application);
		
		return JBut;
	}

	/*
	 * д����Ϸ����(Ӧ�ð�ť)
	 * */
	protected boolean writeConfig() {
		//������ϣ��Ӧ����
		HashMap<Integer,String> keySet = new HashMap<Integer,String>();
		//���ð˸�������Ӧ�Ĺ���λ��
		for(int i = 0 ; i < keytext.length ; i++) {
			
			int keycode = this.keytext[i].getKeyCode();
			if(keycode == 0) {
				this.error.setText("��Ч����");
				return false;
			}
			keySet.put(keycode,this.keytext[i].getMethodName());
		}
		
		if(keySet.size() != 8) {
			this.error.setText("�ظ�����");
			return false;
		}
		
		try {
			
			Img.setSkin(this.skinData.get(this.skinList.getSelectedIndex()).toString());
			
			
			//д���������
			//����������
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(Path));
			//д��������
			oos.writeObject(keySet);
			//�ر�������
			oos.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			this.error.setText(e.getMessage());
			return false;
		}
		this.error.setText("");
		return true;
	}
	
	/*
	 * ��ʼ����������
	 * */
	private void initKeyText() {
		
		int x = 160; int y = 45; int w = 60; int h = 20;
		
		for(int i = 0 ; i < 4 ; i++) {
			keytext[i] = new TextCtrl(x,y,w,h,Method_Name[i]);
			y+=35;
		}
		
		keytext[4] = new TextCtrl(600,10,60,20,Method_Name[4]);

		int x1 = 600; int y1 = 50; int w1 = 60; int h1 = 20;
		for(int i = 5 ; i < 8 ; i++) {
			keytext[i] = new TextCtrl(x1,y1,w1,h1,Method_Name[i]);
			y1+=32;
		}
		
		try {
			//������ȡ��
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Path));
			//����HashMap���飬��֤Key���ظ�������������(��ȡ��)
			HashMap<Integer,String> cfgSet = (HashMap<Integer,String>)ois.readObject();
			//�ر�������
			ois.close();
			//ͨ��String����ȡKey
			Set<Entry<Integer,String>> entryset = cfgSet.entrySet();
			//���ð���
			for(Entry<Integer,String> e : entryset) {
				for(TextCtrl TC : keytext) {
					if(TC.getMethodName().equals(e.getValue())) {
						TC.setKeyCode(e.getKey());
					}
				}
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	/*
	 * ���������(ѡ����)
	 * */
	private JTabbedPane creatMainPanel() {
		
		JTabbedPane JTP = new JTabbedPane();
		
		JTP.addTab("����", this.createContolPanel());
		JTP.addTab("Ƥ��", this.createSkinPanel());
		
		return JTP;
	}
	
	
	/*
	 * ���Ƥ�����
	 * */
	private JPanel createSkinPanel() {
		//����Ԥ��ͼpanel
		JPanel panel = new JPanel(new BorderLayout()); 
		
		File dir = new File(Img.GRAPHES_PATH);
		File[] files = dir.listFiles();
		this.skinViewList = new Image[files.length];
		
		for(int i = 0 ; i < files.length ; i++) {
			//���ѡ��
			this.skinData.addElement(files[i].getName());
			//���Ԥ��ͼ
			this.skinViewList[i] = new ImageIcon(files[i].getPath()+"\\View.png").getImage();
		}
		//���б�������б�����
		this.skinList = new JList(this.skinData);
		//����Ĭ��ѡ�е�һ��
		this.skinList.setSelectedIndex(0);
		this.skinList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				repaint();
				
			}
			
		});
		this.skinView = new JPanel() {
			
			public void paintComponent(Graphics g) {
				Image showImg = skinViewList[skinList.getSelectedIndex()];
				int x = (this.getWidth()-showImg.getWidth(null))>>1;
				int y = (this.getHeight()-showImg.getHeight(null))>>1;
				g.drawImage(showImg, x, y, null);
			}
			
		};
		
		//JScrollPane ��װ���������������ﰲװ�б����(skinList);
		panel.add(new JScrollPane(this.skinList),BorderLayout.WEST);
		
		panel.add(this.skinView,BorderLayout.CENTER);
		
		return panel;
	}

	/*
	 *	��ҿ���������� 
	 * */
	private JPanel createContolPanel() {
		JPanel CCP = new JPanel() {

			public void paintComponent(Graphics g) {
				g.drawImage(Img_handle,0,0,null);
			}
			
		};
		
		//�����ò��ֹ�����
		CCP.setLayout(null);

		for(int i = 0 ; i < keytext.length ; i++) {
			CCP.add(keytext[i]);
		}
		
		return CCP;
	}

	
}
