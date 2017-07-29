/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textanalysator;

import java.util.LinkedList;

/**
 *
 * @author Tom
 */
public class PocitadloZnakov {
    
    public static LinkedList<ZnakPocet>PocitadloZnakov(String str) {
        
        LinkedList<ZnakPocet> pocet_znakov = new LinkedList<>();
        String abeceda = "aáäbcčdďeéfghiíjklĺľmnňoóôpqrŕsštťuúvwxyýzž";
        //+ dz dž ch
        for (int i = 0; i < abeceda.length(); i++) {
            
            ZnakPocet tmp = new ZnakPocet(abeceda.charAt(i), 0);
            pocet_znakov.add(tmp);
        }
        
        ZnakPocet tmp = new ZnakPocet(1000, 0); // 1000 bude dz
        pocet_znakov.add(tmp);
        tmp = new ZnakPocet(1001, 0);           //1001 bude dž
        pocet_znakov.add(tmp);
        tmp = new ZnakPocet(1002, 0);           //1002 bude ch
        pocet_znakov.add(tmp);
        
        str = str.toLowerCase();
        
        for (int i = 0; i < str.length(); i++) {
            
            //dravidlo pre dz
            if (str.charAt(i) == 100) {
                try {
                    if (str.charAt(i+1) == 122) {
                        //je to dz
                        //System.out.println("Nasiel som dz");
                        PlusOneCount(pocet_znakov, 1000);
                        i++;
                        continue;
                    }
                    else if (str.charAt(i+1) == 382) {
                        //je to dž
                        //System.out.println("Nasiel som dž");
                        PlusOneCount(pocet_znakov, 1001);
                        i++;
                        continue;
                    }
                }
                catch(Exception e) {
                    //je to d a za nim je koniec
                    
                }
            } 
            else if (str.charAt(i) == 99) {
                try {
                    if (str.charAt(i+1) == 104) {
                        //je to ch
                        //System.out.println("Nasie som ch");
                        PlusOneCount(pocet_znakov, 1002);
                        i++;
                        continue;
                    }
                }
                catch (Exception e) {
                    
                }
            }
            
            PlusOneCount(pocet_znakov, (int)str.charAt(i));
        }
        
        return pocet_znakov;
    }
    
    private static void PlusOneCount(LinkedList<ZnakPocet> LL, int char_num) {
        
        for (int i = 0; i < LL.size(); i++) {
            if (LL.get(i).kod == char_num) {
                LL.get(i).pocet++;
            }
        }
        
    }

    public static void ShowInfo(LinkedList<ZnakPocet> pocet_znakov) {
        for (int i = 0; i < pocet_znakov.size(); i++) {
            
            if(pocet_znakov.get(i).pocet != 0) {
            
            
            if (pocet_znakov.get(i).kod == 1000) {
                System.out.print("Znak: dz ");
            }
            else if (pocet_znakov.get(i).kod == 1001) {
                System.out.print("Znak: dž ");
            }
            else if (pocet_znakov.get(i).kod == 1002) {
                System.out.print("Znak: ch ");
            }
            else {
                System.out.print("Znak: " +(char)pocet_znakov.get(i).kod + " ");
            }
                
            System.out.print("Kod znaku : " +pocet_znakov.get(i).kod);
            System.out.println(" Pocetnost : " +pocet_znakov.get(i).pocet);
            }
            
        }
    }

}
