package dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

import entity.EBasket;
import entity.ELecture;

public class DAOBasket {


	public Vector<ELecture> getItems(String id) throws FileNotFoundException{
		Vector<ELecture> items = new Vector<ELecture>();
		File file = new File("student/" + id +"basket");
		Scanner scanner = new Scanner(file);

		while (scanner.hasNext()) {
			ELecture eLecture = new ELecture();
			eLecture.read(scanner);
			items.add(eLecture);
		}
		return items;
	}

	public void WriteToFile(Vector<ELecture> isselected, String userID) throws IOException {
		String[] stu = { "number","name","professorName","credit","time"};
		File file = new File("student/" + userID + "basket");

		if(!(file.exists())) {
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file,false);
		for(int j =0; j< isselected.size(); j++) {

			stu[0] = isselected.get(j).getNumber();
			stu[1] = isselected.get(j).getName();
			stu[2] = isselected.get(j).getProfessorName();
			stu[3] = isselected.get(j).getCredit();
			stu[4] = isselected.get(j).getTime();

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
