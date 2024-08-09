/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;


import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Employe;

public class EmployeControleur {
    private static Connection conn = null;
    private static Statement stm;
    private static PreparedStatement pstmet = null;
    private static ResultSet rs = null;

    // Méthode pour insérer un employé dans la base de données
    public static void insererEmploye(Employe employe) {
        try {
            conn = getConnection();
            pstmet = conn.prepareStatement("INSERT INTO examen.employe(Nom_employe, Prenom_employe, Age, Poste, Sexe_employe) VALUES(?, ?, ?, ?, ?)");
            pstmet.setString(1, employe.getNomEmploye());
            pstmet.setString(2, employe.getPrenomEmploye());
            pstmet.setInt(3, employe.getAge());
            pstmet.setString(4, employe.getPoste());
            pstmet.setString(5, employe.getSexeEmploye());
            pstmet.executeUpdate();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    // Méthode pour obtenir tous les employés de la base de données
    public static ArrayList<Employe> getEmployes() {
        ArrayList<Employe> employes = new ArrayList<>();
        try {
            conn = getConnection();
            stm = conn.createStatement();
            rs = stm.executeQuery("SELECT * FROM examen.employe");
            while (rs.next()) {
                Employe employe = new Employe(
                    rs.getInt("Id_employe"),
                    rs.getString("Nom_employe"),
                    rs.getString("Prenom_employe"),
                    rs.getInt("Age"),
                    rs.getString("Poste"),
                    rs.getString("Sexe_employe")
                );
                employes.add(employe);
            }
            conn.close();
            stm.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return employes;
    }

    // Méthode pour obtenir un employé par ID
    public static Employe getEmployeById(int id) {
        Employe employe = null;
        try {
            conn = getConnection();
            pstmet = conn.prepareStatement("SELECT * FROM examen.employe WHERE Id_employe = ?");
            pstmet.setInt(1, id);
            rs = pstmet.executeQuery();
            if (rs.next()) {
                employe = new Employe(
                    rs.getInt("Id_employe"),
                    rs.getString("Nom_employe"),
                    rs.getString("Prenom_employe"),
                    rs.getInt("Age"),
                    rs.getString("Poste"),
                    rs.getString("Sexe_employe")
                );
            }
            conn.close();
            pstmet.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return employe;
    }

    // Méthode pour supprimer un employé de la base de données
    public static void deleteEmploye(Employe employe) {
        try {
            conn = getConnection();
            pstmet = conn.prepareStatement("DELETE FROM examen.employe WHERE Id_employe = ?");
            pstmet.setInt(1, employe.getIdEmploye());
            pstmet.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    // Méthode pour modifier les informations d'un employé
    public static void modifierEmploye(Employe employe) {
        try {
            conn = getConnection();
            pstmet = conn.prepareStatement("UPDATE examen.employe SET Nom_employe=?, Prenom_employe=?, Age=?, Poste=?, Sexe_employe=? WHERE Id_employe=?");
            pstmet.setString(1, employe.getNomEmploye());
            pstmet.setString(2, employe.getPrenomEmploye());
            pstmet.setInt(3, employe.getAge());
            pstmet.setString(4, employe.getPoste());
            pstmet.setString(5, employe.getSexeEmploye());
            pstmet.setInt(6, employe.getIdEmploye());
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