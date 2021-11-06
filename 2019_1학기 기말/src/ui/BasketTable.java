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

import controller.CBasket;
import entity.ELecture;

public class BasketTable extends JTable {

	private static final long serialVersionUID = 1L;

	private DefaultTableModel model;
	private Font font;
	private Vector<ELecture> lectures;
	private Vector<ELecture> storeddata;
	private CBasket cBasket;

	public BasketTable() {
		this.lectures = new Vector<ELecture>();
		this.storeddata = new Vector<ELecture>();

		//font
		font = new Font("고딕", Font.PLAIN, 13);

		//header
		String header[] = {"강좌번호", "강좌이름", "교수명","학점","강좌시간"};

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
		CenterAlignedTableCellRenderer renderer = new CenterAlignedTableCellRenderer(); //row 값들 가운데 정렬
		this.setDefaultRenderer(this.getColumnClass(0), renderer);
	}
	private class CenterAlignedTableCellRenderer extends DefaultTableCellRenderer{
		private static final long serialVersionUID = 1L;
		public CenterAlignedTableCellRenderer(){
			this.setHorizontalAlignment(SwingConstants.CENTER);
		}
	}
	// lectureTable에서 선택한 항목 basket에 추가하는 함수
	public void addItems(Vector<ELecture> lectures) { //Vector isselected = Vector lecutres

		Vector<String> rowdata;
		for(ELecture eLecture : lectures) {
			rowdata = new Vector<String>();
			
			if(!(lcOverlap(eLecture.getNumber()))) {
				rowdata.add(eLecture.getNumber());
				rowdata.add(eLecture.getName());
				rowdata.add(eLecture.getProfessorName());
				rowdata.add(eLecture.getCredit());
				rowdata.add(eLecture.getTime());
				this.model.addRow(rowdata);
				this.lectures.add(eLecture);
			}else{
				JOptionPane.showMessageDialog(null, eLecture.getName() + "은(는) 이미 선택된 강좌입니다.");
			}
		}
		this.updateUI(); // 업데이트 해주는 메소드
	}
	//중복 체크 함수
	private boolean lcOverlap(String number) {
		for(ELecture eLecture : lectures) {
			if(eLecture.getNumber().equals(number)){
				return true;
			}
		}
		return false;
	}
	//BasketTable에서 과목 선택하는 함수
	public Vector<ELecture> select(){

		Vector<ELecture> selectedrow = new Vector<ELecture>();

		for(int i = this.getRowCount()-1; i >= 0; i--) {

			if(this.isRowSelected(i)) {

				selectedrow.add(this.lectures.get(i));
				this.lectures.remove(i);

				model.removeRow(i); //선택된 index 삭제
			}
		}
		return selectedrow;
	}
	//책가방에서 선택된 것들 삭제
	public void cancel() {

		for(int i = this.getRowCount()-1; i >= 0; i--) {

			if(this.isRowSelected(i)) {

				this.lectures.remove(i);
				model.removeRow(i); //선택된 index 삭제
			}
		}
		this.updateUI();
	}
	//파일에서 저장된 책가방 내역 불러오기
	public void initiate(String id) {
		
		try {
			this.cBasket = new CBasket();
			storeddata = this.cBasket.getItems(id);
			
			this.addItems(storeddata);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public Vector<ELecture> saveBasket(){
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
