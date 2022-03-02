// 
// Decompiled by Procyon v0.5.36
// 

package verwaltung;

import java.util.ArrayList;

public class Sortierung implements Comparable<Sortierung>
{
    ArrayList<String> zeilen;
    public String datum;
    
    public Sortierung(final ArrayList<String> zeilen, final String datum) {
        this.zeilen = new ArrayList<String>();
        this.datum = "";
        this.zeilen = zeilen;
        this.datum = datum;
    }
    
    public Sortierung() {
        this.zeilen = new ArrayList<String>();
        this.datum = "";
    }
    
    @Override
    public int compareTo(final Sortierung obj) {
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
