package Controller;

import Model.Livre;
import dao.EmpruntDao;
import dao.LivreDao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import Model.Etudiant;


import java.io.IOException;
import java.util.List;

public class LivreEtudiantController {

    @FXML private TableView<Livre> livreTable;
    @FXML private TableColumn<Livre, String> titreColumn;
    @FXML private TableColumn<Livre, String> auteurColumn;
    @FXML private TableColumn<Livre, String> editeurColumn;
    @FXML private TableColumn<Livre, Integer> anneeColumn;
    @FXML private TableColumn<Livre, Boolean> disponibleColumn;
    private Etudiant etudiant;


    private final ObservableList<Livre> livres = FXCollections.observableArrayList();

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }


    @FXML
    public void initialize() {
        titreColumn.setCellValueFactory(cellData -> cellData.getValue().titreProperty());
        auteurColumn.setCellValueFactory(cellData -> cellData.getValue().auteurProperty());
        editeurColumn.setCellValueFactory(cellData -> cellData.getValue().editeurProperty());
        anneeColumn.setCellValueFactory(cellData -> cellData.getValue().anneeProperty().asObject());
        disponibleColumn.setCellValueFactory(cellData -> cellData.getValue().disponibleProperty().asObject());

        livres.setAll(LivreDao.getAllLivres());
        livreTable.setItems(livres);
    }

    @FXML
    private void handleEmprunter() {
        Livre livre = livreTable.getSelectionModel().getSelectedItem();
        if (livre == null) {
            showAlert("Erreur", "Veuillez sélectionner un livre à emprunter.");
            return;
        }

        boolean ok = EmpruntDao.enregistrerEmprunt(etudiant.getIdEtudiant(), livre.getIdLivre());
        if (ok) {
            showAlert("Succès", "Livre emprunté avec succès !");
            // Optionnel : réduire nombre d'exemplaires ou rafraîchir liste
        } else {
            showAlert("Erreur", "Échec de l'emprunt.");
        }
    }

    @FXML private TextField searchField;

    @FXML
    private void handleSearch() {
        String keyword = searchField.getText().toLowerCase();
        List<Livre> allLivres = LivreDao.getAllLivres();
        List<Livre> filtered = allLivres.stream()
                .filter(livre -> livre.getTitre().toLowerCase().contains(keyword) ||
                        livre.getAuteur().toLowerCase().contains(keyword) ||
                        livre.getEditeur().toLowerCase().contains(keyword))
                .toList();
        livres.setAll(filtered);
    }



    @FXML
    private void handleRetour() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/EtudiantDashboard.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) livreTable.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Espace Étudiant");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Erreur", "Impossible de revenir au tableau de bord.");
        }
    }


    private void showAlert(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
