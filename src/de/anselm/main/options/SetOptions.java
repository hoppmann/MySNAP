package de.anselm.main.options;

import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

public class SetOptions {
	private Options options;

	//constructor calls method
	public SetOptions() {
		makeOptions();
	}

	private void makeOptions () {

		// define options
		options = new Options();
		options.addOption("s", "SNPFile", true, "File containing SNPs to be analyzed.");
		options.addOption("t", "temp", true, "Set directory for temporary files (default \"tmp\").");
		options.addOption("lk", "ld-window-kb", true, "To only analyse SNPs that are not more than X kb apart (default = 1mio).");
		options.addOption("lw", "ld-window", true, "To only analyse SNPs that are not more than X SNPs apart (default = 1mio).");
		options.addOption("lr", "ld-window-r2", true, "Threshold of reported value (default = 0.8).");
		options.addOption("k", "keep", false, "Choose to keep tmp folder.");
		options.addOption("h", "help", false, "Shows this help message.");
		options.addOption("sk", "skipPlink", false, "Choose to skip plink calculation. May only be chosen with prior \"keep\" option chosen.");
		options.addOption("p", "population", true, "Choose reference population.(\"EUR\"(default), \"AFR\", \"ASN\" or\"ALL\")");
		options.addOption("r", "resultDir", true, "Name of result directory (default = \"result\").");
		options.addOption("n", "noScreen", false, "Choose this option to skip result output to screen.");

	}

	//display Help
	public void callHelp() {
		HelpFormatter formatter = new HelpFormatter();
		formatter.setWidth(200);
		formatter.printHelp( "Options", options, true);
	}


	//getter for options
	public Options getOptions() {
		return options;
	}
}
