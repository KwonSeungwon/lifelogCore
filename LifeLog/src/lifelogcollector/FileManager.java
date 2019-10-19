package lifelogcollector;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileManager {
	
	public List<File> Slist(String directoryName) {
        File directory = new File("c:\\STEPS\\");

        List<File> StepsList = new ArrayList<File>();

        File[] sList = directory.listFiles();
        StepsList.addAll(Arrays.asList(sList));
        for (File file : sList) {
            if (file.isFile()) {
            } else if (file.isDirectory()) {
            	StepsList.addAll(Slist(file.getAbsolutePath()));
            }
        }
        return StepsList;
	}
      
        public List<File> HRlist(String directoryName) {
            File directory = new File("c:\\HR\\");

            List<File> HrList = new ArrayList<File>();

            File[] hList = directory.listFiles();
            HrList.addAll(Arrays.asList(hList));
            for (File file : hList) {
                if (file.isFile()) {
                } else if (file.isDirectory()) {
                	HrList.addAll(Slist(file.getAbsolutePath()));
                }
            }
            return HrList;
        }
            
            public List<File> TCXlist(String directoryName) {
                File directory = new File("c:\\TCX\\");

                List<File> TcxList = new ArrayList<File>();

                File[] tList = directory.listFiles();
                TcxList.addAll(Arrays.asList(tList));
                for (File file : tList) {
                    if (file.isFile()) {
                    } else if (file.isDirectory()) {
                    	TcxList.addAll(Slist(file.getAbsolutePath()));
                    }
                }
                return TcxList;
    } 
}



		
		
		

			


	






