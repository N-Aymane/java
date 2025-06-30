package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

public class Bibliothecaire {
    private int idBibliothecaire;
    private String nom;
    private String identifiant;
    private String motDePasse;

    public Bibliothecaire(String nom, String identifiant, String motDePasse) {
        this.idBibliothecaire = idBibliothecaire;
        this.nom = nom;
        this.identifiant = identifiant;
        this.motDePasse = motDePasse;
    }
    public int getIdBibliothecaire() { return idBibliothecaire; }
    public void setIdBibliothecaire(int idBibliothecaire) { this.idBibliothecaire = idBibliothecaire; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getIdentifiant() { return identifiant; }
    public void setIdentifiant(String identifiant) { this.identifiant = identifiant; }

    public String getMotDePasse() { return motDePasse; }
    public void setMotDePasse(String motDePasse) { this.motDePasse = motDePasse; }


    public ObservableValue<String> nomProperty() {
        return null;
    }

    public ObservableValue<String> identifiantProperty() {
        return new SimpleStringProperty(identifiant);
    }

}
