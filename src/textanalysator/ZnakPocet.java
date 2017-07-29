/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
