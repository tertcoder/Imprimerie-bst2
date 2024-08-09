/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;


import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Client;

public class ClientControleur {
    private static Connection conn = null;
    private static Statement stm;
    private static PreparedStatement pstmet = null;
    private static ResultSet rs = null;

    // Méthode pour insérer un client dans la base de données
    public static void insererClient(Client client) {
        try {
            conn = getConnection();
            pstmet = conn.prepareStatement("INSERT INTO examen.client(Nom_client, Prenom_client, Age, Adresse_client, Num_tel) VALUES(?, ?, ?, ?, ?)");
            pstmet.setString(1, client.getNomClient());
            pstmet.setString(2, client.getPrenomClient());
            pstmet.setInt(3, client.getAge());
            pstmet.setString(4, client.getAdresseClient());
            pstmet.setInt(5, client.getNumTel());
            pstmet.executeUpdate();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    // Méthode pour obtenir tous les clients de la base de données
    public static ArrayList<Client> getClients() {
        ArrayList<Client> clients = new ArrayList<>();
        try {
            conn = getConnection();
            stm = conn.createStatement();
            rs = stm.executeQuery("SELECT * FROM examen.client");
            while (rs.next()) {
                Client client = new Client(
                    rs.getInt("Id_client"),
                    rs.getString("Nom_client"),
                    rs.getString("Prenom_client"),
                    rs.getInt("Age"),
                    rs.getString("Adresse_client"),
                    rs.getInt("Num_tel")
                );
                clients.add(client);
            }
            conn.close();
            stm.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return clients;
    }

    // Méthode pour obtenir un client par ID
    public static Client getClientById(int id) {
        Client client = null;
        try {
            conn = getConnection();
            stm = conn.createStatement();
            rs = stm.executeQuery("SELECT * FROM examen.client WHERE Id_client='" + id + "'");
            if (rs.next()) {
                client = new Client(
                    rs.getInt("Id_client"),
                    rs.getString("Nom_client"),
                    rs.getString("Prenom_client"),
                    rs.getInt("Age"),
                    rs.getString("Adresse_client"),
                    rs.getInt("Num_tel")
                );
            }
            conn.close();
            stm.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return client;
    }

    // Méthode pour supprimer un client de la base de données
    public static void deleteClient(Client client) {
        try {
            conn = getConnection();
            stm = conn.createStatement();
            String requete = "DELETE FROM examen.client WHERE Id_client='" + client.getIdClient() + "'";
            stm.executeUpdate(requete);
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    // Méthode pour modifier les informations d'un client
    public static void modifierClient(Client client) {
        try {
            conn = getConnection();
            pstmet = conn.prepareStatement("UPDATE examen.client SET Nom_client=?, Prenom_client=?, Age=?, Adresse_client=?, Num_tel=? WHERE Id_client=?");
            pstmet.setString(1, client.getNomClient());
            pstmet.setString(2, client.getPrenomClient());
            pstmet.setInt(3, client.getAge());
            pstmet.setString(4, client.getAdresseClient());
            pstmet.setInt(5, client.getNumTel());
            pstmet.setInt(6, client.getIdClient());
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