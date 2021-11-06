package ui;

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
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class ChangePW extends JFrame {

	private static final long serialVersionUID = 1L;

	private JLabel pw;
	private JPasswordField pwText;
	private JLabel pw2;
	private JPasswordField pwText2;
	private JButton changeButton;
	private Font font;
	private JPanel mainPanel;
	private String rUserName;
	private String rUserMajor;
	private String rUserGrade;
	private String rUserID;
	private String rUserPW;

	public ChangePW(ActionListener actionHandler) {
		//setting
		this.setTitle("개인정보 변경");
		this.setFont(font);
		this.setSize(500,450);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		//font
		font = new Font("고딕", Font.PLAIN, 15);

		//imageIcon 
		Toolkit toolkit = this.getToolkit();
		Image img = toolkit.createImage("image/mju_icon.jpg");
		this.setIconImage(img);

		//Main Panel
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.white);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		//empty Panel
		JPanel ePanel = new JPanel();
		ePanel.setBackground(Color.white);
		mainPanel.add(ePanel);
		//pw Panel
		JPanel pwPanel = new JPanel();
		pwPanel.setLayout(new FlowLayout());
		pwPanel.setBackground(Color.white);
		//pwLabel 
		pw = new JLabel("새 비밀번호");
		pw.setFont(font);
		pw.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent ke) {
				JTextField src = (JTextField)ke.getSource();
				if(src.getText().length() >= 10) {
					JOptionPane.showMessageDialog(null, "비밀번호가 10자리를 넘었습니다.");
				} 
			}
		});
		pwPanel.add(pw);
		pwText = new JPasswordField(10);
		pwText.setToolTipText("비밀번호는 영문자와 숫자를 합쳐서 10자리 이내로 설정해주세요.");
		pwPanel.add(pwText);
		mainPanel.add(pwPanel);

		//pw2 Panel
		JPanel pw2Panel = new JPanel();
		pw2Panel.setLayout(new FlowLayout());
		pw2Panel.setBackground(Color.white);
		//pw2 Label
		pw2 = new JLabel("비밀번호 확인");
		pw2.setFont(font);
		pw2.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent ke) {
				JTextField src = (JTextField)ke.getSource();
				if(src.getText().length() >= 10) {
					JOptionPane.showMessageDialog(null, "비밀번호가 10자리를 넘었습니다.");
				} 
			}
		});
		pw2Panel.add(pw2);
		pwText2 = new JPasswordField(10);
		pwText2.setToolTipText("비밀번호는 영문자와 숫자를 합쳐서 10자리 이내로 설정해주세요.");
		pwText2.registerKeyboardAction(actionHandler,"newPW",KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0),JComponent.WHEN_FOCUSED);
		pw2Panel.add(pwText2);
		mainPanel.add(pw2Panel);

		//button Panel
		JPanel btPanel = new JPanel();
		btPanel.setBackground(Color.white);
		changeButton = new JButton("비밀번호 변경");
		changeButton.setFont(font);
		changeButton.setToolTipText("비밀번호 변경하기");
		changeButton.setBackground(new Color(0X55CCCCFF));
		changeButton.addActionListener(actionHandler);
		changeButton.setActionCommand("newPW");
		btPanel.add(changeButton);
		mainPanel.add(btPanel);

		this.add(mainPanel);
	}
	private void read(Scanner scanner) {
		rUserName = scanner.next();
		rUserMajor = scanner.next();
		rUserGrade = scanner.next();
		rUserID = scanner.next();
		rUserPW = scanner.next();


	}

	public void newPW(String ID, String selectedcomIndex) {
		rUserGrade = selectedcomIndex;
		if(pwText.getText().equals(pwText2.getText())) {
			try {
				Scanner scanner = new Scanner(new File("student/" + ID + "Info"));
				while(scanner.hasNext()) {
					this.read(scanner);
				}
				File file = new File("student/" + ID + "Info");
				file.delete();
				if(rUserID.equals(ID)) {

					BufferedWriter bw = new BufferedWriter(new FileWriter("student/" + ID + "Info",false));
					bw.write(rUserName  +"\r\n");
					bw.write(rUserMajor +"\r\n");
					bw.write(rUserGrade +"\r\n");
					bw.write(rUserID +"\r\n");
					bw.write(pwText.getText() +"\r\n");
					bw.close();

					File file2 = new File("student/" + ID + "login");
					file2.delete();
					BufferedWriter bw2 = new BufferedWriter(new FileWriter("student/" + ID + "login",true));
					bw2.write(rUserID + "\r\n");
					bw2.write(pwText.getText() +"\r\n");
					bw2.close();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "비밀번호를 성공적으로 바꾸셨습니다.");
			this.dispose();
		}else {
			JOptionPane.showMessageDialog(null, "비밀번호를 알맞게 입력하였는지 다시 확인해주세요.");
		}
	}
}
