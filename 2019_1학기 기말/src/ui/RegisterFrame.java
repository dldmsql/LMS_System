package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

public class RegisterFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel subPanel;
	private JTextField name; //이름
	public JTextField id; //아이디
	private JPasswordField pw; //비밀번호
	private JTextField major;
	private JLabel nameLabel; 
	private JLabel idLabel;
	private JLabel pwLabel;
	private JLabel majorLabel;
	private JButton signup;
	private JButton store;
	private JButton cancel;
	private JButton overlap;
	private Font font;
	private String rUserId;
	public String selectedcom;
	private JComboBox combo;
	private JComboBox com;
	private JComboBox comColor;
	private JLabel refreshLabel;
	private JButton refresh;
	private JLabel size;
	private JLabel message;
	private JLabel textColor;
	private File file;

	public  RegisterFrame(ActionListener actionHandler) {
		//setting
		this.setTitle("회원가입");
		this.setFont(font);
		this.setSize(500, 450);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		//font 
		font = new Font("고딕", Font.PLAIN, 15);
		//imageIcon
		Toolkit toolkit = this.getToolkit();
		Image img = toolkit.createImage("image/mju_icon.jpg");
		this.setIconImage(img);
		//Main Panel 
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBackground(Color.white);
		//sub1 Panel
		JPanel toolbarPanel = new JPanel();
		toolbarPanel.setBackground(new Color(0X55CCCCFF));
		mainPanel.add(toolbarPanel, BorderLayout.NORTH);
		//toolbar
		JToolBar tool = new JToolBar("Menu");
		tool.setBackground(new Color(0X44CCCCFF));
		toolbarPanel.add(tool);
		//refresh button
		refreshLabel = new JLabel();
		refreshLabel.setFont(font);
		refreshLabel.setText("새로고침");
		tool.add(refreshLabel);

		refresh = new JButton("refresh");
		refresh.setBackground(Color.white);
		refresh.setBorderPainted(false); 
		refresh.setToolTipText("새로고침하기");
		refresh.setFont(font);
		refresh.addActionListener(actionHandler);
		refresh.setActionCommand("refresh");
		tool.add(refresh);
		tool.addSeparator();
		//글씨 크기 combobox
		size = new JLabel();
		size.setFont(font);
		size.setText("크기");
		tool.add(size);
		combo = new JComboBox();
		combo.addItem("10");
		combo.addItem("13");
		combo.addItem("15");
		combo.addItem("17");
		combo.setFont(font);
		combo.setBackground(Color.white);
		combo.addActionListener(actionHandler);
		combo.setActionCommand("combo");
		tool.add(combo);
		//글씨 색상
		textColor = new JLabel("색상");
		textColor.setFont(font);
		tool.add(textColor);
		comColor = new JComboBox();
		comColor.addItem("Red");
		comColor.addItem("Blue");
		comColor.addItem("Green");
		comColor.addItem("Gray");
		comColor.addItem("Black");
		comColor.setFont(font);
		comColor.setBackground(Color.white);
		comColor.addActionListener(actionHandler);
		comColor.setActionCommand("Color");
		tool.add(comColor);
		//sub Panel
		subPanel = new JPanel();
		subPanel.setBackground(Color.white);
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.Y_AXIS));
		mainPanel.add(subPanel);
		//name Panel
		JPanel nameP = new JPanel();
		namePanel(nameP);
		subPanel.add(nameP);
		//major Panel
		JPanel majorP = new JPanel();
		majorPanel(majorP);
		subPanel.add(majorP);
		//id Panel
		JPanel idp = new JPanel();
		idPanel(idp,actionHandler);
		subPanel.add(idp);
		//pw Panel
		JPanel pwP = new JPanel();
		pwPanel(pwP,actionHandler);
		subPanel.add(pwP);
		//message Panel
		JPanel messageP = new JPanel();
		messagePanel(messageP);
		subPanel.add(messageP);
		//button Panel
		JPanel btP = new JPanel();
		btPanel(btP,actionHandler);
		subPanel.add(btP);

		this.add(mainPanel);
	}
	private void majorPanel(JPanel majorP) {
		majorP.setLayout(new FlowLayout());
		majorP.setBackground(Color.white);

		majorLabel = new JLabel();
		majorLabel.setText("학과/학부");
		majorLabel.setFont(font);
		majorP.add(majorLabel);

		major = new JTextField(15);
		major.setToolTipText("학과 또는 학부를 입력해주세요.");
		majorP.add(major);

		JLabel gradeLabel = new JLabel();
		gradeLabel.setText("학년");
		majorLabel.setFont(font);
		majorP.add(gradeLabel);

		String grade[]= {"1","2","3","4"};
		com = new JComboBox(grade);
		com.setFont(font);
		selectedcom = com.getSelectedItem().toString();
		com.setBackground(Color.white);
		majorP.add(com);
	}
	private void messagePanel(JPanel messageP) {
		messageP.setLayout(new FlowLayout());
		messageP.setBackground(Color.white);

		message = new JLabel();
		message.setText("비밀번호는 영문과 숫자 조합으로 10자이하로 입력해주세요.");
		message.setFont(font);
		messageP.add(message);
	}
	private void namePanel(JPanel nameP) {
		nameP.setLayout(new FlowLayout());
		nameP.setBackground(Color.white);

		nameLabel = new JLabel("이름 ");
		nameLabel.setFont(font);
		nameP.add(nameLabel);

		name = new JTextField(10);
		name.setToolTipText("이름을 입력해주세요.");
		nameP.add(name);
	}
	private void btPanel(JPanel btP, ActionListener actionHandler) {
		btP.setLayout(new FlowLayout());
		btP.setBackground(Color.white);

		signup = new JButton();
		signup.setText("로그인");
		signup.setToolTipText("로그인하러가기");
		signup.setFont(font);
		signup.setBackground(new Color(0X55CCCCFF));
		signup.addActionListener(actionHandler);
		signup.setActionCommand("signup");
		btP.add(signup);

		store = new JButton();
		store.setText("저장");
		store.setToolTipText("회원정보 저장하기");
		store.setFont(font);
		store.setBackground(new Color(0X55CCCCFF));
		store.addActionListener(actionHandler);
		store.setActionCommand("store");
		btP.add(store);

		cancel = new JButton();
		cancel.setText("취소");
		cancel.setToolTipText("회원가입 취소하기");
		cancel.setFont(font);
		cancel.setBackground(new Color(0X55CCCCFF));
		cancel.addActionListener(actionHandler);
		cancel.setActionCommand("cancelRegister");
		btP.add(cancel);
	}
	private void pwPanel(JPanel pwP, ActionListener actionHandler) {
		pwP.setLayout(new FlowLayout());
		pwP.setBackground(Color.white);

		pwLabel = new JLabel("비밀번호");
		pwLabel.setFont(font);
		pwP.add(pwLabel);

		pw = new JPasswordField(10);
		pw.setToolTipText("비밀번호를 입력해주세요");
		pw.registerKeyboardAction(actionHandler,"store",KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0),JComponent.WHEN_FOCUSED);
		pw.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent ke) {
				JTextField src = (JTextField)ke.getSource();
				if(src.getText().length() >= 10) {
					JOptionPane.showMessageDialog(null, "비밀번호가 10자리를 넘었습니다.");
				} 
			}
		});
		pwP.add(pw);
	}
	private void idPanel(JPanel idp, ActionListener actionHandler) {
		idp.setLayout(new FlowLayout());
		idp.setBackground(Color.white);

		idLabel = new JLabel("아이디 ");
		idLabel.setFont(font);
		idp.add(idLabel);

		id = new JTextField(10);
		id.setToolTipText("아이디를 입력해주세요");
		idp.add(id);

		overlap = new JButton();
		overlap.setText("중복확인");
		overlap.setFont(font);
		overlap.setBackground(new Color(0X55CCCCFF));
		overlap.addActionListener(actionHandler);
		overlap.setActionCommand("idoverlap");
		idp.add(overlap);
	}
	//파일에 저장
	public void store() {
		try {
			file = new File("student/" + id.getText() + "Info");
			if(!(file.exists())) {
				file.createNewFile();
			}
			File login = new File("student/" + id.getText() + "login");
			if(!(login.exists())) {
				login.createNewFile();
			}
			BufferedWriter bw = new BufferedWriter(new FileWriter(file,true));

			bw.write(name.getText() + "\r\n");
			bw.write(major.getText()+ "\r\n");
			bw.write(selectedcom + "\r\n");
			bw.write(id.getText() + "\r\n");
			bw.write(pw.getText() + "\r\n");
			bw.close();

			BufferedWriter bw2 = new BufferedWriter(new FileWriter("student/student",true));

			bw2.write(name.getText() + "\r\n");
			bw2.write(major.getText()+ "\r\n");
			bw2.write(selectedcom + "\r\n");
			bw2.write(id.getText() + "\r\n");
			bw2.write(pw.getText() + "\r\n");
			bw2.close();

			BufferedWriter bw3 = new BufferedWriter(new FileWriter("student/" + id.getText() +"login",true));
			bw3.write(id.getText() + " ");
			bw3.write(pw.getText()+ "\r\n");
			bw3.close();
			
			BufferedWriter bw4 = new BufferedWriter(new FileWriter("data/login",true));
			bw4.write(id.getText() + "\r\n");
			bw4.close();
			
			JOptionPane.showMessageDialog(null, "회원가입을 축하합니다.");
			this.dispose();
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "회원가입에 실패하셨습니다.");
		}	
	}
	//student파일 존재하는 아이디 읽는 함수
	private void read(Scanner scanner) {
		rUserId = scanner.next();
	}
	//Id 중복확인
	public String overlap() {
		Scanner scanner;
		try {
			scanner = new Scanner(new File("data/login"));
			while(scanner.hasNext()) {
				this.read(scanner);
				if(this.rUserId.equals(id.getText())) {
					System.out.println(id.getText());
					return id.getText();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	//새로고침
	public void refresh() {
		this.id.setText(null);
		this.pw.setText(null);
		this.name.setText(null);
		this.major.setText(null);
	}
	public void combo() {
		int index = combo.getSelectedIndex();
		if(index == 0) {
			Font font1 = new Font("고딕", Font.PLAIN , 10);
			refreshLabel.setFont(font1);
			textColor.setFont(font1);
			size.setFont(font1);
			refresh.setFont(font1);
			overlap.setFont(font1);
			cancel.setFont(font1);
			store.setFont(font1);
			signup.setFont(font1);
			majorLabel.setFont(font1);
			pwLabel.setFont(font1);
			idLabel.setFont(font1);
			nameLabel.setFont(font1);
			com.setFont(font1);
			combo.setFont(font1);
			message.setFont(font1);
			comColor.setFont(font1);
		}else if(index == 1) {
			Font font2 = new Font("고딕", Font.PLAIN , 13);
			refreshLabel.setFont(font2);
			textColor.setFont(font2);
			size.setFont(font2);
			refresh.setFont(font2);
			overlap.setFont(font2);
			cancel.setFont(font2);
			store.setFont(font2);
			signup.setFont(font2);
			majorLabel.setFont(font2);
			pwLabel.setFont(font2);
			idLabel.setFont(font2);
			nameLabel.setFont(font2);
			com.setFont(font2);
			combo.setFont(font2);
			message.setFont(font2);
			comColor.setFont(font2);
		}else if(index ==2) {
			Font font3 = new Font("고딕", Font.PLAIN , 15);
			refreshLabel.setFont(font3);
			textColor.setFont(font3);
			size.setFont(font3);
			refresh.setFont(font3);
			overlap.setFont(font3);
			cancel.setFont(font3);
			store.setFont(font3);
			signup.setFont(font3);
			majorLabel.setFont(font3);
			pwLabel.setFont(font3);
			idLabel.setFont(font3);
			nameLabel.setFont(font3);
			com.setFont(font3);
			combo.setFont(font3);
			message.setFont(font3);
			comColor.setFont(font3);
		}else if(index ==3) {
			Font font4 = new Font("고딕", Font.PLAIN , 17);
			refreshLabel.setFont(font4);
			textColor.setFont(font4);
			size.setFont(font4);
			refresh.setFont(font4);
			overlap.setFont(font4);
			cancel.setFont(font4);
			store.setFont(font4);
			signup.setFont(font4);
			majorLabel.setFont(font4);
			pwLabel.setFont(font4);
			idLabel.setFont(font4);
			nameLabel.setFont(font4);
			com.setFont(font4);
			combo.setFont(font4);
			message.setFont(font4);
			comColor.setFont(font4);
		}
	}
	public void colorChange() {
		int index = comColor.getSelectedIndex();
		if(index == 0) {
			refreshLabel.setForeground(Color.red);
			textColor.setForeground(Color.red);
			size.setForeground(Color.red);
			refresh.setForeground(Color.red);
			overlap.setForeground(Color.red);
			cancel.setForeground(Color.red);
			store.setForeground(Color.red);
			signup.setForeground(Color.red);
			majorLabel.setForeground(Color.red);
			pwLabel.setForeground(Color.red);
			idLabel.setForeground(Color.red);
			nameLabel.setForeground(Color.red);
			com.setForeground(Color.red);
			combo.setForeground(Color.red);
			message.setForeground(Color.red);
			comColor.setForeground(Color.red);
		}else if(index == 1) {
			refreshLabel.setForeground(Color.blue);
			size.setForeground(Color.blue);
			textColor.setForeground(Color.blue);
			refresh.setForeground(Color.blue);
			overlap.setForeground(Color.blue);
			cancel.setForeground(Color.blue);
			store.setForeground(Color.blue);
			signup.setForeground(Color.blue);
			majorLabel.setForeground(Color.blue);
			pwLabel.setForeground(Color.blue);
			idLabel.setForeground(Color.blue);
			nameLabel.setForeground(Color.blue);
			com.setForeground(Color.blue);
			combo.setForeground(Color.blue);
			message.setForeground(Color.blue);
			comColor.setForeground(Color.blue);
		}else if(index ==2) {
			refreshLabel.setForeground(Color.green);
			textColor.setForeground(Color.green);
			size.setForeground(Color.green);
			refresh.setForeground(Color.green);
			overlap.setForeground(Color.green);
			cancel.setForeground(Color.green);
			store.setForeground(Color.green);
			signup.setForeground(Color.green);
			majorLabel.setForeground(Color.green);
			pwLabel.setForeground(Color.green);
			idLabel.setForeground(Color.green);
			nameLabel.setForeground(Color.green);
			com.setForeground(Color.green);
			combo.setForeground(Color.green);
			message.setForeground(Color.green);
			comColor.setForeground(Color.green);
		}else if(index ==3) {
			refreshLabel.setForeground(Color.gray);
			textColor.setForeground(Color.gray);
			size.setForeground(Color.gray);
			refresh.setForeground(Color.gray);
			overlap.setForeground(Color.gray);
			cancel.setForeground(Color.gray);
			store.setForeground(Color.gray);
			signup.setForeground(Color.gray);
			majorLabel.setForeground(Color.gray);
			pwLabel.setForeground(Color.gray);
			idLabel.setForeground(Color.gray);
			nameLabel.setForeground(Color.gray);
			com.setForeground(Color.gray);
			combo.setForeground(Color.gray);
			message.setForeground(Color.gray);
			comColor.setForeground(Color.gray);
		}else if(index ==4) {
			refreshLabel.setForeground(Color.black);
			textColor.setForeground(Color.black);
			size.setForeground(Color.black);
			refresh.setForeground(Color.black);
			overlap.setForeground(Color.black);
			cancel.setForeground(Color.black);
			store.setForeground(Color.black);
			signup.setForeground(Color.black);
			majorLabel.setForeground(Color.black);
			pwLabel.setForeground(Color.black);
			idLabel.setForeground(Color.black);
			nameLabel.setForeground(Color.black);
			com.setForeground(Color.black);
			combo.setForeground(Color.black);
			message.setForeground(Color.black);
			comColor.setForeground(Color.black);
		}
	}
}
