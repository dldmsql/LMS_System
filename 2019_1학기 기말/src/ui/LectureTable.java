package ui;

import java.awt.Color;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controller.CLecture;
import entity.ELecture;

@SuppressWarnings("serial")
public class LectureTable extends JTable {
	
	private Vector<ELecture> lectures; 
	private DefaultTableModel model;
	private CLecture cLecture;
	private Font font;
	private Color color;
	
	public LectureTable() {
		// font
		font = new Font("���", Font.ITALIC,10);
		
		//Color
		color = new Color(0XCCCCFF);

		// header
		String header[] = {"���¹�ȣ","�����̸�", "������ ", "����", "���½ð�"};
		
		this.model = new DefaultTableModel(header,0) {public boolean isCellEditable(int rowIndex, int mColIndex) {
			return false;
		}};
		this.setModel(model);
		this.setFont(font);
		this.getTableHeader().setReorderingAllowed(false);
		this.getTableHeader().setResizingAllowed(false);
		this.getTableHeader().setBackground(color);
		this.setBackground(Color.WHITE); // �� �� ����
		this.getTableHeader().setFont(new Font("���", Font.BOLD , 15));
		CenterAlignedTableCellRenderer renderer = new CenterAlignedTableCellRenderer();
		this.setDefaultRenderer(this.getColumnClass(0), renderer);
	}
	private class CenterAlignedTableCellRenderer extends DefaultTableCellRenderer{
		private static final long serialVersionUID = 1L;
		public CenterAlignedTableCellRenderer(){
			this.setHorizontalAlignment(SwingConstants.CENTER);
		}
	}
	public void refresh(String fileName) {// fileName �޾ƿͼ� add ����
		this.cLecture = new CLecture();
		try {
			this.lectures = this.cLecture.getItems(fileName); //lectures : file���� �����̸� ��� �ִ� ����.
			add(this.lectures);
		} catch (FileNotFoundException e) {e.printStackTrace();}
	}
	
	private void add(Vector<ELecture> lectures) {// lectureTable�� ���� ���� ��� �Լ�
		this.model.setRowCount(0); // �� ���� �־���� �����͸� �����.
		 //rowdata : lecture���� ��� �ִ� ����
		Vector<String> rowdata;
		for(ELecture eLecture : lectures) {
			rowdata = new Vector<String>();
			rowdata.add(eLecture.getNumber());
			rowdata.add(eLecture.getName());
			rowdata.add(eLecture.getProfessorName());
			rowdata.add(eLecture.getCredit());
			rowdata.add(eLecture.getTime());
			this.model.addRow(rowdata);
		}
	}
	
	public Vector<ELecture> select() {//selectedrow : lectureTable���� ������ ���� ���� ����
		Vector<ELecture> selectedrow = new Vector<ELecture>();
		
		for(int i =0; i<this.getRowCount(); i++) {
			
			if(this.isRowSelected(i)) {
				
				selectedrow.add(lectures.get(i));
			}
		}
		return selectedrow;
		}
	}
