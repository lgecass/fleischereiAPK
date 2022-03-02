// 
// Decompiled by Procyon v0.5.36
// 

package verwaltung;

import java.util.ArrayList;

public class Auftrag
{
    public Kunde k;
    public ArrayList<Food> liste;
    public String auftragsnummer;
    
    public Auftrag(final Kunde k, final ArrayList<Food> m) {
        this.k = null;
        this.liste = null;
        this.auftragsnummer = "";
        this.liste = m;
        this.k = k;
    }
    
    public Auftrag() {
        this.k = null;
        this.liste = null;
        this.auftragsnummer = "";
    }
}
