package de.anselm.main.files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import de.anselm.main.options.GetOptions;
import de.anselm.main.options.SetOptions;

public class WriteOut {

	//inti variables
	PrintWriter writer = null;
	private GetOptions options;



	//constructor creating file to write in
	public WriteOut(GetOptions options, String currentSNP) {

		//set options object
		this.options = options;

		//create file to write in
		try {
			writer = new PrintWriter(options.getResult() + File.separator + currentSNP + ".txt");
		} catch (FileNotFoundException e) {
			//in case of failure aboard
			System.out.println("\nFAILURE: Can't open file: " + currentSNP + ".txt\n");
			new SetOptions().callHelp();
			System.exit(1);
		}
	}


	//write header to output file
	public void writeHeader() {
		writer.println("LeadSNP\tProxySNP\tChr\tPositionLead\tPositionProxy\tDistance\tr_squared\tD_prime");
		if(!options.getScreen()){
			System.out.println("\n\nLeadSNP\tProxySNP\tChr\tPositionLead\tPositionProxy\tDistance\tr_squared\tD_prime");
		}
	}

	//write out Data
	public void writeData(ArrayList<String> addedDistance) {
		writer.println(addedDistance.get(2) + "\t" + addedDistance.get(5) + "\t" + addedDistance.get(0) + "\t" + addedDistance.get(1) + "\t" + addedDistance.get(4) + "\t" + addedDistance.get(8) + "\t" + addedDistance.get(6)+ "\t" + addedDistance.get(7));
		if (!options.getScreen()){
			System.out.println(addedDistance.get(2) + "\t" + addedDistance.get(5) + "\t" + addedDistance.get(0) + "\t" + addedDistance.get(1) + "\t" + addedDistance.get(4) + "\t" + addedDistance.get(8) + "\t" + addedDistance.get(6)+ "\t" + addedDistance.get(7));
		}
	}

	public void close() {
		writer.close();
	}



}
