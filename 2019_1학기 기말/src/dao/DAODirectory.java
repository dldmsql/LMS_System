package dao;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

import entity.EDirectory;

public class DAODirectory {

	
	public Vector<EDirectory> getItems(String fileName) throws FileNotFoundException {
		Vector<EDirectory> items = new Vector<EDirectory>();
		File file = new File(fileName);
		Scanner scanner = new Scanner(file);
		
		while(scanner.hasNext()) {
			EDirectory vODirectory = new EDirectory();
			vODirectory.read(scanner);
			items.add(vODirectory);
		}
		return items;
	}
}
