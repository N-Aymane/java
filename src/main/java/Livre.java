import java.util.ArrayList;
import java.util.List;

public class Livre {
    private int idLivre;
    private String titre;
    private String auteur;
    private String editeur;
    private int annee;
    private int nombreExemplaires;
    private boolean disponible;

    private static List<Livre> livres = new ArrayList<>();

    public Livre(int idLivre, String titre, String auteur, String editeur, int annee, int nombreExemplaires) {
        this.idLivre = idLivre;
        this.titre = titre;
        this.auteur = auteur;
        this.editeur = editeur;
        this.annee = annee;
        this.nombreExemplaires = nombreExemplaires;
        this.disponible = true;
    }

    public int getIdLivre() { return idLivre; }
    public void setIdLivre(int idLivre) { this.idLivre = idLivre; }

    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }

    public String getAuteur() { return auteur; }
    public void setAuteur(String auteur) { this.auteur = auteur; }

    public String getEditeur() { return editeur; }
    public void setEditeur(String editeur) { this.editeur = editeur; }

    public int getAnnee() { return annee; }
    public void setAnnee(int annee) { this.annee = annee; }

    public int getNombreExemplaires() { return nombreExemplaires; }
    public void setNombreExemplaires(int nombreExemplaires) { this.nombreExemplaires = nombreExemplaires; }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public static void ajouterLivre(Livre livre) {
        livres.add(livre);
        System.out.println("✅ Livre ajouté : " + livre);
    }

    public static boolean modifierLivre(int idLivre, Livre nouveauLivre) {
        for (int i = 0; i < livres.size(); i++) {
            if (livres.get(i).getIdLivre() == idLivre) {
                livres.set(i, nouveauLivre);
                System.out.println("✏️ Livre modifié.");
                return true;
            }
        }
        System.out.println("❌ Livre non trouvé.");
        return false;
    }

    public static boolean supprimerLivre(int idLivre) {
        for (int i = 0; i < livres.size(); i++) {
            if (livres.get(i).getIdLivre() == idLivre) {
                System.out.println("🗑️ Livre supprimé : " + livres.get(i));
                livres.remove(i);
                return true;
            }
        }
        System.out.println("❌ Livre non trouvé.");
        return false;
    }

    public static void afficherLivres() {
        if (livres.isEmpty()) {
            System.out.println("📂 Aucun livre dans la bibliothèque.");
        } else {
            System.out.println("📚 Liste des livres :");
            for (Livre livre : livres) {
                System.out.println(livre);
            }
        }
    }

    public static Livre rechercherLivre(String motCle) {
        for (Livre livre : livres) {
            if (livre.getTitre().equalsIgnoreCase(motCle) ||
                    livre.getAuteur().equalsIgnoreCase(motCle)) {
                return livre;
            }
        }
        return null;
    }

}
