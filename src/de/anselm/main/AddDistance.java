package de.anselm.main;

import java.util.ArrayList;



public class AddDistance {

	ExtractPlinkOutcome ldinfo;
	ArrayList<String> addedDistance;


	public ArrayList<String> rearrange(String line) {

		// reset Arraylist and create new for rearranging
		addedDistance = new ArrayList<>();

		//replace several space with one tab and remove first space
		line = line.replaceAll("\\s+", "\t");
		line = line.replaceFirst("^\t", "");

		//split header and add "Distance"
		if (line.matches("^CHR_A.*")){

		} else {

			//split line
			String[] splittedNumberLine = line.split("\t");

			//write splitted line in retrun array 
			for (String temp : splittedNumberLine){
				addedDistance.add(temp);
			}

			//add Distance
			int distance = Math.abs(Integer.valueOf(splittedNumberLine[1]) - Integer.valueOf(splittedNumberLine[4]));
			addedDistance.add(String.valueOf(distance));

		}
		
		return addedDistance;
	}
}
