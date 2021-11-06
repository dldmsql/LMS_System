package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

import entity.ELecture;

public class BasketFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private Font font;
	private BasketTable basketTable;
	private SincheongTable sincheongTable;
	private JButton selectbutton;
	private JButton deletebutton;
	private JButton cancelbutton;
	private JButton backToBasket;
	private String id;
	private JMenuBar mb;
	private JMenu menu;
	private JMenuItem userInfo;
	private JMenuItem logout;
	private JMenu menu2;
	private JComboBox fontcom;
	
	public BasketFrame(ActionListener actionHandler) {
		this.setLayout(new FlowLayout());

		//setting
		this.setTitle("마이페이지");
		this.setSize(600, 500);
		this.setFont(font);
		this.setLocationRelativeTo(null);

		//imageIcon
		Toolkit toolkit = this.getToolkit();
		Image img = toolkit.createImage("image/mju_icon.jpg");
		this.setIconImage(img);
		//Font
		this.font = new Font("고딕", Font.PLAIN, 10);

		//panel
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(Color.white);
		
		//menubar
		mb = new JMenuBar();
		mb.setBackground(new Color(0X55CCCCFF));
		menu = new JMenu("Info");
		menu.setFont(font);
		menu.setMnemonic('I');
		menu2 = new JMenu("Exit");
		menu2.setFont(font);
		menu2.setMnemonic('E');
		mb.add(menu);
		mb.add(menu2);
		userInfo = new JMenuItem("user");
		
		userInfo.setFont(font);
		userInfo.addActionListener(actionHandler);
		userInfo.setToolTipText("개인정보 열람하기");
		userInfo.setActionCommand("user");
		userInfo.setAccelerator(KeyStroke.getKeyStroke('U', KeyEvent.CTRL_MASK));
		menu.add(userInfo);
		logout = new JMenuItem("logout");
		logout.setFont(font);
		logout.setToolTipText("로그아웃하기");
		logout.addActionListener(actionHandler);
		logout.setActionCommand("logout");
		logout.setAccelerator(KeyStroke.getKeyStroke('L', KeyEvent.CTRL_MASK) );
		menu2.add(logout);
		panel.add(mb);
		
		//toolbar
		JToolBar tool = new JToolBar("fontSize");
		tool.setBackground(Color.white);
		panel.add(tool);
		JLabel fontLabel = new JLabel("글씨 크기");
		tool.add(fontLabel);
		fontcom = new JComboBox();
		fontcom.addItem("10");
		fontcom.addItem("13");
		fontcom.addItem("15");
		fontcom.addItem("17");
		fontcom.setFont(font);
		fontcom.setBackground(Color.white);
		fontcom.addActionListener(actionHandler);
		fontcom.setActionCommand("fontSize");
		tool.add(fontcom);
			
		JPanel blabelP = new JPanel();
		blabelP.setBackground(Color.white);
		blabelPanel(blabelP);
		panel.add(blabelP);

		//Table
		JScrollPane scrollPane = new JScrollPane();
		this.basketTable = new BasketTable();
		scrollPane.getViewport().setBackground(Color.white);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.setViewportView(this.basketTable);
		panel.add(scrollPane);

		JPanel btPanel = new JPanel();
		btPanel.setBackground(Color.white);
		buttonPanel(btPanel,actionHandler);
		panel.add(btPanel);

		JPanel slabelP = new JPanel();
		slabelP.setBackground(Color.white);
		slabelPanel(slabelP);
		panel.add(slabelP);

		scrollPane = new JScrollPane();
		this.sincheongTable = new SincheongTable();
		scrollPane.getViewport().setBackground(Color.white);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.setViewportView(this.sincheongTable);
		panel.add(scrollPane);

		JPanel sbtPanel = new JPanel();
		sbtPanel.setBackground(Color.white);
		buttonPanel2(sbtPanel, actionHandler);
		panel.add(sbtPanel);

		this.add(panel);
	}
	private void slabelPanel(JPanel slabelP) {
		slabelP.setLayout(new BorderLayout());

		//Label
		JLabel sincheongLabel = new JLabel("        신청내역");
		sincheongLabel.setFont(new Font("고딕", Font.BOLD , 15));
		slabelP.add(sincheongLabel,BorderLayout.WEST);
	}
	private void blabelPanel(JPanel blabelP) {
		blabelP.setLayout(new BorderLayout());

		//Label
		JLabel basketLabel = new JLabel("         책가방");
		basketLabel.setFont(new Font("고딕", Font.BOLD , 15));
		blabelP.add(basketLabel,BorderLayout.WEST);
	}
	private void buttonPanel2(JPanel sbtPanel, ActionListener actionHandler) {
		this.setLayout(new BorderLayout());
		//button
		cancelbutton = new JButton("삭제");
		cancelbutton.setFont(font);
		cancelbutton.setToolTipText("신청내역에서 지우기");
		cancelbutton.addActionListener(actionHandler);
		cancelbutton.setActionCommand("cancelSincheong");
		cancelbutton.setBackground(new Color(0XCCCCFF));
		sbtPanel.add(cancelbutton,BorderLayout.EAST);

		backToBasket = new JButton("되돌리기");
		backToBasket.setFont(font);
		backToBasket.setToolTipText("책가방으로 되돌리기");
		backToBasket.addActionListener(actionHandler);
		backToBasket.setActionCommand("backToBasket");
		backToBasket.setBackground(new Color(0XCCCCFF));
		sbtPanel.add(backToBasket,BorderLayout.EAST);

	}
	private void buttonPanel(JPanel btPanel,ActionListener actionHandler ) {

		this.setLayout(new BorderLayout());

		//Button
		selectbutton = new JButton("신청");
		selectbutton.setFont(font);
		selectbutton.setToolTipText("신청하기");
		selectbutton.addActionListener(actionHandler);
		selectbutton.setActionCommand("select");
		selectbutton.setBackground(new Color(0XCCCCFF));
		btPanel.add(selectbutton,BorderLayout.EAST);

		deletebutton = new JButton("삭제");
		deletebutton.setFont(font);
		deletebutton.setToolTipText("책가방에서 지우기");
		deletebutton.addActionListener(actionHandler);
		deletebutton.setActionCommand("deleteBasket");
		deletebutton.setBackground(new Color(0XCCCCFF));
		btPanel.add(deletebutton,BorderLayout.EAST);

	}
	//basketTable에 isselected 값 전달
	public void addItems(Vector<ELecture> isselected) {

		this.basketTable.addItems(isselected);
	}
	//basketTable select함수 실행
	public Vector<ELecture> showFrame() {
		return this.basketTable.select();
	}
	//sincheongTable에 basketSelected값 보내기
	public void givebasket(Vector<ELecture> basketSelected) {
		this.sincheongTable.addItems(basketSelected);
	}
	public void cancel() {
		this.basketTable.cancel();
	}
	public void cancelSincheong() {
		this.sincheongTable.cancel();
	}
	public Vector<ELecture> backToBasket() {
		return this.sincheongTable.backToBasket();
	}
	public void initiate(String iD) {
		this.id = iD;
		this.basketTable.initiate(id);
		this.sincheongTable.initiate(id);
	}
	public Vector<ELecture> saveBasket(){
		return this.basketTable.saveBasket();
	}
	public Vector<ELecture> saveSincheong(){
		return this.sincheongTable.saveSincheong();
	}
	public void fontSize() {
		int index = fontcom.getSelectedIndex();
		this.basketTable.fontSize(index);
		this.sincheongTable.fontSize(index);
		if(index ==0) {
			Font font1 = new Font("고딕", Font.PLAIN, 10);
			menu.setFont(font1);
			menu2.setFont(font1);
			fontcom.setFont(font1);
			cancelbutton.setFont(font1);
			backToBasket.setFont(font1);
			selectbutton.setFont(font1);
			deletebutton.setFont(font1);
		}else if(index ==1) {
			Font font2 = new Font("고딕", Font.PLAIN, 13);
			menu.setFont(font2);
			menu2.setFont(font2);
			fontcom.setFont(font2);
			cancelbutton.setFont(font2);
			backToBasket.setFont(font2);
			selectbutton.setFont(font2);
			deletebutton.setFont(font2);
		}else if(index ==2) {
			Font font3 = new Font("고딕", Font.PLAIN, 15);
			menu.setFont(font3);
			menu2.setFont(font3);
			fontcom.setFont(font3);
			cancelbutton.setFont(font3);
			backToBasket.setFont(font3);
			selectbutton.setFont(font3);
			deletebutton.setFont(font3);
		}else if(index ==3) {
			Font font4 = new Font("고딕", Font.PLAIN, 17);
			menu.setFont(font4);
			menu2.setFont(font4);
			fontcom.setFont(font4);
			cancelbutton.setFont(font4);
			backToBasket.setFont(font4);
			selectbutton.setFont(font4);
			deletebutton.setFont(font4);
		}
	}
}
