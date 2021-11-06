package ui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private Font font;
	private ImageIcon image;
	public JTextField idText;
	public JPasswordField pwText;
	private JButton button;
	private JButton register;
	private JButton find;


	public LoginFrame(ActionListener actionHandler, KeyListener keyHandler) {

		//setting
		this.setTitle("로그인");
		this.setSize(600, 605);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		//imageIcon
		Toolkit toolkit = this.getToolkit();
		Image img = toolkit.createImage("image/mju_icon.jpg");
		this.setIconImage(img);

		//Font
		font = new Font("고딕", Font.PLAIN, 25);

		//Main panel
		JPanel panel = new JPanel();
		panel.setBackground(Color.white);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		//Image Panel
		JPanel imageP = new JPanel();
		imagePanel(imageP);
		panel.add(imageP);

		//Id Panel
		JPanel idP = new JPanel();
		idPanel(idP);
		panel.add(idP);

		//Pw Panel
		JPanel pwP = new JPanel();
		pwPanel(pwP,keyHandler);
		panel.add(pwP);

		//Login Panel
		JPanel loginP = new JPanel();
		loginPanel(loginP,actionHandler);
		panel.add(loginP);

		this.add(panel);
		this.setVisible(true);
	}
	private void loginPanel(JPanel loginP, ActionListener actionHandler) {
		this.setLayout(new FlowLayout());
		loginP.setBackground(Color.white);

		register = new JButton();
		register.setText("회원가입");
		register.setToolTipText("회원가입하기");
		register.setSize(200, 85);
		register.setFont(font);
		register.setBackground(new Color(0X55CCCCFF));
		register.addActionListener(actionHandler);
		register.setActionCommand("register");
		loginP.add(register);

		find = new JButton();
		find.setText("아이디/비밀번호 찾기");
		find.setToolTipText("아이디/비밀번호 찾기");
		find.setFont(font);
		find.setSize(200, 85);
		find.setBackground(new Color(0X55CCCCFF));
		find.addActionListener(actionHandler);
		find.setActionCommand("find");
		loginP.add(find);

		button = new JButton();
		button.setText(" 로그인  ");
		button.setToolTipText("로그인하기");
		button.setSize(200, 85);
		button.setFont(font);
		button.setBackground(new Color(0X55CCCCFF));
		button.addActionListener(actionHandler);
		button.setActionCommand("login");
		loginP.add(button);
	}
	private void pwPanel(JPanel pwP, KeyListener keyHandler) {
		pwP.setLayout(new FlowLayout());
		pwP.setBackground(Color.white);

		JLabel pwLabel = new JLabel("비밀번호    ");
		pwLabel.setFont(font);
		pwP.add(pwLabel);

		pwText = new JPasswordField(10);
		pwText.setToolTipText("비밀번호를 입력해주세요.");
		pwText.setSize(200, 85);
		pwText.addKeyListener(keyHandler);
		pwText.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent ke) {
				JTextField src = (JTextField)ke.getSource();
				if(src.getText().length() >= 10) {
					JOptionPane.showMessageDialog(null, "비밀번호가 10자리를 넘었습니다.");
				} 
			}
		});
		pwP.add(pwText);
	}

		private void idPanel(JPanel idP) {
			idP.setLayout(new FlowLayout());
			idP.setBackground(Color.white);

			JLabel idLabel = new JLabel("아이디    ");
			idLabel.setFont(font);
			idP.add(idLabel);

			idText = new JTextField(10);
			idText.setToolTipText("아이디를 입력해주세요.");
			idText.setSize(200, 85);
			idP.add(idText);
		}
		private void imagePanel(JPanel imageP) {
			this.setLayout(null);
			imageP.setBackground(Color.white);

			image = new ImageIcon("image/Mju_logo.jpg");
			JLabel lb = new JLabel(image, JLabel.CENTER);
			imageP.add(lb);
		}

	}


