package de.anselm.main.files;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import de.anselm.main.options.SetOptions;

public class FileOpener {
	public ArrayList<String> fileOpener (String fileName){

		//create Array for file content
		ArrayList<String> records = new ArrayList<String>();
		System.out.println("Opening " + fileName + ".");
		
		//read in file
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			String line;
			while ((line = reader.readLine()) != null) {
				records.add(line);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("\nFile " + fileName +" not found.\n");
			new SetOptions().callHelp();
			System.exit(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.format("Exeption occured trying to read '%s'.", fileName);
			return null;
			

		}
		return records;
	}
}
