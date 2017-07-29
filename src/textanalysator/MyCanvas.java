package textanalysator;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

/**
 *
 * @author Tom
 */
public class MyCanvas extends Canvas{

    LinkedList<ZnakPocet> pocetznakov;
    MyFrame F;
    
    public MyCanvas(MyFrame F) {
        
        this.F = F;
    }
    
    @Override
    public void paint(Graphics g) {
        
       
        if (F.shouldDrawComparation) {
            
            LinkedList<ZnakPocet> L1 = F.pocetZnakov;
            LinkedList<ZnakPocet> L2 = F.pocetZnakov2;
            
            if (L1 == null || L2 == null)
                return;
            
            double maxpocet = 0;

            for (int i = 0; i < L1.size(); i++) {
                if (L1.get(i).pocet > maxpocet)
                    maxpocet = L1.get(i).pocet;
                
            }
            
            for (int j = 0; j < L2.size(); j++) {
                if (L2.get(j).pocet > maxpocet)
                    maxpocet = L2.get(j).pocet;    
            }
            
            double aktualnaVyska = this.getHeight()/2 - this.getHeight()/10;
            double pomer = aktualnaVyska/maxpocet;
            
            LinkedList<ZnakPocet> difference = new LinkedList<>();
            
            String abeceda = "aáäbcčdďeéfghiíjklĺľmnňoóôpqrŕsštťuúvwxyýzž";
            //+ dz dž ch
            for (int i = 0; i < abeceda.length(); i++) {

                ZnakPocet tmp = new ZnakPocet(abeceda.charAt(i), 0);
                difference.add(tmp);
            }

            ZnakPocet tmp = new ZnakPocet(1000, 0); // 1000 bude dz
            difference.add(tmp);
            tmp = new ZnakPocet(1001, 0);           //1001 bude dž
            difference.add(tmp);
            tmp = new ZnakPocet(1002, 0);           //1002 bude ch
            difference.add(tmp);
            
            for (int i = 0; i < difference.size(); i++) {
                
                for (int j = 0; j < L2.size(); j++) {
                    
                    if (difference.get(i).kod == L2.get(j).kod) {
                        difference.get(i).pocet = L2.get(j).pocet;
                        
                        if (L2.get(j).pocet > 0)
                            difference.get(i).wasUsedAtLeastOnceInComparation = true;
                        
                        break;
                    }
                }
                

            }
            
            for (int i = 0; i < difference.size(); i++) {
                
                for (int j = 0; j < L1.size(); j++) {

                        if (difference.get(i).kod == L1.get(j).kod) {
                            difference.get(i).pocet = difference.get(i).pocet - L1.get(j).pocet;
                            
                            if (L1.get(j).pocet > 0)
                                difference.get(i).wasUsedAtLeastOnceInComparation = true;
                            
                            break;
                        }
                }
            }
            int j = 0;
            int posX;
            int posY;
            int vyskaStlpika;
            
            for (int i = 0; i < difference.size(); i++) { 
                
                if (difference.get(i).pocet == 0 && difference.get(i).wasUsedAtLeastOnceInComparation == false) 
                    continue;
                
                
                
                vyskaStlpika = (int)(difference.get(i).pocet*pomer);
                    
                if (vyskaStlpika == 0 && difference.get(i).pocet > 0)
                    vyskaStlpika = 1;
                
                posX = 20*j + 50 + j;
                
                char c = (char)difference.get(i).kod;
                String str = "";
                str = str + c;
                
                if (difference.get(i).kod == 1000) {
                    str = "dz";
                }
                else if (difference.get(i).kod == 1001) {
                    str = "dž";
                }
                else if (difference.get(i).kod == 1002) {
                    str = "ch";
                }
                
                
                if (difference.get(i).pocet == 0 && difference.get(i).wasUsedAtLeastOnceInComparation == true) {
                    g.drawString(str, posX+5, this.getHeight()/2);
                    j++;
                    continue;
                }
                
                g.drawString(str, posX+5, this.getHeight()/2);
                
                if (difference.get(i).pocet > 0) {
                    g.setColor(Color.GREEN);
                    posY = this.getHeight()/2  - 15 - vyskaStlpika;
                    g.fillRect(posX, posY, 20, vyskaStlpika); 
                    
                    g.setColor(Color.BLACK);
                    g.drawString(Integer.toString(difference.get(i).pocet), posX+5, posY - 10);
                }
                
                else if (difference.get(i).pocet < 0) {
                    g.setColor(Color.BLUE);
                    posY = this.getHeight()/2 + 15;
                    g.fillRect(posX, posY, 20, -vyskaStlpika);
                    
                    g.setColor(Color.BLACK);
                    g.drawString(Integer.toString(difference.get(i).pocet), posX+5, posY + (-vyskaStlpika) + 15);
                }
                
                
                j++;
            }
            
            
            
            
            
            
            
            
            
            
            
            
            
            return;
        }
        
        if(F.pocetZnakov == null)
            return;
        
        this.pocetznakov = F.pocetZnakov;
        double maxsize = 0;
        for (int i = 0; i < pocetznakov.size(); i++) {
            if (pocetznakov.get(i).pocet > maxsize) {
                maxsize = pocetznakov.get(i).pocet;
            } 
        }
        
        
        
        int j = 0;
        int posX;
        int posY;
        int vyskaStlpika = 0;
        double aktualnavyska = this.getHeight() - this.getHeight()/10;
        double pomer = aktualnavyska/maxsize;
        
        for (int i = 0; i < pocetznakov.size(); i++) {
            
            if (pocetznakov.get(i).pocet != 0) {
                g.setColor(Color.red);
                
                vyskaStlpika = (int)(pocetznakov.get(i).pocet*pomer);
                
                if (vyskaStlpika == 0 && pocetznakov.get(i).pocet > 0)
                    vyskaStlpika = 1;
                
                posX = 20*j + 50 + j;
                posY = this.getHeight() - 15 - vyskaStlpika;
                
                g.fillRect(posX, posY, 20, vyskaStlpika);
                
                char c = (char)pocetznakov.get(i).kod;
                String str = "";
                str = str + c;
                
                if (pocetznakov.get(i).kod == 1000) {
                    str = "dz";
                }
                else if (pocetznakov.get(i).kod == 1001) {
                    str = "dž";
                }
                else if (pocetznakov.get(i).kod == 1002) {
                    str = "ch";
                }
                
                g.setColor(Color.BLACK);
                g.drawString(str, posX + 5, this.getHeight() - 5);
                
                j++;
            }
            else {
                
            }
            
            
        }
        
        j = 0;
         for (int i = 0; i < pocetznakov.size(); i++) {
             
             if (pocetznakov.get(i).pocet != 0) {
             
             vyskaStlpika = (int)(pocetznakov.get(i).pocet*pomer);
             posX = 20*j + 50 + j;
             posY = this.getHeight() - 15 - vyskaStlpika;
             
             g.drawString(Integer.toString(pocetznakov.get(i).pocet), posX, posY - 10);
             j++;
             }
         }
        
        
    }
    
  
    
}
