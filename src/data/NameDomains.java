package data;

import java.io.File;
import java.util.ArrayList;

public class NameDomains {
	public ArrayList<String> domains;
	
	public NameDomains(String pathDataSets){
		File folder = new File(pathDataSets);
		File[] listOfFiles = folder.listFiles();
		
		domains = new ArrayList<String>();
		for(int i = 0; i < listOfFiles.length; i++){
			if(listOfFiles[i].isFile())
				domains.add(listOfFiles[i].getName());
		}
	}

	public ArrayList<String> getDomains() {
		return domains;
	}

}
