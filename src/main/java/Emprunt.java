
import java.time.LocalDate;

public class Emprunt {
    private int idEmprunt;
    private Livre livre;
    private Etudiant etudiant;
    private LocalDate dateEmprunt;
    private LocalDate dateRetourPrevu;
    private LocalDate dateRetourEffectif;

    public Emprunt(int idEmprunt, Livre livre, Etudiant etudiant, LocalDate dateEmprunt, LocalDate dateRetourPrevu) {
        this.idEmprunt = idEmprunt;
        this.livre = livre;
        this.etudiant = etudiant;
        this.dateEmprunt = dateEmprunt;
        this.dateRetourPrevu = dateRetourPrevu;
        this.dateRetourEffectif = null;
        livre.setDisponible(false);
    }

    public void retourner() {
        if (dateRetourEffectif != null) {
            System.out.println("Ce livre a déjà été retourné le " + dateRetourEffectif);
            return;
        }
        this.dateRetourEffectif = LocalDate.now();
        livre.setDisponible(true);
        System.out.println("Livre retourné avec succès.");
    }

    public boolean estActif() {
        return dateRetourEffectif == null;
    }

    public int getIdEmprunt() {
        return idEmprunt;
    }

    public Livre getLivre() {
        return livre;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public LocalDate getDateEmprunt() {
        return dateEmprunt;
    }

    public LocalDate getDateRetourPrevu() {
        return dateRetourPrevu;
    }

    public LocalDate getDateRetourEffectif() {
        return dateRetourEffectif;
    }
}
