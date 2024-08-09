package model;

 
public class Employe {
    private int idEmploye;
    private String nomEmploye;
    private String prenomEmploye;
    private int age;
    private String poste;
    private String sexeEmploye;
//    private String urlProfile;

    // Constructeur par défaut
    public Employe() {
    }

    // Constructeur avec tous les champs
    public Employe(int idEmploye, String nomEmploye, String prenomEmploye, int age, String poste, String sexeEmploye) {
        this.idEmploye = idEmploye;
        this.nomEmploye = nomEmploye;
        this.prenomEmploye = prenomEmploye;
        this.age = age;
        this.poste = poste;
        this.sexeEmploye = sexeEmploye;
//        this.urlProfile = urlProfile;
    }

    // Constructeur sans ID (pour création d'un nouvel employé)
    public Employe(String nomEmploye, String prenomEmploye, int age, String poste, String sexeEmploye) {
        this.nomEmploye = nomEmploye;
        this.prenomEmploye = prenomEmploye;
        this.age = age;
        this.poste = poste;
        this.sexeEmploye = sexeEmploye;
//        this.urlProfile = urlProfile;
    }

    // Getters et Setters
    public int getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(int idEmploye) {
        this.idEmploye = idEmploye;
    }

    public String getNomEmploye() {
        return nomEmploye;
    }

    public void setNomEmploye(String nomEmploye) {
        this.nomEmploye = nomEmploye;
    }

    public String getPrenomEmploye() {
        return prenomEmploye;
    }

    public void setPrenomEmploye(String prenomEmploye) {
        this.prenomEmploye = prenomEmploye;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public String getSexeEmploye() {
        return sexeEmploye;
    }

    public void setSexeEmploye(String sexeEmploye) {
        this.sexeEmploye = sexeEmploye;
    }

//    public String getUrlProfile() {
//        return urlProfile;
//    }
//
//    public void setUrlProfile(String urlProfile) {
//        this.urlProfile = urlProfile;
//    }
}
