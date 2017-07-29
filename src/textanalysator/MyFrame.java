/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textanalysator;

import java.awt.Button;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

/**
 *
 * @author Tom
 */
public class MyFrame extends Frame implements ActionListener{

    MyCanvas C;
    
    String toDo;
    LinkedList<ZnakPocet> pocetZnakov;
    LinkedList<ZnakPocet> pocetZnakov2;
    
    boolean shouldDrawComparation;
    
    MenuBar MB;
    
    Menu M1;
    MenuItem LoadText;
    
    Menu M2;
    MenuItem PorovnajDva;
    
    Button Analyze;
    Button WriteText;
    
    
    public MyFrame() {
        super("Analyzator");
        this.setSize(500,600);
        this.setLocationRelativeTo(null);
        
        addWindowListener(new WindowAdapter ()
                                {   public void windowClosing(WindowEvent e) {
                                    
                                    System.exit(0);
                                    }
                                }
        );
        
        C = new MyCanvas(this);
        this.add("Center",C);
        
        MB = new MenuBar();
        this.setMenuBar(MB);
        
        M1 = new Menu("Súbor");
        LoadText = new MenuItem("Načítaj text");
        LoadText.addActionListener(this);
        
        M2 = new Menu("Porovnávanie");
        PorovnajDva = new MenuItem("Porovnanie dvoch textov");
        PorovnajDva.addActionListener(this);
        
        M2.add(PorovnajDva);
        
        
        M1.add(LoadText);
        MB.add(M1);
        MB.add(M2);
        
        
        
        
        
        
        
        Analyze = new Button("Analyzuj");
        Analyze.addActionListener(this);
        
        WriteText = new Button("Otvoriť editor");
        WriteText.addActionListener(this);
        
        Panel P = new Panel();
        
        P.add(Analyze);
        P.add(WriteText);
        
        this.add("South",P);
        
        toDo = "";
        
        
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == LoadText) {
            
            FileDialog FD = new FileDialog(this, "Load",FileDialog.LOAD);
            FD.setVisible(true);

            if (FD.getFile() != null) {
                shouldDrawComparation = false;
                toDo = "";
                try (BufferedReader br = new BufferedReader(new FileReader(FD.getDirectory() + FD.getFile()))) {
                    String line;
                        while ((line = br.readLine()) != null) {
                            toDo = toDo + line;
                        }
                }
                catch (IOException ex) {
                    System.out.println(ex);
                }
                pocetZnakov = PocitadloZnakov.PocitadloZnakov(toDo);
                C.repaint();
            }
        }
        else if (e.getSource() == Analyze) {
            shouldDrawComparation = false;
            if ("".equals(toDo)) {
                
            }
            else {
                pocetZnakov = PocitadloZnakov.PocitadloZnakov(toDo);
                C.repaint();
            }

        }
        else if (e.getSource() == WriteText) {
            
            InputTextFrame ITF = new InputTextFrame(this,toDo);
            
            
        }
        else if (e.getSource() == PorovnajDva) {
            
            InputTwoTexFrame ITTF = new InputTwoTexFrame(this);

        }
        
        
       
    }
    
    
    
    
    
}
