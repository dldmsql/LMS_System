package dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

import entity.EBasket;
import entity.ELecture;
import entity.ESincheong;

public class DAOSincheong {
	public Vector<ELecture> getItems(String id) throws FileNotFoundException{
		Vector<ELecture> items = new Vector<ELecture>();
		File file = new File("student/" + id + "sincheong");
		Scanner scanner = new Scanner(file);

		while (scanner.hasNext()) {
			ELecture eLecture = new ELecture();
			eLecture.read(scanner);
			items.add(eLecture);
		}
		return items;
	}

	public void WiteToFile(Vector<ELecture> basketSelected, String userID) throws IOException {
		String[] stu = { "number","name","professorName","credit","time"};
		File file = new File("student/" + userID + "sincheong");
		FileWriter fw = new FileWriter(file,false);
		for(int j =0; j< basketSelected.size(); j++) {
			stu[0] = basketSelected.get(j).getNumber();
			stu[1] = basketSelected.get(j).getName();
			stu[2] = basketSelected.get(j).getProfessorName();
			stu[3] = basketSelected.get(j).getCredit();
			stu[4] = basketSelected.get(j).getTime();

			int i = 0;
			while(i <5) {
				fw.write(stu[i] + " ");
				i++;
			}
			fw.write("\n");
		}
		fw.close();
	}
}

