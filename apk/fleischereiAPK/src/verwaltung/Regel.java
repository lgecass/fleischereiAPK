// 
// Decompiled by Procyon v0.5.36
// 

package verwaltung;

public class Regel
{
    public String food;
    public String dependency;
    
    public Regel(final String foood, final String substring2) {
        this.food = "";
        this.dependency = "";
        this.food = foood;
        this.dependency = substring2;
    }
    
    @Override
    public String toString() {
        return String.valueOf(this.food) + " " + this.dependency;
    }
}
