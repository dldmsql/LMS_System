
package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

import dao.DAOBasket;
import entity.EBasket;
import entity.ELecture;

public class CBasket {

	private DAOBasket daoBasket;
	
	public CBasket() throws IOException {
		this.daoBasket = new DAOBasket();
	}
	
	public Vector<ELecture> getItems(String id) throws FileNotFoundException{
		return this.daoBasket.getItems(id);
	}
	
	public void add(Vector<ELecture> isselected, String userID) throws IOException {
		this.daoBasket.WriteToFile(isselected, userID);
	}
}
