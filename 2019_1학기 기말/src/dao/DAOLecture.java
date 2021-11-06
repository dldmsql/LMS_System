package dao;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

import entity.ELecture;

public class DAOLecture {

	
	public Vector<ELecture> getItems(String fileName) throws FileNotFoundException {
		Vector<ELecture> items = new Vector<ELecture>();
		File file = new File("data/" + fileName);
		Scanner scanner = new Scanner(file);
		
		while(scanner.hasNext()) {
			ELecture eLecture = new ELecture();
			eLecture.read(scanner);
			items.add(eLecture);
		}
		scanner.close();
		return items;
	}
}
