package de.anselm.main.files;

import java.io.IOException;
import java.util.Properties;
import de.anselm.main.options.GetOptions;

public class ConfigFile {

	//define variables
	private String pathToPlink;
	private String pathToBed;
	private String prefix;
	private GetOptions options;


	//Construktor to load ConfigFile
	public ConfigFile(GetOptions options) {
		super();
		this.options = options;
		loadConfig();
	}

	//get directory of config file
	private void loadConfig() {
		
		
		//read in config
		Properties prop = new Properties();
		
		try {
			prop.load(getClass().getClassLoader().getResourceAsStream("MySNAP.config"));
		} catch (IOException e1) {
			System.out.println("\n FAILURE: Couldn't find config file.\n");
			System.exit(1);
		}

		//load config entries
		pathToPlink = prop.getProperty("pathPlink");
		pathToBed = prop.getProperty("pathBed");
		
		//set prefix corresponding to option "population"
		prefix = prop.getProperty("prefixBed");
		prefix = prefix.replace("POP", options.getPopulation());
	}

	//getter for config variables

	public String getPathToPlink() {
		return pathToPlink;
	}

	public String getPathToBed() {
		return pathToBed;
	}

	public String getPrefix() {
		return prefix;
	}
}
