package verwaltung;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HideFile {
	  public static void main(String[] args) {
		   
		  }
			
		  public static void setHiddenAttrib(File file) {
		    try {
		      List<String> cmdList = new ArrayList<String>();	    
		      cmdList.add("attrib");
		      cmdList.add("-H");
		      cmdList.add(file.getPath());
		      ProcessBuilder pb = new ProcessBuilder();
		      pb.command(cmdList);
		      Process p;		
		      p = pb.start();
		      p.waitFor();
		    } catch (IOException | InterruptedException e) {
		      // TODO Auto-generated catch block
		      e.printStackTrace();
		    }         
		  }
		}

