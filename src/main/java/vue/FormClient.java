/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vue;

import controller.ClientControleur;
import model.Client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FormClient extends JFrame implements ActionListener {

    JLabel label_id, label_nom, label_prenom, label_age, label_adresse, label_tel;
    JTextField field_id, field_nom, field_prenom, field_age, field_adresse, field_tel;
    JButton btn_add, btn_view, btn_update, btn_delete, btn_reset, btn_search;
    DefaultTableModel modele_table;
    JTable table_client;
    JScrollPane scroll_pane;

    Client client = null;

    public FormClient() {
        setLayout(null);
        setTitle("Formulaire Client");
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

        label_adresse = new JLabel("Adresse");
        label_adresse.setBounds(30, 230, 100, 30);
        this.getContentPane().add(label_adresse);

        field_adresse = new JTextField();
        field_adresse.setBounds(140, 230, 150, 30);
        this.getContentPane().add(field_adresse);

        label_tel = new JLabel("Numéro de téléphone");
        label_tel.setBounds(30, 280, 150, 30);
        this.getContentPane().add(label_tel);

        field_tel = new JTextField();
        field_tel.setBounds(180, 280, 150, 30);
        this.getContentPane().add(field_tel);

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

        modele_table = new DefaultTableModel(new String[]{"ID", "Nom", "Prénom", "Âge", "Adresse", "Numéro de téléphone"}, 0);
        table_client = new JTable(modele_table);
        scroll_pane = new JScrollPane(table_client);
        scroll_pane.setBounds(30, 370, 540, 200);
        this.getContentPane().add(scroll_pane);
    }

    public void ajouter_client() {
        String nom = field_nom.getText();
        String prenom = field_prenom.getText();
        int age = Integer.parseInt(field_age.getText());
        String adresse = field_adresse.getText();
        int tel = Integer.parseInt(field_tel.getText());

        if (!nom.isEmpty() && !prenom.isEmpty() && age > 0 && !adresse.isEmpty() && tel > 0) {
            Client client = new Client(nom, prenom, age, adresse, tel);
            ClientControleur.insererClient(client);
            JOptionPane.showMessageDialog(this, "Client bien enregistré");
            visualiser_client();

        } else {
            JOptionPane.showMessageDialog(this, "Complétez le formulaire au complet!");
        }
    }

    public void visualiser_client() {
        modele_table.setRowCount(0);
        ArrayList<Client> clients = ClientControleur.getClients();
        for (Client c : clients) {
            modele_table.addRow(new Object[]{c.getIdClient(), c.getNomClient(), c.getPrenomClient(), c.getAge(), c.getAdresseClient(), c.getNumTel()});
        }
    }

    public void rechercher_client() {
        int id = Integer.parseInt(field_id.getText()); // Use field_id to get the ID
        client = ClientControleur.getClientById(id);
        if (client != null) {
            completerClient(client);
        } else {
            JOptionPane.showMessageDialog(this, "Client non trouvé !");
        }
    }

    public void completerClient(Client c) {
        field_id.setText(String.valueOf(c.getIdClient()));
        field_nom.setText(c.getNomClient());
        field_prenom.setText(c.getPrenomClient());
        field_age.setText(String.valueOf(c.getAge()));
        field_adresse.setText(c.getAdresseClient());
        field_tel.setText(String.valueOf(c.getNumTel()));
    }

    private void reset_formulaire() {
        field_id.setText("");
        field_nom.setText("");
        field_prenom.setText("");
        field_age.setText("");
        field_adresse.setText("");
        field_tel.setText("");
    }

    private void supprimer_client() {
        if (client != null) {
            String mssg = "Voulez-vous supprimer le client " + client.getNomClient() + " " + client.getPrenomClient() + " ?";
            int rep = JOptionPane.showConfirmDialog(null, mssg);
            if (rep == 0) {
                ClientControleur.deleteClient(client);
                visualiser_client();
                reset_formulaire();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un client d'abord !");
        }
    }

    private void modifier_client() {
        int id = Integer.parseInt(field_id.getText()); // Retrieve the ID from field_id
        String nom = field_nom.getText();
        String prenom = field_prenom.getText();
        int age = Integer.parseInt(field_age.getText());
        String adresse = field_adresse.getText();
        int tel = Integer.parseInt(field_tel.getText());

        if (!nom.isEmpty() && !prenom.isEmpty() && age > 0 && !adresse.isEmpty() && tel > 0) {
            if (client != null && client.getIdClient() == id) {
                client.setNomClient(nom);
                client.setPrenomClient(prenom);
                client.setAge(age);
                client.setAdresseClient(adresse);
                client.setNumTel(tel);

                ClientControleur.modifierClient(client);
                JOptionPane.showMessageDialog(this, "Client bien modifié");
                visualiser_client();
            } else {
                JOptionPane.showMessageDialog(this, "Client non trouvé ou ID incorrect !");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Complétez le formulaire au complet!");
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_add) {
            ajouter_client();
        } else if (e.getSource() == btn_view) {
            visualiser_client();
        } else if (e.getSource() == btn_search) {
            rechercher_client();
        } else if (e.getSource() == btn_delete) {
            supprimer_client();
        } else if (e.getSource() == btn_reset) {
            reset_formulaire();
        } else if (e.getSource() == btn_update) {
            modifier_client();
        }
    }
}