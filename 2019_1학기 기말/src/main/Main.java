package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JOptionPane;

import controller.CBasket;
import controller.CLogin;
import controller.CSincheong;
import entity.ELecture;
import ui.BasketFrame;
import ui.ChangePW;
import ui.FindFrame;
import ui.LoginFrame;
import ui.MainFrame;
import ui.RegisterFrame;

public class Main {
	//components
	private LoginFrame loginFrame;
	private ActionHandler actionHandler;
	private MainFrame mainFrame;
	private CLogin cLogin;
	private BasketFrame basketFrame;
	private KeyHandler keyHandler;
	private RegisterFrame registerFrame;
	private FindFrame findFrame;
	private Vector<ELecture> isselected;
	private CBasket cBasket;
	private CSincheong cSincheong;
	private Vector<ELecture> basketSelected;
	private String ID;
	private WindowHandler windowHandler;
	private ChangePW changePW;
	private String selectedcomIndex;

	public Main() {
		this.windowHandler = new WindowHandler();
		this.isselected = new Vector<ELecture>();
		this.basketSelected = new Vector<ELecture>();
		this.actionHandler = new ActionHandler();
		this.keyHandler = new KeyHandler();
		this.loginFrame = new LoginFrame(actionHandler, keyHandler);
		this.registerFrame = new RegisterFrame(actionHandler);
		this.findFrame = new FindFrame(actionHandler);
		this.changePW = new ChangePW(actionHandler);
		this.mainFrame = new MainFrame(actionHandler,windowHandler);
		this.cLogin = new CLogin();
		try {
			this.cBasket = new CBasket();
			this.cSincheong = new CSincheong();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.basketFrame = new BasketFrame(actionHandler);
	}
	//logincheck method
	public String loginCheck() {
		String id = this.loginFrame.idText.getText();
		char[] pass = this.loginFrame.pwText.getPassword();
		String pw = new String(pass);
			boolean isLogin = false;
			try {
				isLogin = cLogin.authenticate(id, pw);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} 
			if(isLogin) {
				JOptionPane.showMessageDialog(null, "�α��ο� �����ϼ̽��ϴ�.");
				loginFrame.dispose();
			}else {
				JOptionPane.showMessageDialog(null, "��ġ�ϴ� ������ �����ϴ�.");
				return null;
			}
		return id;
	}
	//���¸� ������ ��, Vector�� ��Ƽ� ������� basketFrame ����, ������� "����"����
	private void show() { //isselected : LectureTable�� select�Լ����� ���� selectedrow�� ���� ����, LectureTable���� ������ ���µ��� ��� ����.
		isselected = mainFrame.showFrame();
		if(isselected !=null) {
			basketFrame.setVisible(true);
			basketFrame.addItems(isselected); // Vector�� ��� �� ������

		}
	}
	//login button
	private void loginOk () {
		ID = loginCheck();
		if(ID != null) {
			loginFrame.dispose();
			try {
				this.cLogin.createFile(ID);
				this.cLogin.createFileS(ID);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mainFrame.setVisible(true);
			basketFrame.initiate(ID);
			mainFrame.setID(ID);
		}else {
			loginFrame.dispose();
		}
	}
	//ȸ������
	public void register() {
		registerFrame.setVisible(true);
	}
	//basketTable���� ���� �� sincheongTable���� ������
	private void select() {// basketSelected : basketTable���� ������ ���� ���� ����.
		basketSelected = basketFrame.showFrame(); 
		if(basketSelected != null) {
			basketFrame.givebasket(basketSelected);
		}
	}
	//logout
	private void logout() {
		try {
			this.cBasket.add(this.basketFrame.saveBasket(), ID);
			this.cSincheong.add(this.basketFrame.saveSincheong(), ID);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mainFrame.dispose();
		basketFrame.dispose();
		this.mainFrame = new MainFrame(actionHandler, windowHandler);
		this.basketFrame = new BasketFrame(actionHandler);
		loginFrame = new LoginFrame(actionHandler,keyHandler);
	}
	//å���濡�� ����
	public void cancel() {
		basketFrame.cancel();
	}
	//��û�������� ����
	public void cancelSincheong() {
		basketFrame.cancelSincheong();
	}
	//��û�������� å�������� �ǵ�����
	public void backToBasket() {
		basketFrame.addItems(basketFrame.backToBasket()); //basketFrame.backToBasket() : ���� �޴� selectedrow�� ��� �ִ�.
	}
	//ȸ������ ��� ��ư
	public void cancelregister() {
		JOptionPane.showMessageDialog(null, "ȸ�������� ����ϼ̽��ϴ�.");
		this.registerFrame.dispose();
	}
	//ȸ������ ���� ����
	public void store() {
		this.registerFrame.store();
	}
	//�α���â ����
	public void showLoginFrame() {
		this.registerFrame.dispose();
		this.loginFrame.setVisible(true);
	}
	//Id �ߺ�Ȯ��
	public void overlap() {
		String getId = this.registerFrame.overlap();
		if(getId !=null) {
			JOptionPane.showMessageDialog(null, "�̹� �����ϴ� ID�Դϴ�.");
		}else {
			JOptionPane.showMessageDialog(null, "��밡���� ID�Դϴ�.");
		}
	}
	//���̵� �� ��й�ȣ ã�� â ����
	public void findidpw() {
		this.findFrame.setVisible(true);
	}
	//���̵� ã��
	public void findID() {
		String findID = this.findFrame.findID();
		if(findID != null) {
			JOptionPane.showMessageDialog(findFrame, "���̵� :" + findID);
		}else {
			JOptionPane.showMessageDialog(findFrame, "���Ե� ������ �������� �ʽ��ϴ�.");
		}
	}
	//��й�ȣ ã��
	public void findPW() {
		String findID = this.findFrame.findID();
		String findPW = this.findFrame.findPW(findID);
		if(findPW != null) {
			JOptionPane.showMessageDialog(findFrame, "��й�ȣ : " + findPW);
		}else {
			JOptionPane.showMessageDialog(findFrame, "���Ե� ������ �������� �ʽ��ϴ�.");
		}
	}
	//���̵�/��й�ȣ ã�� â �ݱ�
	public void cancelfind() {
		JOptionPane.showMessageDialog(null, "���̵�/��й�ȣ ã�⸦ ����ϼ̽��ϴ�.");
		this.findFrame.dispose();
	}
	//���̵�/��й�ȣ ã�⿡�� �α��� ȭ������ �̵�
	public void showlogin() {
		JOptionPane.showMessageDialog(null, "�α���ȭ������ �̵��մϴ�.");
		this.findFrame.dispose();
	}
	//������������
	public void userinfo() {
		String[] userinfo = this.mainFrame.userInfo();
		JOptionPane.showMessageDialog(mainFrame, userinfo);
	}
	public void showBasket() {
		this.basketFrame.setVisible(true);
	}
	//���ΰ�ħ ��ư
	public void refresh() {
		this.registerFrame.refresh();
		this.findFrame.refresh();
	}
	//�۾� ũ�� ����_ȸ������â������
	public void combo() {
		this.registerFrame.combo();
	}
	//basketFrame���� �������� Ȯ��â ����
	public void user() {
		String[] userinfo = this.mainFrame.userInfo();
		JOptionPane.showMessageDialog(basketFrame, userinfo);		
	}
	//�۾� ũ�� ����
	public void fontSize() {
		this.basketFrame.fontSize();
	}
	//�������б� Ȩ������ ����
	public void gotourl() {
		Runtime runtime = Runtime.getRuntime();
		try {
			runtime.exec("explorer.exe http://www.mju.ac.kr/");
		} catch (IOException e) {e.printStackTrace();}
	}
	//�۾� ���󺯰�
	public void colorChange() {
		this.registerFrame.colorChange();
	}
	//��й�ȣ ����
	public void changePW() {
		this.changePW.setVisible(true);
		
	}
	public void newPW() {
		selectedcomIndex = this.registerFrame.selectedcom;
		this.changePW.newPW(ID,selectedcomIndex);
	}
	public static void main(String[] args) {
		// memory allocation & invoke constructor
		Main main = new Main();
	}
	//actionHandler
	class ActionHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getActionCommand().equals("login")) {
				loginOk();
			}else if(e.getActionCommand().equals("register")) {
				register();
			}else if(e.getActionCommand().equals("lectureSelect")) {
				show();
			}else if(e.getActionCommand().equals("select")) {
				select();
			}else if(e.getActionCommand().equals("logout")) {
				logout();
			}else if(e.getActionCommand().equals("deleteBasket")) {
				cancel();
			}else if(e.getActionCommand().equals("cancelSincheong")) {
				cancelSincheong();
			}else if(e.getActionCommand().equals("backToBasket")) {
				backToBasket();
			}else if(e.getActionCommand().equals("signup")) {
				showLoginFrame();
			}else if(e.getActionCommand().equals("store")) {
				store();
			}else if(e.getActionCommand().equals("cancelRegister")) {
				cancelregister();
			}else if(e.getActionCommand().equals("idoverlap")) {
				overlap();
			}else if(e.getActionCommand().equals("find")) {
				findidpw();
			}else if(e.getActionCommand().equals("findID")) {
				findID();
			}else if(e.getActionCommand().equals("findPW")) {
				findPW();
			}else if(e.getActionCommand().equals("cancelfind")) {
				cancelfind();
			}else if(e.getActionCommand().equals("find&login")) {
				showlogin();
			}else if(e.getActionCommand().equals("userinfo")) {
				userinfo();
			}else if(e.getActionCommand().equals("showBasket")) {
				showBasket();
			}else if(e.getActionCommand().equals("refresh")) {
				refresh();
			}else if(e.getActionCommand().equals("combo")) {
				combo();
			}else if(e.getActionCommand().equals("user")) {
				user();
			}else if(e.getActionCommand().equals("fontSize")) {
				fontSize();
			}else if(e.getActionCommand().equals("gotourl")) {
				gotourl();
			}else if(e.getActionCommand().equals("Color")) {
				colorChange();
			}else if(e.getActionCommand().equals("changePW")) {
				changePW();
			}else if(e.getActionCommand().equals("newPW")) {
				newPW();
			}
		}
	}	
	//����Ű ���
	public class KeyHandler implements KeyListener{
		@Override
		public void keyTyped(KeyEvent e) {
		}
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				loginOk();
			}
		}
		@Override
		public void keyReleased(KeyEvent e) {
		}
	}
	class WindowHandler implements WindowListener{
		@Override
		public void windowOpened(WindowEvent e) {
		}
		@Override
		public void windowClosing(WindowEvent e) {
			logout();
		}
		@Override
		public void windowClosed(WindowEvent e) {
		}
		@Override
		public void windowIconified(WindowEvent e) {
		}
		@Override
		public void windowDeiconified(WindowEvent e) {
		}
		@Override
		public void windowActivated(WindowEvent e) {
		}
		@Override
		public void windowDeactivated(WindowEvent e) {
		}
	}

}
