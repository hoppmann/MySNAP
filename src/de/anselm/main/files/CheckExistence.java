package de.anselm.main.files;

import java.io.File;

public class CheckExistence {
	
	public Boolean check(String path) {
		
		boolean found = new File(path).isFile();
		
		return found;
	}

}
