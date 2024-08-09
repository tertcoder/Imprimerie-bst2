/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vue;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipal extends JFrame implements ActionListener {
    JMenuBar barre;
    JMenu menu_db, menu_tr, menu_f;
    JMenuItem item_c, item_m, item_e, item_p, item_qu;
    
    public MenuPrincipal() {
        barre = new JMenuBar();
        
        menu_db = new JMenu("Donnees de base");
        menu_tr = new JMenu("Donnees de traitement");
        menu_f = new JMenu("Fichier");
     
        item_c=new JMenuItem("Clients");
        item_c.addActionListener(this);
        
        item_e=new JMenuItem("Employé");
        item_e.addActionListener(this);
        item_m=new JMenuItem("Commandes");
        item_m.addActionListener(this);
        item_p=new JMenuItem("Paiements");
        item_p.addActionListener(this);
        
        item_qu=new JMenuItem("Quitter");
        item_qu.addActionListener(this);
        
        barre.add(menu_db);
        barre.add(menu_tr);
        barre.add(menu_f);
        
        menu_db.add(item_c);
        menu_db.add(item_e);
        menu_db.add(item_m);
        menu_db.add(item_p);

        
        menu_tr.add(item_qu);
        
        this.getContentPane().add(barre);
        barre.setBounds(10,10,400,40);
        this.setLayout(null);
        
    }
 
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==item_c){
            
            FormClient cf = new FormClient();
            cf.setTitle("Gestion des Clients");
            cf.setSize(600, 600);
            cf.setVisible(true);
            
        } 
        else if(e.getSource()==item_e) {
            
            FormEmploye ef=new FormEmploye();
            
            ef.setTitle("Gestion des Employes");
            ef.setSize(600, 600);
            ef.setVisible(true);
            
        } 
        else if(e.getSource()==item_m) {
            FormCommande mf=new FormCommande();

            mf.setTitle("Commande");
            mf.setSize(600, 600);
            mf.setVisible(true);
            
        }
        //else if(e.getSource()==item_r) {
//            ReparationForm rf=new ReparationForm();
//
//            rf.setTitle("Gestion des Reparation");
//            rf.setSize(600, 600);
//            rf.setVisible(true);
//            
//        } 
        else if(e.getSource()==item_qu) {
            dispose();
        }


    }
}