package Controller;

import Model.Etudiant;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class EtudiantDashboardController {

    @FXML private Label nomLabel;
    @FXML private Label prenomLabel;
    @FXML private Label matriculeLabel;
    @FXML private Label filiereLabel;
    @FXML private Label niveauLabel;
    @FXML private TableView<?> empruntTable;


    private Etudiant etudiantConnecte;

    public void setEtudiantConnecte(Etudiant etudiant) {
        this.etudiantConnecte = etudiant;
        afficherInfosEtudiant();
    }

    private void afficherInfosEtudiant() {
        if (etudiantConnecte != null) {
            nomLabel.setText(etudiantConnecte.getNom());
            prenomLabel.setText(etudiantConnecte.getPrenom());
            matriculeLabel.setText(etudiantConnecte.getMatricule());
            filiereLabel.setText(etudiantConnecte.getFiliere());
            niveauLabel.setText(etudiantConnecte.getNiveau());
        }
    }

    @FXML
    private void handleVoirLivres(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LivreEtudiantView.fxml"));
            Parent root = loader.load();

            LivreEtudiantController  controller = loader.getController();
             controller.setEtudiant(etudiantConnecte);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Livres à emprunter");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Erreur", "Impossible d’ouvrir la liste des livres.");
        }
    }

    @FXML
    private void handleMesEmprunts() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MesEmpruntsView.fxml"));
            Parent root = loader.load();

            MesEmpruntsController controller = loader.getController();
            controller.setEtudiant(etudiantConnecte);

            Stage stage = new Stage();
            stage.setTitle("Mes Emprunts");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Erreur", "Impossible d'ouvrir la vue des emprunts.");
        }
    }


    @FXML
    private void handleRetour() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/EtudiantDashboard.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) empruntTable.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Dashboard Étudiant");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void toggleFullScreen() {
        Stage stage = (Stage) nomLabel.getScene().getWindow(); // Utilise n'importe quel composant de la scène
        stage.setFullScreen(!stage.isFullScreen());
    }

    @FXML
    private void handleLogout() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LoginView.fxml")); // vérifie le bon chemin
            Parent root = loader.load();

            Stage stage = (Stage) nomLabel.getScene().getWindow(); // remplace la scène actuelle
            stage.setScene(new Scene(root));
            stage.setTitle("Connexion");
            stage.setResizable(false);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Erreur", "Impossible de retourner à la page de connexion.");
        }
    }





    private void showAlert(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
