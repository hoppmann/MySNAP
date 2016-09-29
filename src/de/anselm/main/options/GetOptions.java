package de.anselm.main.options;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;


public class GetOptions {


	//define Variables
	private String[] args;
	private String path;
	private String temp;
	private String windowKB;
	private String window;
	private String windowR;
	private Options options;
	private SetOptions setOptions;
	private boolean keep;
	private boolean skip;
	private String population;
	private String result;
	private boolean noScreen;
	

	//Constructor to init args
	public GetOptions(String[] args) {
		//get arguments
		this.args = args;

		//run set options
		setOptions = new SetOptions();
		this.options = setOptions.getOptions();

		//run getOptions
		getOptions();
	}

	public void getOptions() {

		System.out.println("Getting commandline options.");

		//parse commandline options
		CommandLineParser cmdParser = new PosixParser();
		CommandLine cmd = null;

		try {
			cmd = cmdParser.parse(options, args);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getLocalizedMessage());
			setOptions.callHelp();
			System.exit(1);
		}

		if (cmd.hasOption("help")){
			new SetOptions().callHelp();
			System.exit(1);
		}
		
		//write options in variables and setting defaults
		path = cmd.getOptionValue("SNPFile");
		keep = cmd.hasOption("keep");
		skip = cmd.hasOption("skipPlink");
		noScreen = cmd.hasOption("noScreen");
		
		
		//set resultDir default = result
		result = cmd.getOptionValue("resultDir");
		if (result == null || "".equals(result.trim())){
			result = "result";
		}
		
		//set reference population
		population = cmd.getOptionValue("population");
		if (population == null || "".equals(population.trim())){
			population = "EUR";
		}
		
		
		//temproary directory
		temp = cmd.getOptionValue("temp");
		if (temp == null || "".equals(temp.trim())){
			temp = "tmp";
		}
		
		//kb-window default = 1mio
		windowKB = cmd.getOptionValue("ld-window-kb");
		if (windowKB == null || "".equals(windowKB.trim())){
			windowKB = "1000000";
		}
		
		//SNP window default = 1000000
		window = cmd. getOptionValue("ld-window");
		if (window == null || "".equals(window.trim())){
			window = "1000000";
		}
		
		//window-r2
		windowR = cmd.getOptionValue("ld-window-r2");
		if (windowR == null || "".equals(windowR.trim())){
			windowR = "0.8";
		}
	}

	
	//getter for options
	public String getPath() {
		return path;
	}

	public String getTemp() {
		return temp;
	}

	public String getWindowKB() {
		return windowKB;
	}

	public String getWindow() {
		return window;
	}

	public String getWindowR() {
		return windowR;
	}
	
	public boolean getKeep() {
		return keep;
	}
	public boolean getSkip() {
		return skip;
	}
	public String getPopulation() {
		return population;
	}
	public String getResult() {
		return result;
	}
	public boolean getScreen(){
		return noScreen;
	}

}
