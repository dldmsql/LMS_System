package ui;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controller.CSincheong;
import entity.ELecture;

public class SincheongTable extends JTable {

	private static final long serialVersionUID = 1L;

	private DefaultTableModel model;
	private Font font;
	private Vector<ELecture> lectures;
	private int a;
	private CSincheong cSincheong;
	private Vector<ELecture> storeddata;

	public SincheongTable() {
		this.lectures = new Vector<ELecture>();
		this.storeddata = new Vector<ELecture>();
		//font
		font = new Font("고딕", Font.PLAIN, 10);

		//header
		String header[] = {"강좌번호", "강좌이름", "교수명", "학점", "강좌시간"};

		this.model = new DefaultTableModel(header,0) {public boolean isCellEditable(int rowIndex, int mColIndex) {
			return false;
		}};
		this.setModel(model);
		this.setFont(font);
		this.getTableHeader().setBackground(new Color(0X55CCCCFF));
		this.getTableHeader().setReorderingAllowed(false);
		this.getTableHeader().setResizingAllowed(false);
		this.getTableHeader().setFont(new Font("고딕", Font.BOLD, 15));
		this.setAutoCreateRowSorter(true); // 자동 행 정렬 기능
		CenterAlignedTableCellRenderer renderer = new CenterAlignedTableCellRenderer();
		this.setDefaultRenderer(this.getColumnClass(0), renderer);
	}
	private class CenterAlignedTableCellRenderer extends DefaultTableCellRenderer{
		private static final long serialVersionUID = 1L;
		public CenterAlignedTableCellRenderer(){
			this.setHorizontalAlignment(SwingConstants.CENTER);
		}
	}
	//신청 내역 row 받아오기
	public void addItems (Vector<ELecture> lectures) {
		Vector<String> rowdata;
		for(ELecture eLecture : lectures) {
			if(a+ Integer.parseInt(eLecture.getCredit())<=18) {
				rowdata = new Vector<String>();
				if(!(bsOverlap(eLecture.getNumber()))) {
					rowdata.add(eLecture.getNumber());
					rowdata.add(eLecture.getName());
					rowdata.add(eLecture.getProfessorName());
					rowdata.add(eLecture.getCredit());
					a+= Integer.parseInt(eLecture.getCredit());
					rowdata.add(eLecture.getTime());
					this.model.addRow(rowdata);
					this.lectures.add(eLecture);
				}else {
					JOptionPane.showMessageDialog(null, eLecture.getName() + "은(는) 이미 선택된 강좌입니다.");
				}
			}else {JOptionPane.showMessageDialog(null, "최대 18학점을 초과하셨습니다.");
			} 
		}this.updateUI();
	}
	//중복 체크 함수 
	private boolean bsOverlap(String number) {
		for(ELecture eLecture : lectures) {
			if(eLecture.getNumber().equals(number)) {
				return true;
			}
		}
		return false;
	}
	//신청내역에서 선택한 것을 삭제
	public void cancel() {
		for(int i = this.getRowCount()-1; i >= 0; i--) {

			if(this.isRowSelected(i)) {

				this.lectures.remove(i);
				model.removeRow(i); //선택된 index 삭제
			}
		}
		this.updateUI();
	}
	//신청내역에서 책가방으로 되돌릴 때 선택된 것을 담고 삭제
	public Vector<ELecture> backToBasket() {

		Vector<ELecture> selectedrow = new Vector<ELecture>();

		for(int i = this.getRowCount()-1; i >= 0; i--) {

			if(this.isRowSelected(i)) {

				selectedrow.add(this.lectures.get(i));
				model.removeRow(i);

				lectures.remove(i); //선택된 index 삭제
			}
		}
		this.updateUI();
		return selectedrow;	
	}
	//파일에서 저장된 신청내역 불러오기
	public void initiate(String id) {
		try {
			this.cSincheong = new CSincheong();
			storeddata = this.cSincheong.getItems(id);
			this.addItems(storeddata);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public Vector<ELecture> saveSincheong(){
		return lectures;
	}
	public void fontSize(int index) {
		if(index == 0) {
			Font font1 = new Font("고딕", Font.PLAIN, 10);
			this.setFont(font1);
			this.getTableHeader().setFont(font1);
		}else if(index ==1) {
			Font font2 = new Font("고딕", Font.PLAIN, 13);
			this.setFont(font2);
			this.getTableHeader().setFont(font2);
		}else if(index ==2) {
			Font font3 = new Font("고딕", Font.PLAIN, 15);
			this.setFont(font3);
			this.getTableHeader().setFont(font3);
		}else if(index ==3) {
			Font font4 = new Font("고딕", Font.PLAIN, 17);
			this.setFont(font4);
			this.getTableHeader().setFont(font4);
		}
	}
}
