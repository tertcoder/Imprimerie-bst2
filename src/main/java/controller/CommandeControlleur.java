/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Commande;

public class CommandeControlleur {
    private static Connection conn = null;
    private static Statement stm;
    private static PreparedStatement pstmet = null;
    private static ResultSet rs = null;

    // Méthode pour insérer une commande dans la base de données
    public static void insererCommande(Commande commande) {
        try {
            conn = getConnection();
            pstmet = conn.prepareStatement("INSERT INTO examen.commande(Description_commande, Date_commande, Statut_commande, Id_employe, Id_client) VALUES(?, ?, ?, ?, ?)");
            pstmet.setString(1, commande.getDescriptionCommande());
            pstmet.setDate(2, commande.getDateCommande());
            pstmet.setString(3, commande.getStatutCommande());
            pstmet.setInt(4, commande.getIdEmploye());
            pstmet.setInt(5, commande.getIdClient());
            pstmet.executeUpdate();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    // Méthode pour obtenir toutes les commandes de la base de données
    public static ArrayList<Commande> getCommandes() {
        ArrayList<Commande> commandes = new ArrayList<>();
        try {
            conn = getConnection();
            stm = conn.createStatement();
            rs = stm.executeQuery("SELECT * FROM examen.commande");
            while (rs.next()) {
                Commande commande = new Commande(
                    rs.getInt("Id_commande"),
                    rs.getString("Description_commande"),
                    rs.getDate("Date_commande"),
                    rs.getString("Statut_commande"),
                    rs.getInt("Id_employe"),
                    rs.getInt("Id_client")
                );
                commandes.add(commande);
            }
            conn.close();
            stm.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return commandes;
    }

    // Méthode pour obtenir une commande par ID
    public static Commande getCommandeById(int id) {
        Commande commande = null;
        try {
            conn = getConnection();
            stm = conn.createStatement();
            rs = stm.executeQuery("SELECT * FROM examen.commande WHERE Id_commande='" + id + "'");
            if (rs.next()) {
                commande = new Commande(
                    rs.getInt("Id_commande"),
                    rs.getString("Description_commande"),
                    rs.getDate("Date_commande"),
                    rs.getString("Statut_commande"),
                    rs.getInt("Id_employe"),
                    rs.getInt("Id_client")
                );
            }
            conn.close();
            stm.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return commande;
    }

    // Méthode pour supprimer une commande de la base de données
    public static void deleteCommande(Commande commande) {
        try {
            conn = getConnection();
            stm = conn.createStatement();
            String requete = "DELETE FROM examen.commande WHERE Id_commande='" + commande.getIdCommande() + "'";
            stm.executeUpdate(requete);
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    // Méthode pour modifier les informations d'une commande
    public static void modifierCommande(Commande commande) {
        try {
            conn = getConnection();
            pstmet = conn.prepareStatement("UPDATE examen.commande SET Description_commande=?, Date_commande=?, Statut_commande=?, Id_employe=?, Id_client=? WHERE Id_commande=?");
            pstmet.setString(1, commande.getDescriptionCommande());
            pstmet.setDate(2, commande.getDateCommande());
            pstmet.setString(3, commande.getStatutCommande());
            pstmet.setInt(4, commande.getIdEmploye());
            pstmet.setInt(5, commande.getIdClient());
            pstmet.setInt(6, commande.getIdCommande());
            pstmet.executeUpdate();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    // Méthode pour établir une connexion à la base de données
    public static Connection getConnection() {
        String serveur = "localhost";
        int port = 3306;
        String database = "examen";
        String username = "root";
        String password = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://" + serveur + ":" + port + "/" + database;
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("connected");
            return conn;
        } catch (Exception e) {
            System.out.println("ERREUR");
            e.printStackTrace();
            return null;
        }
    }
}