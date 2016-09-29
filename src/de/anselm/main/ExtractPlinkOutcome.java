package de.anselm.main;

import java.util.ArrayList;

import de.anselm.main.files.FileOpener;
import de.anselm.main.options.GetOptions;

public class ExtractPlinkOutcome {
	
	//Declare variables
	GetOptions options;
	String currentSNP;
	ArrayList<String> fileContent = new ArrayList<String>();

	//constructor
	public ExtractPlinkOutcome(GetOptions options, String currentSNP) {
		super();
		this.currentSNP = currentSNP;
		this.options = options;
		
		extract();
	}

	public void extract() {

		//Open plink file
		FileOpener file = new FileOpener();
		fileContent = file.fileOpener(options.getTemp() + "/plink_" + currentSNP + ".ld");
		
	}

	//getter for filecontent
	public ArrayList<String> getFileContent() {
		return fileContent;
	}
	
}
