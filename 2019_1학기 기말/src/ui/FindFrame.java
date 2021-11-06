package ui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class FindFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel mainPanel;
	private Font font;
	private JLabel name;
	private JTextField userName;
	private JTextField id;
	private JLabel userID;
	private JButton ok;
	private JLabel IDfind;
	private JLabel PWfind;
	private JButton ok2;
	public String rUserName;
	public String rUserID;
	public String rUserPW;
	public String rUserMajor;
	public String rUserGrade;
	private JButton login;
	private JButton cancel;
	private JButton register;
	private JLabel majorLabel;
	private JTextField major;
	private JButton refresh;

	public FindFrame(ActionListener actionHandler) {
		
		//setting
		this.setTitle("���̵�/��й�ȣ ã��");
		this.setFont(font);
		this.setSize(500, 450);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		//font 
		font = new Font("���", Font.PLAIN, 15);

		//imageIcon
		Toolkit toolkit = this.getToolkit();
		Image img = toolkit.createImage("image/mju_icon.jpg");
		this.setIconImage(img);

		//Main Panel
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.white);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		//findID Panel
		JPanel idP = new JPanel();
		idP.setLayout(new BoxLayout(idP, BoxLayout.Y_AXIS));
		mainPanel.add(idP);
		//id label
		JPanel idLP = new JPanel();
		idLPanel(idLP);
		idP.add(idLP);
		//name Panel
		JPanel nameP = new JPanel();
		namePanel(nameP);
		idP.add(nameP);
		//major Panel
		JPanel majorP = new JPanel();
		majorPanel(majorP,actionHandler);
		idP.add(majorP);
		//FindPW Panel
		JPanel pwp = new JPanel();
		pwp.setLayout(new BoxLayout(pwp, BoxLayout.Y_AXIS));
		mainPanel.add(pwp);
		//pw Label
		JPanel pwLP = new JPanel();
		pwLabel(pwLP);
		pwp.add(pwLP);
		//pw input Panel
		JPanel pwinP = new JPanel();
		pwinPanel(pwinP,actionHandler);
		pwp.add(pwinP);
		//button Panel
		JPanel buttonP = new JPanel();
		buttonPanel(buttonP,actionHandler);
		mainPanel.add(buttonP);

		this.add(mainPanel);
	}
	//���� �Է� �г�
	private void majorPanel(JPanel majorP, ActionListener actionHandler) {
		majorP.setLayout(new FlowLayout());
		majorP.setBackground(Color.white);
		//Label
		majorLabel = new JLabel();
		majorLabel.setText("�а�/�к�");
		majorLabel.setFont(font);
		majorP.add(majorLabel);

		major = new JTextField(15);
		major.setToolTipText("�а� �Ǵ� �кθ� �Է����ּ���.");
		major.registerKeyboardAction(actionHandler,"findID",KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0),JComponent.WHEN_FOCUSED);
		majorP.add(major);
		//Ȯ�� ��ư
		ok = new JButton();
		ok.setText("Ȯ��");
		ok.setFont(font);
		ok.setBackground(new Color(0X55CCCCFF));
		ok.addActionListener(actionHandler);
		ok.setActionCommand("findID");
		majorP.add(ok);
	}
	//��ư �г�
	private void buttonPanel(JPanel buttonP, ActionListener actionHandler) {
		buttonP.setLayout(new FlowLayout());
		buttonP.setBackground(Color.white);
		//ȸ������ ��ư
		register = new JButton();
		register.setText("ȸ������");
		register.setFont(font);
		register.setToolTipText("ȸ�������Ϸ� ����");
		register.setBackground(new Color(0X55CCCCFF));
		register.addActionListener(actionHandler);
		register.setActionCommand("register");
		buttonP.add(register);
		//�α��� ��ư
		login = new JButton();
		login.setText("�α���");
		login.setFont(font);
		login.setToolTipText("�α����Ϸ� ����");
		login.setBackground(new Color(0X55CCCCFF));
		login.addActionListener(actionHandler);
		login.setActionCommand("find&login");
		buttonP.add(login);
		//��� ��ư
		cancel = new JButton();
		cancel.setText("���");
		cancel.setFont(font);
		cancel.setBackground(new Color(0X55CCCCFF));
		cancel.setToolTipText("���̵� �� ��й�ȣ ã�� ����");
		cancel.addActionListener(actionHandler);
		cancel.setActionCommand("cancelfind");
		buttonP.add(cancel);
		//���ΰ�ħ ��ư
		refresh = new JButton("�ʱ�ȭ");
		refresh.setBackground(new Color(0X55CCCCFF));
		refresh.setToolTipText("���ΰ�ħ�ϱ�");
		refresh.setFont(font);
		refresh.addActionListener(actionHandler);
		refresh.setActionCommand("refresh");
		buttonP.add(refresh);
		
	}
	//��й�ȣ ã�� �г�
	private void pwinPanel(JPanel pwinP, ActionListener actionHandler) {
		pwinP.setLayout(new FlowLayout());
		pwinP.setBackground(Color.white);
		//Label
		userID = new JLabel();
		userID.setText("���̵�");
		userID.setFont(font);
		pwinP.add(userID);
		// ���̵� �Է�
		id = new JTextField(10);
		id.setToolTipText("���̵� �Է����ּ���.");
		id.registerKeyboardAction(actionHandler,"findPW",KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0),JComponent.WHEN_FOCUSED);
		pwinP.add(id);
		//Ȯ�� ��ư
		ok2 = new JButton();
		ok2.setText("Ȯ��");
		ok2.setFont(font);
		ok2.setBackground(new Color(0X55CCCCFF));
		ok2.addActionListener(actionHandler);
		ok2.setActionCommand("findPW");
		pwinP.add(ok2);
	}
	private void pwLabel(JPanel pwLP) {
		pwLP.setLayout(new FlowLayout());
		pwLP.setBackground(Color.white);
		//Label
		PWfind = new JLabel("��й�ȣ ã��");
		PWfind.setFont(new Font("���", Font.BOLD, 15));
		pwLP.add(PWfind);
	}
	private void idLPanel(JPanel idLP) {
		idLP.setLayout(new FlowLayout());
		idLP.setBackground(Color.white);
		//Label
		IDfind = new JLabel("���̵� ã��");
		IDfind.setFont(new Font("���", Font.BOLD,15));
		idLP.add(IDfind);
	}
	private void namePanel(JPanel nameP) {
		nameP.setLayout(new FlowLayout());
		nameP.setBackground(Color.white);
		//Label
		name = new JLabel();
		name.setText("�̸�");
		name.setFont(font);
		nameP.add(name);
		//�̸� �Է�
		userName = new JTextField(10);
		userName.setToolTipText("�л��� �̸��� �Է����ּ���.");
		nameP.add(userName);
	}
	//student���Ͽ��� �� �о���� - ���̵��
	private void read(Scanner scanner) {
		rUserName = scanner.next();
		rUserMajor = scanner.next();
		rUserGrade = scanner.next();
		rUserID = scanner.next();
		rUserPW = scanner.next();
	}
	//student ���Ͽ��� �� �о���� - ��й�ȣ��
	private void read2(Scanner sc) {
		rUserID = sc.next();
		rUserPW = sc.next();
	}
	//id ã��
	public String findID() {
		try {
			Scanner scanner = new Scanner(new File("student/student" ));
			while(scanner.hasNext()) {
			this.read(scanner);
			if(this.rUserName.equals(userName.getText()) && this.rUserMajor.equals(major.getText())) {
				return rUserID;
			}
		}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	//��й�ȣ ã��
	public String findPW(String findID) {
		Scanner sc;
		try {
			sc = new Scanner(new File("student/" + findID + "login" ));
			while(sc.hasNext()) {
				this.read2(sc);
				if(this.rUserID.equals(id.getText())) {
					return rUserPW;
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	//���ΰ�ħ
		public void refresh() {
			this.id.setText(null);
			this.userName.setText(null);
			this.major.setText(null);
		}
}
