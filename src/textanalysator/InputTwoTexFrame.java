/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textanalysator;

import java.awt.Button;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author Tom
 */
public class InputTwoTexFrame extends Frame implements ActionListener{

    MyFrame F;
    TextArea TALeft;
    TextArea TARight;
    
    Button OK;
    
    public InputTwoTexFrame(MyFrame F) {
        this.setSize(1000, 400);
        this.setLocationRelativeTo(F);
        
        addWindowListener(new WindowAdapter ()
                                {   public void windowClosing(WindowEvent e) {
                                    
                                        dispose();
                                    }
                                }
        );
        
        this.F = F;
        TALeft = new TextArea("Zelené grafy s kladnou hodnotou ak je v tomto okne viac daného znaku");
               
        TARight = new TextArea("Modré grafy so zápornou hodnotou ak je v tomto okne viac daného znaku");
        
        
        OK = new Button("Ulož oboje a analyzuj");
        OK.addActionListener(this);
        
        Panel P = new Panel();
        
        P.add(OK);
        
        this.add("South", P);
        this.add("West",TALeft);
        this.add("East",TARight);
        
        
        
        this.setVisible(true);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == OK) {
            
            F.pocetZnakov = PocitadloZnakov.PocitadloZnakov(TARight.getText());
            F.pocetZnakov2 = PocitadloZnakov.PocitadloZnakov(TALeft.getText());
            F.shouldDrawComparation = true;
            
            F.C.repaint();
            this.dispose();
            
        }
    }
    
}
