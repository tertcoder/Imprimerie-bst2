/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class Commande {
    private int idCommande;
    private String descriptionCommande;
    private java.sql.Date dateCommande;
    private String statutCommande;
    private int idEmploye;
    private int idClient;

    // Constructeur par défaut
    public Commande() {
    }

    // Constructeur avec tous les paramètres
    public Commande(int idCommande, String descriptionCommande, java.sql.Date dateCommande, String statutCommande, int idEmploye, int idClient) {
        this.idCommande = idCommande;
        this.descriptionCommande = descriptionCommande;
        this.dateCommande = dateCommande;
        this.statutCommande = statutCommande;
        this.idEmploye = idEmploye;
        this.idClient = idClient;
    }

    // Constructeur sans idCommande
    public Commande(String descriptionCommande, java.sql.Date dateCommande, String statutCommande, int idEmploye, int idClient) {
        this.descriptionCommande = descriptionCommande;
        this.dateCommande = dateCommande;
        this.statutCommande = statutCommande;
        this.idEmploye = idEmploye;
        this.idClient = idClient;
    }

    // Getters et Setters
    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public String getDescriptionCommande() {
        return descriptionCommande;
    }

    public void setDescriptionCommande(String descriptionCommande) {
        this.descriptionCommande = descriptionCommande;
    }

    public java.sql.Date getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(java.sql.Date dateCommande) {
        this.dateCommande = dateCommande;
    }

    public String getStatutCommande() {
        return statutCommande;
    }

    public void setStatutCommande(String statutCommande) {
        this.statutCommande = statutCommande;
    }

    public int getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(int idEmploye) {
        this.idEmploye = idEmploye;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }
}
