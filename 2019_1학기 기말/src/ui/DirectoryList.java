package ui;

import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.util.Vector;

//import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;

import controller.CDirectory;
import entity.EDirectory;

@SuppressWarnings("serial")
public class DirectoryList extends JList<String> {

	private CDirectory cDirectory;
	Vector<EDirectory> directories;

	public DirectoryList(ListSelectionListener listSelectionHandler) {

		try {
			this.cDirectory = new CDirectory();
			this.directories = this.cDirectory.getItems("data/root");

			Vector<String> listData = new Vector<String>();
			for (EDirectory eDirectory : directories) {
				listData.add(eDirectory.getName());
			}
			this.setPreferredSize(new Dimension(100,200));
			this.setListData(listData);
			this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			this.addListSelectionListener(listSelectionHandler);

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

	}

	public String getSelectedFileName() {
		int selectedIndex = this.getSelectedIndex();
		if(selectedIndex == -1) {
			selectedIndex = 0;
		}
		return this.directories.get(selectedIndex).getHyperLink();
	}

	public String refresh(String fileName) throws FileNotFoundException {
		this.directories = this.cDirectory.getItems("data/" + fileName);

		Vector<String> listData = new Vector<String>();
		for (EDirectory eDirectory : directories) {
			listData.add(eDirectory.getName());
		}
		this.setListData(listData);
//		this.setSelectedIndex(0); // 선택이 되지 않은 경우 --> -1이 표기/-1을 막으면 해결.
		this.repaint(); // 그림을 다시 그리는 명령어
		return this.directories.get(0).getHyperLink();
	}

}