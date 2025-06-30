package Model;

import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;

import java.time.LocalDate;

public class Emprunt {
    private int idEmprunt;
    private Livre livre;
    private Etudiant etudiant;
    private ObjectProperty<LocalDate> dateEmprunt;
    private ObjectProperty<LocalDate> dateRetourPrevu;
    private ObjectProperty<LocalDate> dateRetourEffectif;

    public Emprunt(Livre livre, Etudiant etudiant, LocalDate dateEmprunt, LocalDate dateRetourPrevu) {
        this.livre = livre;
        this.etudiant = etudiant;
        this.dateEmprunt = new SimpleObjectProperty<>(dateEmprunt);
        this.dateRetourPrevu = new SimpleObjectProperty<>(dateRetourPrevu);
        this.dateRetourEffectif = new SimpleObjectProperty<>(null);
    }

    public int getIdEmprunt() {
        return idEmprunt;
    }

    public void setIdEmprunt(int idEmprunt) {
        this.idEmprunt = idEmprunt;
    }

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public LocalDate getDateEmprunt() {
        return dateEmprunt.get();
    }

    public void setDateEmprunt(LocalDate dateEmprunt) {
        this.dateEmprunt.set(dateEmprunt);
    }

    public LocalDate getDateRetourPrevu() {
        return dateRetourPrevu.get();
    }

    public void setDateRetourPrevu(LocalDate dateRetourPrevu) {
        this.dateRetourPrevu.set(dateRetourPrevu);
    }

    public LocalDate getDateRetourEffectif() {
        return dateRetourEffectif.get();
    }

    public void setDateRetourEffectif(LocalDate dateRetourEffectif) {
        this.dateRetourEffectif.set(dateRetourEffectif);
    }

    public ObservableValue<String> titreProperty() {
        return new SimpleStringProperty(livre.getTitre());
    }

    public ObservableValue<String> auteurProperty() {
        return new SimpleStringProperty(livre.getAuteur());
    }

    public ObjectProperty<LocalDate> dateEmpruntProperty() {
        return dateEmprunt;
    }

    public ObjectProperty<LocalDate> dateRetourPrevuProperty() {
        return dateRetourPrevu;
    }

    public StringProperty statutProperty() {
        String statut = (dateRetourEffectif.get() == null) ? "En cours" : "Retourné";
        return new SimpleStringProperty(statut);
    }
}
