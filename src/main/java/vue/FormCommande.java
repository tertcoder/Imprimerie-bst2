/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vue;


import controller.ClientControleur;
import controller.CommandeControlleur;
import model.Client;
import model.Commande;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;

public class FormCommande extends JFrame implements ActionListener {

    JLabel labelNomClient, labelPrenomClient, labelAdresseClient, labelNumTelClient, labelDateNaissanceClient, labelDescriptionCommande, labelDateCommande, labelEmploye;
    JTextField fieldNomClient, fieldPrenomClient, fieldAdresseClient, fieldNumTelClient, fieldDateNaissanceClient, fieldDateCommande;
    JTextArea areaDescriptionCommande;
    JComboBox<String> comboEmploye;
    JButton btnAjouter;
   
    public FormCommande() {
        

        setLayout(null);
        setTitle("Formulaire Commande");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Informations du Client
        labelNomClient = new JLabel("Nom");
        labelNomClient.setBounds(30, 30, 100, 30);
        this.getContentPane().add(labelNomClient);

        fieldNomClient = new JTextField();
        fieldNomClient.setBounds(150, 30, 150, 30);
        this.getContentPane().add(fieldNomClient);

        labelPrenomClient = new JLabel("Prénom");
        labelPrenomClient.setBounds(30, 80, 100, 30);
        this.getContentPane().add(labelPrenomClient);

        fieldPrenomClient = new JTextField();
        fieldPrenomClient.setBounds(150, 80, 150, 30);
        this.getContentPane().add(fieldPrenomClient);

        labelAdresseClient = new JLabel("Adresse");
        labelAdresseClient.setBounds(30, 130, 100, 30);
        this.getContentPane().add(labelAdresseClient);

        fieldAdresseClient = new JTextField();
        fieldAdresseClient.setBounds(150, 130, 150, 30);
        this.getContentPane().add(fieldAdresseClient);

        labelNumTelClient = new JLabel("Numéro de téléphone");
        labelNumTelClient.setBounds(30, 180, 150, 30);
        this.getContentPane().add(labelNumTelClient);

        fieldNumTelClient = new JTextField();
        fieldNumTelClient.setBounds(150, 180, 150, 30);
        this.getContentPane().add(fieldNumTelClient);

        // Description de la Commande
        labelDescriptionCommande = new JLabel("Description de la commande");
        labelDescriptionCommande.setBounds(30, 230, 200, 30);
        this.getContentPane().add(labelDescriptionCommande);

        areaDescriptionCommande = new JTextArea();
        areaDescriptionCommande.setBounds(150, 230, 200, 60);
        this.getContentPane().add(areaDescriptionCommande);

        // Date de la Commande
        labelDateCommande = new JLabel("Date de la commande");
        labelDateCommande.setBounds(30, 300, 200, 30);
        this.getContentPane().add(labelDateCommande);

        fieldDateCommande = new JTextField();
        fieldDateCommande.setBounds(150, 300, 150, 30);
        this.getContentPane().add(fieldDateCommande);

        // Employé
        labelEmploye = new JLabel("Employé");
        labelEmploye.setBounds(30, 350, 200, 30);
        this.getContentPane().add(labelEmploye);

        comboEmploye = new JComboBox<>(new String[]{"Employé 1", "Employé 2"}); // Remplacer par une liste dynamique d'employés
        comboEmploye.setBounds(150, 350, 150, 30);
        this.getContentPane().add(comboEmploye);

        // Bouton d'ajout
        btnAjouter = new JButton("Ajouter Commande");
        btnAjouter.setBounds(150, 400, 200, 30);
        btnAjouter.addActionListener(this);
        this.getContentPane().add(btnAjouter);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAjouter) {
            // Récupération des données du formulaire
            String nomClient = fieldNomClient.getText();
            String prenomClient = fieldPrenomClient.getText();
            String adresseClient = fieldAdresseClient.getText();
            int numTelClient = Integer.parseInt(fieldNumTelClient.getText());
            String descriptionCommande = areaDescriptionCommande.getText();
            Date dateCommande = Date.valueOf(fieldDateCommande.getText());
            int employeId = comboEmploye.getSelectedIndex() + 1; // Exemple, l'id employé commence à 1

            // Création d'un nouvel objet Client et Commande
            Client client = new Client(nomClient, prenomClient, 0, adresseClient, numTelClient); // L'âge peut être récupéré autrement si nécessaire
            Commande commande = new Commande(descriptionCommande, dateCommande, "En cours", employeId, client.getIdClient());

            // Insertion dans la base de données
            ClientControleur.insererClient(client);
            CommandeControlleur.insererCommande(commande);

            JOptionPane.showMessageDialog(this, "Commande ajoutée avec succès.");
        }
    }

    
}