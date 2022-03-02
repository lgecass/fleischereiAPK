package verwaltung;

import java.util.ArrayList;

public class Auftrag_nach_Datum implements Comparable<Auftrag_nach_Datum> {
public String header="";
public ArrayList<String> zeilen=new ArrayList<String>();
public String datum="";


public Auftrag_nach_Datum(String header, ArrayList<String> zeilen, String datum) {
	this.header=header;
	this.zeilen=zeilen;
	this.datum=datum;
	
}

public Auftrag_nach_Datum() {
	
	
}

public int compareTo(final Auftrag_nach_Datum obj) {
    System.out.println("bin in sortierung");
    System.out.println("dieses" + this.datum + "vergleich" + obj.datum);
    System.out.println(this.datum.substring(6, 10));
    System.out.println(obj.datum.substring(6, 10));
    System.out.println(this.datum.substring(3, 5));
    System.out.println(obj.datum.substring(3, 5));
    System.out.println(this.datum.substring(0, 2));
    System.out.println(obj.datum.substring(0, 2));
    if (Integer.valueOf(this.datum.substring(6, 10)) > Integer.valueOf(obj.datum.substring(6, 10))) {
        return 1;
    }
    if (Integer.valueOf(this.datum.substring(6, 10)) < Integer.valueOf(obj.datum.substring(6, 10))) {
        return -1;
    }
    if (Integer.valueOf(this.datum.substring(3, 5)) > Integer.valueOf(obj.datum.substring(3, 5))) {
        return 1;
    }
    if (Integer.valueOf(this.datum.substring(3, 5)) < Integer.valueOf(obj.datum.substring(3, 5))) {
        return -1;
    }
    if (Integer.valueOf(this.datum.substring(3, 5)) == Integer.valueOf(obj.datum.substring(3, 5))) {
        if (Integer.valueOf(this.datum.substring(0, 2)) > Integer.valueOf(obj.datum.substring(0, 2))) {
            return 1;
        }
        if (Integer.valueOf(this.datum.substring(0, 2)) < Integer.valueOf(obj.datum.substring(0, 2))) {
            return -1;
        }
        if (Integer.valueOf(this.datum.substring(0, 2)) == Integer.valueOf(obj.datum.substring(0, 2))) {
            return 0;
        }
    }
    System.out.println("bin hier ohne zu vergleichen");
    return 0;
}


}
