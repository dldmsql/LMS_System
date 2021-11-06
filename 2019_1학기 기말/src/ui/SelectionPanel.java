package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import entity.ELecture;


@SuppressWarnings("serial")
public class SelectionPanel extends JPanel {

	private DirectoryPanel departmentSelection;
	private LectureTable lectureSelection;
	private ListSelectionHandler listHandler;
	private JButton button;
	private JButton logoutbutton;
	private JButton userinfo;
	private JButton basket;
	private JButton urlbutton;
	private Font font;
	private JLabel label;
	private JLabel tableLabel;
	private ImageIcon image;
	private JLabel imageLabel;
	private ImageIcon userImage;
	private JLabel uILabel;
	private JButton changePW;

	public SelectionPanel(ActionListener actionHandler, String id) {
		//layout
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		//event
		this.listHandler = new ListSelectionHandler();
		//font
		font = new Font("고딕", Font.BOLD,15);
		//상단바 Panel
		JPanel upPanel = new JPanel();
		upPanel.setLayout(new BorderLayout());
		upPanel.setBackground(new Color(0X55CCCCFF));
		//image
		this.image = new ImageIcon("image/mju_2.png");
		this.imageLabel = new JLabel(image, JLabel.CENTER);
		upPanel.add(imageLabel, BorderLayout.LINE_START);
		//collge Label
		JLabel college = new JLabel("명지대학교");
		college.setFont(new Font("고딕", Font.BOLD, 45));
		college.setForeground(Color.white);
		upPanel.add(college, BorderLayout.CENTER);
		this.add(upPanel);
		//sub Panel
		JScrollPane scrollPane = new JScrollPane();
		JPanel subP = new JPanel();
		scrollPane.setViewportView(subP);
		subP.setLayout(new BorderLayout());
		subP.setBackground(Color.white);
		this.add(scrollPane);
		//Left Panel
		JPanel leftP = new JPanel();
		leftP.setLayout(new BoxLayout(leftP, BoxLayout.Y_AXIS));
		leftP.setBackground(Color.white);
		subP.add(leftP,BorderLayout.WEST);
		//userImage 
		userImage = new ImageIcon("image/user.jpg");
		uILabel = new JLabel(userImage, JLabel.CENTER);
		leftP.add(uILabel);
		//아이디Label
		label = new JLabel();
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setFont(font);
		leftP.add(label);
		//개인정보확인 button
		userinfo = new JButton("개인정보확인");
		userinfo.setFont(font);
		userinfo.setOpaque(false);
		userinfo.setBorderPainted(false);
		userinfo.setBackground(new Color(0X55CCCCFF));
		userinfo.setToolTipText("개인정보확인하기");
		userinfo.addActionListener(actionHandler);
		userinfo.setActionCommand("userinfo");
		leftP.add(userinfo);
		//basket button
		basket = new JButton("책가방");
		basket.setFont(font);
		basket.setOpaque(false); 
		basket.setBorderPainted(false); 
		basket.setBackground(new Color(0X55CCCCFF));
		basket.setToolTipText("책가방 내역 및 신청 내역 확인하러 가기");
		basket.addActionListener(actionHandler);
		basket.setActionCommand("showBasket");
		leftP.add(basket);
		//홈페이지 연결
		urlbutton = new JButton("바로가기");
		urlbutton.setFont(font);
		urlbutton.setOpaque(false);
		urlbutton.setBorderPainted(false);
		urlbutton.setBackground(Color.white);
		urlbutton.setToolTipText("명지대 홈페이지 바로가기");
		urlbutton.addActionListener(actionHandler);
		urlbutton.setActionCommand("gotourl");
		leftP.add(urlbutton);
		//logout button
		logoutbutton = new JButton("로그아웃");
		logoutbutton.setFont(font);
		logoutbutton.setOpaque(false);
		logoutbutton.setBorderPainted(false);
		logoutbutton.setBackground(new Color(0X55CCCCFF));
		logoutbutton.setToolTipText("로그아웃");
		logoutbutton.addActionListener(actionHandler);
		logoutbutton.setActionCommand("logout");
		leftP.add(logoutbutton,BorderLayout.EAST);
		//비밀번호 변경
		changePW = new JButton();
		changePW.setFont(font);
		changePW.setText("비밀번호 변경");
		changePW.setOpaque(false);
		changePW.setBorderPainted(false);
		changePW.setBackground(Color.white);
		changePW.setToolTipText("비밀번호 변경하기");
		changePW.addActionListener(actionHandler);
		changePW.setActionCommand("changePW");
		leftP.add(changePW);
		//right Panel
		JPanel rightP = new JPanel();
		rightP.setBackground(Color.white);
		rightP.setLayout(new BoxLayout(rightP, BoxLayout.Y_AXIS));
		subP.add(rightP);
		//Label Panel
		JPanel labelPanel = new JPanel();
		labelPanel.setBackground(Color.white);
		labelPanel.setLayout(new FlowLayout());
		rightP.add(labelPanel);
		//deprtmentSelection Label
		JLabel dpLabel = new JLabel();
		dpLabel.setText("Campus        ");
		dpLabel.setFont(new Font("고딕", Font.BOLD, 16));
		labelPanel.add(dpLabel);
		JLabel dpLabel2 = new JLabel();
		dpLabel2.setText("College");
		dpLabel2.setFont(new Font("고딕", Font.BOLD, 16));
		labelPanel.add(dpLabel2);
		JLabel dpLabel3 = new JLabel();
		dpLabel3.setText("        Department");
		dpLabel3.setFont(new Font("고딕", Font.BOLD, 16));
		labelPanel.add(dpLabel3);
		//departmentSelection Panel
		this.departmentSelection = new DirectoryPanel(listHandler);
		rightP.add(this.departmentSelection);
		//Tabel Label
		tableLabel = new JLabel();
		tableLabel.setText("강좌정보 Table");
		tableLabel.setFont(new Font("고딕", Font.BOLD,18));
		rightP.add(tableLabel);
		//lectureTable
		scrollPane = new JScrollPane();
		this.lectureSelection = new LectureTable();
		scrollPane.setPreferredSize(new Dimension(400,200));
		scrollPane.getViewport().setBackground(Color.white);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.setViewportView(this.lectureSelection);
		rightP.add(scrollPane);
		//Button_select
		button = new JButton("선택");
		button.setFont(font);
		button.setBackground(new Color(0X55CCCCFF));
		button.setToolTipText("책가방에 담기");
		button.addActionListener(actionHandler);
		button.setActionCommand("lectureSelect");
		rightP.add(button);
		//down Panel
		JPanel downP = new JPanel();
		downP.setBackground(Color.white);
		this.add(downP);
		//address Label
		JLabel downLabel = new JLabel();
		downLabel.setText("인문캠퍼스 : 서울특별시 서대문구 거북골로 34 02)300-1700 "
				+ "자연캠퍼스 : 경기도 용인시 처인구 명지로 116 031)330-6114");
		downLabel.setFont(font);
		downP.add(downLabel);
	}
	//event
	public class ListSelectionHandler implements ListSelectionListener {

		// departmentSelection에서 event 받아온 것을 fileName에 담고 lectureSelection에 보낸다.
		@Override
		public void valueChanged(ListSelectionEvent event) {
			String fileName = departmentSelection.refresh(event);
			lectureSelection.refresh(fileName);
		}
	}
	// list에 처음부터 선택되도록 지정해주는 함수
	public void initiate() {
		departmentSelection.init();
	}
	//Vector isselected에 담겼으면 넘겨주는 함수
	public Vector<ELecture> showFrame() {
		return lectureSelection.select();
	}
	public void setID(String id) {
		this.label.setText("      " + id + "님");
	}
}