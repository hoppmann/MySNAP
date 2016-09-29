package de.anselm.main;

import java.io.File;
import java.util.ArrayList;

import org.apache.commons.cli.ParseException;

import de.anselm.main.files.ConfigFile;
import de.anselm.main.files.ExtractSNPFile;
import de.anselm.main.files.FileOpener;
import de.anselm.main.files.MakeDirectory;
import de.anselm.main.files.ProcessPlink;
import de.anselm.main.options.GetOptions;
import de.anselm.main.options.SetOptions;


public class Main {


	public static void main(String[] args) throws ParseException {


		// get options
		GetOptions options = new GetOptions(args);

		//check if snpfile is given
		if (options.getPath() == null){
			System.out.println("\nFAILURE: Option SNPFile mandatory.\n");
			new SetOptions().callHelp();
			System.exit(1);
		}

		//////// create directories ////////
		MakeDirectory dir = new MakeDirectory(options);
		//tmp
		dir.create(options.getTemp());
		File path = dir.getPath();

		//directory
		dir.create(options.getResult());

		//////// read in config file ///////
		ConfigFile config = new ConfigFile(options);

		//////// Open file to read in SNPs ///////
		ArrayList<String> records = new ArrayList<String>();
		FileOpener FO = new FileOpener();
		records = FO.fileOpener(options.getPath());


		//////// PLINK ////////
		//foreach SNP do Plink
		ExtractSNPFile snpFile = new ExtractSNPFile();
		int numberLines = 0;
		for (String snp : records){

			//skip lines marked as header
			if (snp.matches("^#.*")){
				continue;
			}
			numberLines++;
			snpFile.extract(snp);
			//start Plink
			if (!options.getSkip()){
				PlinkExecuter plink = new PlinkExecuter(snpFile, config, options);
				plink.plink();
			}
		}


		//////// Process Plink outcome ////////
		for (int i =0 ; i < numberLines; i++){
			String currentSNP = snpFile.getSnpList(i);
			new ProcessPlink(options, currentSNP); 
		}

		// clean up
		if (!options.getKeep()){
			System.out.println("\nRemoving tmp folder.\n" + path.toString());
			String[] entries = path.list();
			for (String s : entries){
				new File(path.getPath(), s).delete();
			}
			path.delete();
		}
		System.out.println("\nProgram finished.\n");


	}



}
