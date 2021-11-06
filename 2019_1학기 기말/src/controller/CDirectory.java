package controller;
import java.io.FileNotFoundException;
import java.util.Vector;

import dao.DAODirectory;
import entity.EDirectory;

public class CDirectory {

	private DAODirectory eDirectory;
	
	public CDirectory() {
		this.eDirectory = new DAODirectory();
	}

	public Vector<EDirectory> getItems(String fileName) throws FileNotFoundException {
		return this.eDirectory.getItems(fileName);
	}
}
