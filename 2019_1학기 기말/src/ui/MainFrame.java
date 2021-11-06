package ui;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JFrame;

import entity.ELecture;

@SuppressWarnings({ "serial", "unused" })
public class MainFrame extends JFrame {

	private SelectionPanel selectionPanel;
	private String id;
	public String rUserName;
	public String rUserID;
	public String rUserPW;
	public String rUserMajor;
	public String rUserGrade;
	public MainFrame(ActionListener actionHandler, WindowListener windowHandler) {

		this.setTitle("학사관리 시스템");
		this.setSize(1000, 600);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addWindowListener(windowHandler);

		//imageIcon
		Toolkit toolkit = this.getToolkit();
		Image img = toolkit.createImage("image/mju_icon.jpg");
		this.setIconImage(img);

		this.selectionPanel = new SelectionPanel(actionHandler,id);
		this.selectionPanel.initiate();
		this.add(selectionPanel);
	}
	// lectureTable에서 선택한 값들을 vector에 담아서 전달.
	public Vector<ELecture> showFrame() {
		return this.selectionPanel.showFrame();
	}
	public void setID(String id) {
		this.id = id;
		this.selectionPanel.setID(id);
	}
	//student 파일에서 읽어오기
	private void read (Scanner scanner) {
		rUserName = scanner.next();
		rUserMajor = scanner.next();
		rUserGrade = scanner.next();
		rUserID = scanner.next();
		rUserPW = scanner.next();
	}
	//개인정보열람
	public String[] userInfo() {
		Scanner scanner;
		try {
			scanner = new Scanner(new File("student/" + id + "Info"));
			while(scanner.hasNext()) {
				this.read(scanner);
				if(id.equals(rUserID)) {
					String stu[] = {rUserName, rUserMajor, rUserGrade, rUserID, rUserPW};
					return stu;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}