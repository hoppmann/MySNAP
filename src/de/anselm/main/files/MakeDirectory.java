package de.anselm.main.files;

import java.io.File;
import java.io.IOException;

import de.anselm.main.options.GetOptions;

public class MakeDirectory {

//	private GetOptions options;
	private File path = null;

	public MakeDirectory(GetOptions options) {
		super();
//		this.options = options;
	}




	public void create(String dir) {
		// create directory 
		//get current path
		String current = null;
		try {
			current = new java.io.File( "." ).getCanonicalPath();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//create direcotry
		

		//save path for later deletion
		try {
			path = new File(current + File.separator + dir);
			path.mkdir();
		} catch (Exception e) {
			System.out.println("\nFAILURE: Coudn't create directory.");
			System.exit(1);
		}
	}

	//getter 
	public File getPath() {
		return path;
	}



}
