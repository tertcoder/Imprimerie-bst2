/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author tertcoder
 */
public class Client {
    private int idClient;
    private String nomClient;
    private String prenomClient;
    private int age;
    private String adresseClient;
    private int numTel;
//    private String urlProfile;
 
    public Client() {
    }

    
    public Client(int idClient, String nomClient, String prenomClient, int age, String adresseClient, int numTel) {
        this.idClient = idClient;
        this.nomClient = nomClient;
        this.prenomClient = prenomClient;
        this.age = age;
        this.adresseClient = adresseClient;
        this.numTel = numTel;
//        this.urlProfile = urlProfile;
    }
    
    public Client(String nomClient, String prenomClient, int age, String adresseClient, int numTel) {
        this.nomClient = nomClient;
        this.prenomClient = prenomClient;
        this.age = age;
        this.adresseClient = adresseClient;
        this.numTel = numTel;
//        this.urlProfile = urlProfile;
    }
 
    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public String getPrenomClient() {
        return prenomClient;
    }

    public void setPrenomClient(String prenomClient) {
        this.prenomClient = prenomClient;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAdresseClient() {
        return adresseClient;
    }

    public void setAdresseClient(String adresseClient) {
        this.adresseClient = adresseClient;
    }

    public int getNumTel() {
        return numTel;
    }

    public void setNumTel(int numTel) {
        this.numTel = numTel;
    }

//    public String getUrlProfile() {
//        return urlProfile;
//    }
//
//    public void setUrlProfile(String urlProfile) {
//        this.urlProfile = urlProfile;
//    }
}