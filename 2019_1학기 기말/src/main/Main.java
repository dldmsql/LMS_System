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
				JOptionPane.showMessageDialog(null, "로그인에 성공하셨습니다.");
				loginFrame.dispose();
			}else {
				JOptionPane.showMessageDialog(null, "일치하는 정보가 없습니다.");
				return null;
			}
		return id;
	}
	//강좌를 선택한 후, Vector에 담아서 담겼으면 basketFrame 띄우고, 비었으면 "문구"띄우기
	private void show() { //isselected : LectureTable에 select함수에서 담은 selectedrow와 같은 벡터, LectureTable에서 선택한 강좌들이 담긴 벡터.
		isselected = mainFrame.showFrame();
		if(isselected !=null) {
			basketFrame.setVisible(true);
			basketFrame.addItems(isselected); // Vector에 담긴 거 보내기

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
	//회원가입
	public void register() {
		registerFrame.setVisible(true);
	}
	//basketTable에서 선택 후 sincheongTable에게 보내기
	private void select() {// basketSelected : basketTable에서 선택한 강좌 담은 벡터.
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
	//책가방에서 삭제
	public void cancel() {
		basketFrame.cancel();
	}
	//신청내역에서 삭제
	public void cancelSincheong() {
		basketFrame.cancelSincheong();
	}
	//신청내역에서 책가방으로 되돌리기
	public void backToBasket() {
		basketFrame.addItems(basketFrame.backToBasket()); //basketFrame.backToBasket() : 리턴 받는 selectedrow가 담겨 있다.
	}
	//회원가입 취소 버튼
	public void cancelregister() {
		JOptionPane.showMessageDialog(null, "회원가입을 취소하셨습니다.");
		this.registerFrame.dispose();
	}
	//회원가입 내용 저장
	public void store() {
		this.registerFrame.store();
	}
	//로그인창 띄우기
	public void showLoginFrame() {
		this.registerFrame.dispose();
		this.loginFrame.setVisible(true);
	}
	//Id 중복확인
	public void overlap() {
		String getId = this.registerFrame.overlap();
		if(getId !=null) {
			JOptionPane.showMessageDialog(null, "이미 존재하는 ID입니다.");
		}else {
			JOptionPane.showMessageDialog(null, "사용가능한 ID입니다.");
		}
	}
	//아이디 및 비밀번호 찾는 창 띄우기
	public void findidpw() {
		this.findFrame.setVisible(true);
	}
	//아이디 찾기
	public void findID() {
		String findID = this.findFrame.findID();
		if(findID != null) {
			JOptionPane.showMessageDialog(findFrame, "아이디 :" + findID);
		}else {
			JOptionPane.showMessageDialog(findFrame, "가입된 정보가 존재하지 않습니다.");
		}
	}
	//비밀번호 찾기
	public void findPW() {
		String findID = this.findFrame.findID();
		String findPW = this.findFrame.findPW(findID);
		if(findPW != null) {
			JOptionPane.showMessageDialog(findFrame, "비밀번호 : " + findPW);
		}else {
			JOptionPane.showMessageDialog(findFrame, "가입된 정보가 존재하지 않습니다.");
		}
	}
	//아이디/비밀번호 찾기 창 닫기
	public void cancelfind() {
		JOptionPane.showMessageDialog(null, "아이디/비밀번호 찾기를 취소하셨습니다.");
		this.findFrame.dispose();
	}
	//아이디/비밀번호 찾기에서 로그인 화면으로 이동
	public void showlogin() {
		JOptionPane.showMessageDialog(null, "로그인화면으로 이동합니다.");
		this.findFrame.dispose();
	}
	//개인정보열람
	public void userinfo() {
		String[] userinfo = this.mainFrame.userInfo();
		JOptionPane.showMessageDialog(mainFrame, userinfo);
	}
	public void showBasket() {
		this.basketFrame.setVisible(true);
	}
	//새로고침 버튼
	public void refresh() {
		this.registerFrame.refresh();
		this.findFrame.refresh();
	}
	//글씨 크기 조절_회원가입창에서만
	public void combo() {
		this.registerFrame.combo();
	}
	//basketFrame에서 개인정보 확인창 띄우기
	public void user() {
		String[] userinfo = this.mainFrame.userInfo();
		JOptionPane.showMessageDialog(basketFrame, userinfo);		
	}
	//글씨 크기 조절
	public void fontSize() {
		this.basketFrame.fontSize();
	}
	//명지대학교 홈페이지 연결
	public void gotourl() {
		Runtime runtime = Runtime.getRuntime();
		try {
			runtime.exec("explorer.exe http://www.mju.ac.kr/");
		} catch (IOException e) {e.printStackTrace();}
	}
	//글씨 색상변경
	public void colorChange() {
		this.registerFrame.colorChange();
	}
	//비밀번호 변경
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
	//엔터키 기능
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
