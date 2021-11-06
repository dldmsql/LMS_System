package dao;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class ELogin {
	private String rUserId;
	private String rPassword;

	private void read(Scanner scanner) {
		this.rUserId = scanner.next();
		this.rPassword = scanner.next();
	}


	public boolean authenticate(String userId, String password) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("student/" + userId + "login"));	
		while(scanner.hasNext()) {
			this.read(scanner);
			if(this.rUserId.equals(userId) && this.rPassword.equals(password)) {
				scanner.close();
				return true;
			}
		}
		scanner.close();
//		InvalidUserException invalidUserException = new InvalidUserException();
//		throw invalidUserException;
		return false;
	}


	public void createFile(String iD) throws IOException {
		File file = new File("student/" + iD + "basket");
		if(!(file.exists())) {
			file.createNewFile();
		}
	}
//	public class InvalidUserException extends Exception{
//		private static final long serialVersionUID = 1L;
//		public InvalidUserException() {
//			super("잘 못 된 사용자 입 니 다.");
//		}
//	}


	public void createFileS(String iD) throws IOException {
		File file = new File("student/"+ iD + "sincheong");
		if(!(file.exists())) {
			file.createNewFile();
		}
	}

}