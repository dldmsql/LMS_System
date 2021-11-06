package controller;
import java.io.FileNotFoundException;
import java.io.IOException;

import dao.ELogin;


public class CLogin {
	private ELogin eLogin;
	
	public CLogin() {
		this.eLogin =  new ELogin();
	}

	public boolean authenticate(String userId, String password) throws FileNotFoundException {
		boolean validUser = eLogin.authenticate(userId, password);
		return validUser;
		
	}

	public void createFile(String iD) throws IOException {
		this.eLogin.createFile(iD);
	}

	public void createFileS(String iD) throws IOException {
		this.eLogin.createFileS(iD);
	}

}
