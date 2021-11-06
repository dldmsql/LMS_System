package controller;
import java.io.FileNotFoundException;
import java.util.Vector;

import dao.DAOLecture;
import entity.ELecture;

public class CLecture {

	private DAOLecture daoLecture;
	
	public CLecture() {
		this.daoLecture = new DAOLecture();
	}

	public Vector<ELecture> getItems(String fileName) throws FileNotFoundException {
		return this.daoLecture.getItems(fileName);
	}
}
