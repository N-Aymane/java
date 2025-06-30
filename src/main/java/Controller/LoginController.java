package Controller;

import Model.Bibliothecaire;
import Model.Etudiant;
import dao.BibliothecaireDao;
import dao.EtudiantDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField nomField;

    @FXML
    private PasswordField motDePasseField;

    @FXML
    private Button loginButton;

    @FXML private ComboBox<String> roleComboBox;

    @FXML
    private void handleLogin(ActionEvent event) {
        String nom = nomField.getText().trim();
        String motDePasse = motDePasseField.getText();
        String role = roleComboBox.getValue();

        if (nom.isBlank() || motDePasse.isBlank() || role == null) {
            showAlert(Alert.AlertType.WARNING, "Champs manquants", "Veuillez remplir tous les champs et sélectionner un rôle.");
            return;
        }

        if (role.equals("Étudiant")) {
            Etudiant etudiant = EtudiantDao.verifierIdentifiants(nom, motDePasse);
            if (etudiant != null) {
                chargerVueEtudiant(etudiant);
            } else {
                showAlert(Alert.AlertType.ERROR, "Connexion échouée", "Nom ou mot de passe incorrect pour l'étudiant.");
            }

        } else if (role.equals("Bibliothécaire")) {
            Bibliothecaire biblio = BibliothecaireDao.verifierIdentifiants(nom, motDePasse);
            if (biblio != null) {
                chargerVueBibliothecaire(biblio);
            } else {
                showAlert(Alert.AlertType.ERROR, "Connexion échouée", "Identifiants bibliothécaire invalides.");
            }
        }
    }


    private void chargerVueEtudiant(Etudiant etudiant) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/EtudiantDashboard.fxml"));
            Parent root = loader.load();

            EtudiantDashboardController controller = loader.getController();
            controller.setEtudiantConnecte(etudiant);

            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Espace Étudiant");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erreur", "Impossible de charger l'interface étudiante.");
        }
    }

    private void chargerVueBibliothecaire(Bibliothecaire biblio) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/BibliothecaireDashboard.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Espace Bibliothécaire");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erreur", "Impossible de charger l'interface bibliothécaire.");
        }
    }




    private void showAlert(Alert.AlertType type, String titre, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
