package de.anselm.main.files;

import java.util.ArrayList;

import de.anselm.main.AddDistance;
import de.anselm.main.ExtractPlinkOutcome;
import de.anselm.main.options.GetOptions;

public class ProcessPlink {

	GetOptions options;
	ExtractSNPFile snpFile;
	String currentSNP;
	//arraylist to read in file
	ArrayList<String> file = new ArrayList<>();
	//arraylist to get distance added file
	ArrayList<String> addedDistance = new ArrayList<String>();


	//Constructor
	public ProcessPlink(GetOptions options, String currentSNP) {
		super();
		this.options = options;
		this.currentSNP = currentSNP;

		process();
	}

	private void process() {

		System.out.println("\n######## " + currentSNP + " ########");
		ExtractPlinkOutcome ldinfo = new ExtractPlinkOutcome(options, currentSNP);
		file = ldinfo.getFileContent();

		//Screen output
		System.out.println("Rearranging " + currentSNP);

		//add Distance and rearrange file
		AddDistance rearrangeFile = new AddDistance();

		//Screen output
		System.out.println("Writing in file");

		//write header
		WriteOut out = new WriteOut(options, currentSNP);
		out.writeHeader();

		//loop over each snp
		for (String linePlinkOut : file){
			addedDistance = rearrangeFile.rearrange(linePlinkOut);

			//if line not header write entries in out-file
			if (addedDistance.size() != 0){
				out.writeData(addedDistance);
			}
		}

		//close out file
		out.close();




	}





}
