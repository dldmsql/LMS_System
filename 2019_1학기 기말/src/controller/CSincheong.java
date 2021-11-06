package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

import dao.DAOSincheong;
import entity.EBasket;
import entity.ELecture;
import entity.ESincheong;

public class CSincheong {
private DAOSincheong daoSincheong;
	
	public CSincheong() throws IOException {
		this.daoSincheong = new DAOSincheong();
	}
	
	public Vector<ELecture> getItems(String id) throws FileNotFoundException{
		return this.daoSincheong.getItems(id);
	}
	
	public void add(Vector<ELecture> basketSelected, String userID) throws IOException {
		this.daoSincheong.WiteToFile(basketSelected, userID);
	}
}
