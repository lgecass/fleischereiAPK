// 
// Decompiled by Procyon v0.5.36
// 

package verwaltung;

public class Food
{
    String foodname;
    String zubereitung;
    String foodloc;
    String foodprice;
    
    public Food(final String fn, final String fl, final String fp) {
        this.foodname = fn;
        this.foodloc = fl;
        this.foodprice = fp;
    }
    
    public Food(final String fn, final String fl, final String fp, final boolean n) {
        this.foodname = fn;
        this.zubereitung = fl;
        this.foodprice = fp;
    }
}
