package Model;

import javafx.beans.property.*;

public class Livre {
    private final IntegerProperty idLivre;
    private final StringProperty titre;
    private final StringProperty auteur;
    private final StringProperty editeur;
    private final IntegerProperty annee;
    private final IntegerProperty nombreExemplaires;
    private final BooleanProperty disponible;

    public Livre(int idLivre, String titre, String auteur, String editeur, int annee, int nombreExemplaires) {
        this.idLivre = new SimpleIntegerProperty(idLivre);
        this.titre = new SimpleStringProperty(titre);
        this.auteur = new SimpleStringProperty(auteur);
        this.editeur = new SimpleStringProperty(editeur);
        this.annee = new SimpleIntegerProperty(annee);
        this.nombreExemplaires = new SimpleIntegerProperty(nombreExemplaires);
        this.disponible = new SimpleBooleanProperty(true);
    }

    // Getters et setters avec propriétés JavaFX

    public int getIdLivre() { return idLivre.get(); }
    public void setIdLivre(int id) { this.idLivre.set(id); }
    public IntegerProperty idLivreProperty() { return idLivre; }

    public String getTitre() { return titre.get(); }
    public void setTitre(String titre) { this.titre.set(titre); }
    public StringProperty titreProperty() { return titre; }

    public String getAuteur() { return auteur.get(); }
    public void setAuteur(String auteur) { this.auteur.set(auteur); }
    public StringProperty auteurProperty() { return auteur; }

    public String getEditeur() { return editeur.get(); }
    public void setEditeur(String editeur) { this.editeur.set(editeur); }
    public StringProperty editeurProperty() { return editeur; }

    public int getAnnee() { return annee.get(); }
    public void setAnnee(int annee) { this.annee.set(annee); }
    public IntegerProperty anneeProperty() { return annee; }

    public int getNombreExemplaires() { return nombreExemplaires.get(); }
    public void setNombreExemplaires(int nb) { this.nombreExemplaires.set(nb); }
    public IntegerProperty nombreExemplairesProperty() { return nombreExemplaires; }

    public boolean isDisponible() { return disponible.get(); }
    public void setDisponible(boolean dispo) { this.disponible.set(dispo); }
    public BooleanProperty disponibleProperty() { return disponible; }
}
