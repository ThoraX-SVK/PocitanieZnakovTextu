package textanalysator;

/**
 *
 * @author Tom
 */
public class ZnakPocet {
    
    public int kod;
    public int pocet;
    public boolean wasUsedAtLeastOnceInComparation;

    public ZnakPocet(int kod, int pocet) {
        this.kod = kod;
        this.pocet = pocet;
        this.wasUsedAtLeastOnceInComparation = false;
    }
    
}
