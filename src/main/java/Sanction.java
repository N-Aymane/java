import java.time.LocalDate;

public class Sanction {
    private int idSanction;
    private Etudiant etudiant;
    private String motif;
    private LocalDate date;

    public Sanction(int idSanction, Etudiant etudiant, String motif, LocalDate date) {
        this.idSanction = idSanction;
        this.etudiant = etudiant;
        this.motif = motif;
        this.date = date;
    }
    public int getIdSanction() { return idSanction; }
    public void setIdSanction(int idSanction) { this.idSanction = idSanction; }

    public Etudiant getEtudiant() { return etudiant; }
    public void setEtudiant(Etudiant etudiant) { this.etudiant = etudiant; }

    public String getMotif() { return motif; }
    public void setMotif(String motif) { this.motif = motif; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }


}
