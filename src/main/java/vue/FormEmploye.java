/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vue;

import controller.EmployeControleur;
import model.Employe;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FormEmploye extends JFrame implements ActionListener {

    JLabel label_id, label_nom, label_prenom, label_age, label_poste, label_sexe;
    JTextField field_id, field_nom, field_prenom, field_age, field_poste;
    JRadioButton radio_masculin, radio_feminin;
    ButtonGroup groupe_sexe;
    JButton btn_add, btn_view, btn_update, btn_delete, btn_reset, btn_search;
    DefaultTableModel modele_table;
    JTable table_employe;
    JScrollPane scroll_pane;

    Employe employe = null;

    public FormEmploye() {
        setLayout(null);
        setTitle("Formulaire Employé");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // ID Label and TextField
        label_id = new JLabel("ID");
        label_id.setBounds(30, 30, 100, 30);
        this.getContentPane().add(label_id);

        field_id = new JTextField();
        field_id.setBounds(140, 30, 150, 30);
        this.getContentPane().add(field_id);

        label_nom = new JLabel("Nom");
        label_nom.setBounds(30, 80, 100, 30);
        this.getContentPane().add(label_nom);

        field_nom = new JTextField();
        field_nom.setBounds(140, 80, 150, 30);
        this.getContentPane().add(field_nom);

        label_prenom = new JLabel("Prénom");
        label_prenom.setBounds(30, 130, 100, 30);
        this.getContentPane().add(label_prenom);

        field_prenom = new JTextField();
        field_prenom.setBounds(140, 130, 150, 30);
        this.getContentPane().add(field_prenom);

        label_age = new JLabel("Âge");
        label_age.setBounds(30, 180, 100, 30);
        this.getContentPane().add(label_age);

        field_age = new JTextField();
        field_age.setBounds(140, 180, 150, 30);
        this.getContentPane().add(field_age);

        label_poste = new JLabel("Poste");
        label_poste.setBounds(30, 230, 100, 30);
        this.getContentPane().add(label_poste);

        field_poste = new JTextField();
        field_poste.setBounds(140, 230, 150, 30);
        this.getContentPane().add(field_poste);

        // Label et boutons radio pour Sexe
        label_sexe = new JLabel("Sexe");
        label_sexe.setBounds(30, 280, 100, 30);
        this.getContentPane().add(label_sexe);

        radio_masculin = new JRadioButton("Masculin");
        radio_masculin.setBounds(140, 280, 100, 30);
        this.getContentPane().add(radio_masculin);

        radio_feminin = new JRadioButton("Féminin");
        radio_feminin.setBounds(240, 280, 100, 30);
        this.getContentPane().add(radio_feminin);

        // Groupement des boutons radio pour que seul un puisse être sélectionné à la fois
        groupe_sexe = new ButtonGroup();
        groupe_sexe.add(radio_masculin);
        groupe_sexe.add(radio_feminin);

        btn_add = new JButton("Ajouter");
        btn_add.setBounds(30, 330, 100, 30);
        btn_add.addActionListener(this);
        this.getContentPane().add(btn_add);

        btn_view = new JButton("Visualiser");
        btn_view.setBounds(140, 330, 100, 30);
        btn_view.addActionListener(this);
        this.getContentPane().add(btn_view);

        btn_update = new JButton("Modifier");
        btn_update.setBounds(250, 330, 100, 30);
        btn_update.addActionListener(this);
        this.getContentPane().add(btn_update);

        btn_delete = new JButton("Supprimer");
        btn_delete.setBounds(360, 330, 100, 30);
        btn_delete.addActionListener(this);
        this.getContentPane().add(btn_delete);

        btn_reset = new JButton("Réinitialiser");
        btn_reset.setBounds(470, 330, 100, 30);
        btn_reset.addActionListener(this);
        this.getContentPane().add(btn_reset);

        btn_search = new JButton("Rechercher");
        btn_search.setBounds(300, 30, 120, 30);
        btn_search.addActionListener(this);
        this.getContentPane().add(btn_search);

        modele_table = new DefaultTableModel(new String[]{"ID", "Nom", "Prénom", "Âge", "Poste", "Sexe"}, 0);
        table_employe = new JTable(modele_table);
        scroll_pane = new JScrollPane(table_employe);
        scroll_pane.setBounds(30, 370, 540, 200);
        this.getContentPane().add(scroll_pane);
    }

    public void ajouter_employe() {
        String nom = field_nom.getText();
        String prenom = field_prenom.getText();
        int age = Integer.parseInt(field_age.getText());
        String poste = field_poste.getText();
        String sexe = radio_masculin.isSelected() ? "Masculin" : (radio_feminin.isSelected() ? "Féminin" : "");

        if (!nom.isEmpty() && !prenom.isEmpty() && age > 0 && !poste.isEmpty() && !sexe.isEmpty()) {
            Employe employe = new Employe(nom, prenom, age, poste, sexe);
            EmployeControleur.insererEmploye(employe);
            JOptionPane.showMessageDialog(this, "Employé bien enregistré");
            visualiser_employe();

        } else {
            JOptionPane.showMessageDialog(this, "Complétez le formulaire au complet!");
        }
    }

    public void visualiser_employe() {
        modele_table.setRowCount(0);
        ArrayList<Employe> employes = EmployeControleur.getEmployes();
        for (Employe e : employes) {
            modele_table.addRow(new Object[]{e.getIdEmploye(), e.getNomEmploye(), e.getPrenomEmploye(), e.getAge(), e.getPoste(), e.getSexeEmploye()});
        }
    }

    public void rechercher_employe() {
        int id = Integer.parseInt(field_id.getText());
        employe = EmployeControleur.getEmployeById(id);
        if (employe != null) {
            completerEmploye(employe);
        } else {
            JOptionPane.showMessageDialog(this, "Employé non trouvé !");
        }
    }

    public void completerEmploye(Employe e) {
        field_id.setText(String.valueOf(e.getIdEmploye()));
        field_nom.setText(e.getNomEmploye());
        field_prenom.setText(e.getPrenomEmploye());
        field_age.setText(String.valueOf(e.getAge()));
        field_poste.setText(e.getPoste());
        if (e.getSexeEmploye().equalsIgnoreCase("Masculin")) {
            radio_masculin.setSelected(true);
        } else if (e.getSexeEmploye().equalsIgnoreCase("Féminin")) {
            radio_feminin.setSelected(true);
        }
    }

    private void reset_formulaire() {
        field_id.setText("");
        field_nom.setText("");
        field_prenom.setText("");
        field_age.setText("");
        field_poste.setText("");
        groupe_sexe.clearSelection();
    }

    private void supprimer_employe() {
        if (employe != null) {
            String mssg = "Voulez-vous supprimer l'employé " + employe.getNomEmploye() + " " + employe.getPrenomEmploye() + " ?";
            int rep = JOptionPane.showConfirmDialog(null, mssg);
            if (rep == 0) {
                EmployeControleur.deleteEmploye(employe);
                visualiser_employe();
                reset_formulaire();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un employé d'abord !");
        }
    }

    private void modifier_employe() {
        int id = Integer.parseInt(field_id.getText());
        String nom = field_nom.getText();
        String prenom = field_prenom.getText();
        int age = Integer.parseInt(field_age.getText());
        String poste = field_poste.getText();
        String sexe = radio_masculin.isSelected() ? "Masculin" : (radio_feminin.isSelected() ? "Féminin" : "");

        if (!nom.isEmpty() && !prenom.isEmpty() && age > 0 && !poste.isEmpty() && !sexe.isEmpty()) {
            if (employe != null && employe.getIdEmploye() == id) {
                employe.setNomEmploye(nom);
                employe.setPrenomEmploye(prenom);
                employe.setAge(age);
                employe.setPoste(poste);
                employe.setSexeEmploye(sexe);

                EmployeControleur.modifierEmploye(employe);
                JOptionPane.showMessageDialog(this, "Employé bien modifié");
                visualiser_employe();
            } else {
                JOptionPane.showMessageDialog(this, "Employé non trouvé ou ID incorrect !");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Complétez le formulaire au complet!");
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_add) {
            ajouter_employe();
        } else if (e.getSource() == btn_view) {
            visualiser_employe();
        } else if (e.getSource() == btn_search) {
            rechercher_employe();
        } else if (e.getSource() == btn_delete) {
            supprimer_employe();
        } else if (e.getSource() == btn_reset) {
            reset_formulaire();
        } else if (e.getSource() == btn_update) {
            modifier_employe();
        }
    }
}