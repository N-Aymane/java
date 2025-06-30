
import javafx.beans.property.*;

public class Etudiant {

    private final IntegerProperty idEtudiant;
    private final StringProperty nom;
    private final StringProperty prenom;
    private final StringProperty matricule;
    private final StringProperty filiere;
    private final StringProperty niveau;



    public Etudiant(int idEtudiant, String nom, String prenom, String matricule, String filiere, String niveau) {
        this.idEtudiant = new SimpleIntegerProperty(idEtudiant);
        this.nom = new SimpleStringProperty(nom);
        this.prenom = new SimpleStringProperty(prenom);
        this.matricule = new SimpleStringProperty(matricule);
        this.filiere = new SimpleStringProperty(filiere);
        this.niveau = new SimpleStringProperty(niveau);
    }



    public IntegerProperty idEtudiantProperty() { return idEtudiant; }
    public StringProperty nomProperty() { return nom; }
    public StringProperty prenomProperty() { return prenom; }
    public StringProperty matriculeProperty() { return matricule; }
    public StringProperty filiereProperty() { return filiere; }
    public StringProperty niveauProperty() { return niveau; }

    public int getIdEtudiant() { return idEtudiant.get(); }
    public String getNom() { return nom.get(); }
    public String getPrenom() { return prenom.get(); }
    public String getMatricule() { return matricule.get(); }
    public String getFiliere() { return filiere.get(); }
    public String getNiveau() { return niveau.get(); }


    public void setIdEtudiant(int id) { this.idEtudiant.set(id); }
    public void setNom(String n) { this.nom.set(n); }
    public void setPrenom(String p) { this.prenom.set(p); }
    public void setMatricule(String m) { this.matricule.set(m); }
    public void setFiliere(String f) { this.filiere.set(f); }
    public void setNiveau(String n) { this.niveau.set(n); }
}
