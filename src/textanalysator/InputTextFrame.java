package textanalysator;

import java.awt.Button;
import java.awt.Font;
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
public class InputTextFrame extends Frame implements ActionListener {
    
    MyFrame F;
    TextArea TA;
    Button OK;
    
    public InputTextFrame(MyFrame parrent, String str) {
        this.F = parrent;
        this.setSize(300, 300);
        this.setLocationRelativeTo(F);
        
        addWindowListener(new WindowAdapter ()
                                {   public void windowClosing(WindowEvent e) {
                                    
                                        dispose();
                                    }
                                }
        );
        
        
        
        TA = new TextArea("");
        TA.setText(str);
        
        this.add(TA);
        
        OK = new  Button("Ulož a analyzuj");
        OK.addActionListener(this);
        
        Panel P = new Panel();
        P.add(OK);
        
        this.add("South",P);
        this.add("Center",TA);
        
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == OK) {
            
            F.shouldDrawComparation = false;
            F.toDo = TA.getText();
            F.pocetZnakov = PocitadloZnakov.PocitadloZnakov(F.toDo);
            F.C.repaint();
            
            this.dispose();
        }
    }
    
    
    
}
