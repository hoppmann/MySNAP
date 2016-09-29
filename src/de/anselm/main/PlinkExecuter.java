package de.anselm.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import de.anselm.main.files.CheckExistence;
import de.anselm.main.files.ConfigFile;
import de.anselm.main.files.ExtractSNPFile;
import de.anselm.main.options.GetOptions;

public class PlinkExecuter {

	//define variables
	private ExtractSNPFile params;
	private ConfigFile configFile;
	private GetOptions options;

	//Constructor setting parameters
	public PlinkExecuter(ExtractSNPFile params, ConfigFile config, GetOptions options) {
		super();

		//give out which SNP is calculated
		System.out.println("\nRunning Plink on " + params.getSnp());

		//setting parameters
		this.params = params;
		this.configFile = config;
		this.options = options;

	}

	public void plink() {

		//check if directory is correct
		File file = new File(configFile.getPathToBed() + File.separator + options.getPopulation());
		if (!(file.exists() && file.isDirectory())){
			System.out.println("\nFAILURE: Directory " + configFile.getPathToBed() + File.separator + options.getPopulation() + " not found.\nExecution halted.\n");
			System.exit(1);
		}

		//check if bed/bim/fam file exists
		String[] suffix = {"bed", "bim", "fam"};
		CheckExistence exists = new CheckExistence();
		for (String element : suffix){
			if (!exists.check(configFile.getPathToBed() + File.separator + options.getPopulation() + File.separator + configFile.getPrefix() + params.getChr() + "." + element )){
				System.out.println("\nFAILURE: " + configFile.getPathToBed() + File.separator + options.getPopulation() + File.separator + configFile.getPrefix() + params.getChr() + "." + element+ " not found.\nExecution halted.\n");
				System.exit(1);
			}
		}

		//form plink command
		ArrayList<String> command = new ArrayList<String>();
		command.add(configFile.getPathToPlink());
		command.add("--r2");
		command.add("dprime");
		command.add("--ld-snp");
		command.add(params.getSnp());
		command.add("--ld-window-kb");
		command.add(options.getWindowKB());
		command.add("--ld-window");
		command.add(options.getWindow());
		command.add("--ld-window-r2");
		command.add(options.getWindowR());
		command.add("--out");
		command.add(options.getTemp() + File.separator +"plink_" + params.getSnp());
		command.add("--bfile");
		command.add(configFile.getPathToBed() + File.separator + options.getPopulation() + File.separator + configFile.getPrefix() + params.getChr());

		//start plink process
		ProcessBuilder builder = new ProcessBuilder(command);

		for (String element : command){
			System.out.print(element + " ");
		}
		System.out.println();
		//		System.out.println(command.toString());
		try {
			//execute command
			Process proc = builder.start();

			//check for noScreen option
			//give out plink output
			BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));

			String lineIn = in.readLine();
			while (lineIn != null){
				if (!options.getScreen()){
					System.out.println(lineIn);
				}
				lineIn = in.readLine();
			}
		} catch (IOException e) {
			System.out.println(e.getLocalizedMessage());
			System.exit(1);
		}
	}
}
