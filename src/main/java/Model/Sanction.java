package Model;

import javafx.beans.binding.BooleanExpression;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;

import java.time.LocalDate;

public class Sanction {
    private int idSanction;
    private Etudiant etudiant;
    private StringProperty motif;
    private ObjectProperty<LocalDate> date;

    public Sanction(Etudiant etudiant, String motif, LocalDate date) {
        this.etudiant = etudiant;
        this.motif = new SimpleStringProperty(motif);
        this.date = new SimpleObjectProperty<>(date);
    }

    public int getIdSanction() {
        return idSanction;
    }

    public void setIdSanction(int idSanction) {
        this.idSanction = idSanction;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public String getMotif() {
        return motif.get();
    }

    public void setMotif(String motif) {
        this.motif.set(motif);
    }

    public StringProperty motifProperty() {
        return motif;
    }

    public LocalDate getDate() {
        return date.get();
    }

    public void setDate(LocalDate date) {
        this.date.set(date);
    }

    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }

    public ObservableValue<String> etudiantProperty() {
        return new SimpleStringProperty(etudiant.getNomComplet());
    }

}
