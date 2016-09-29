package de.anselm.main.files;

import java.util.ArrayList;

public class ExtractSNPFile {
	
	private String chr;
	private String snp;
	ArrayList<String> snpList = new ArrayList<>();
	


	public void extract(String line) {
		//split string
		String[] splitted = line.split("\t") ;
		
		//divide splitted String into variables
		snp = splitted[0];
		chr = splitted[1];
		snpList.add(splitted[0]);
	}


	// Getter
	public String getChr() {
		return chr;
	}
	public String getSnp() {
		return snp;
	}
	public String getSnpList(int index) {
		return snpList.get(index);
	}
	
	
	

}
