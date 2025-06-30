package Controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Model.Livre;
import Model.Emprunt;
import dao.LivreDao;
import dao.EmpruntDao;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatter;
import javafx.beans.property.SimpleStringProperty;


public class BibliothecaireController {

    @FXML private TextField titreField;
    @FXML private TextField auteurField;
    @FXML private TextField editeurField;
    @FXML private TextField anneeField;
    @FXML private TextField nombreExemplairesField;

    @FXML private TableView<Livre> livreTable;
    @FXML private TableColumn<Livre, String> titreColumn;
    @FXML private TableColumn<Livre, String> auteurColumn;
    @FXML private TableColumn<Livre, String> editeurColumn;
    @FXML private TableColumn<Livre, Integer> anneeColumn;
    @FXML private TableColumn<Livre, Integer> nombreExemplairesColumn;

    @FXML
    private TableView<Emprunt> empruntTable;
    @FXML private TableColumn<Emprunt, String> titreEmpruntColumn;
    @FXML private TableColumn<Emprunt, String> emprunteurColumn;
    @FXML private TableColumn<Emprunt, String> dateEmpruntColumn;
    @FXML private TableColumn<Emprunt, String> dateRetourColumn;
    @FXML private TableColumn<Emprunt, String> statutEmpruntColumn;

    private final ObservableList<Livre> livres = FXCollections.observableArrayList();
    private final ObservableList<Emprunt> emprunts = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        titreColumn.setCellValueFactory(cellData -> cellData.getValue().titreProperty());
        auteurColumn.setCellValueFactory(cellData -> cellData.getValue().auteurProperty());
        editeurColumn.setCellValueFactory(cellData -> cellData.getValue().editeurProperty());
        anneeColumn.setCellValueFactory(cellData -> cellData.getValue().anneeProperty().asObject());
        nombreExemplairesColumn.setCellValueFactory(cellData -> cellData.getValue().nombreExemplairesProperty().asObject());

        livres.setAll(LivreDao.getAllLivres());
        livreTable.setItems(livres);


        if (empruntTable != null) {
            titreEmpruntColumn.setCellValueFactory(cellData ->
                    new SimpleStringProperty(cellData.getValue().getLivre().getTitre())
            );

            emprunteurColumn.setCellValueFactory(cellData ->
                    new SimpleStringProperty(cellData.getValue().getEtudiant().getNom())
            );

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            dateEmpruntColumn.setCellValueFactory(cellData ->
                    new SimpleStringProperty(cellData.getValue().getDateEmprunt().format(formatter))
            );

            dateRetourColumn.setCellValueFactory(cellData ->
                    new SimpleStringProperty(cellData.getValue().getDateRetourPrevu().format(formatter))
            );

            statutEmpruntColumn.setCellValueFactory(cellData ->
                    new SimpleStringProperty(
                            cellData.getValue().getDateRetourEffectif() == null ? "En cours" : "Retourné"
                    )
            );

            emprunts.setAll(EmpruntDao.getAllEmprunts());
            empruntTable.setItems(emprunts);
        }


    }

    @FXML
    private void ajouterLivre() {
        try {
            Livre livre = new Livre(
                    0,
                    titreField.getText(),
                    auteurField.getText(),
                    editeurField.getText(),
                    Integer.parseInt(anneeField.getText()),
                    Integer.parseInt(nombreExemplairesField.getText())
            );

            if (LivreDao.ajouterLivre(livre)) {
                livres.setAll(LivreDao.getAllLivres());
                clearFields();
                showAlert("Succès", "Livre ajouté.");
            } else {
                showAlert("Erreur", "Impossible d'ajouter ce livre.");
            }

        } catch (NumberFormatException e) {
            showAlert("Erreur", "Année et nombre d'exemplaires doivent être des entiers.");
        }
    }

    @FXML
    private void modifierLivre() {
        Livre selection = livreTable.getSelectionModel().getSelectedItem();
        if (selection != null) {
            try {
                selection.setTitre(titreField.getText());
                selection.setAuteur(auteurField.getText());
                selection.setEditeur(editeurField.getText());
                selection.setAnnee(Integer.parseInt(anneeField.getText()));
                selection.setNombreExemplaires(Integer.parseInt(nombreExemplairesField.getText()));

                if (LivreDao.modifierLivre(selection)) {
                    livres.setAll(LivreDao.getAllLivres());
                    clearFields();
                    showAlert("Modifié", "Livre mis à jour.");
                } else {
                    showAlert("Erreur", "Échec de la mise à jour.");
                }

            } catch (NumberFormatException e) {
                showAlert("Erreur", "Année et nombre d'exemplaires doivent être des entiers.");
            }
        } else {
            showAlert("Aucun livre sélectionné", "Sélectionnez un livre à modifier.");
        }
    }

    @FXML
    private void supprimerLivre() {
        Livre selection = livreTable.getSelectionModel().getSelectedItem();
        if (selection != null) {
            if (LivreDao.supprimerLivre(selection.getIdLivre())) {
                livres.setAll(LivreDao.getAllLivres());
                clearFields();
                showAlert("Supprimé", "Livre supprimé.");
            } else {
                showAlert("Erreur", "Impossible de supprimer ce livre.");
            }
        } else {
            showAlert("Aucun livre sélectionné", "Sélectionnez un livre à supprimer.");
        }
    }

    @FXML
    private void handleLogout() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LoginView.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) livreTable.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Connexion");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Erreur", "Impossible de revenir à la page de connexion.");
        }
    }

    @FXML
    private void toggleFullScreen() {
        Stage stage = (Stage) titreField.getScene().getWindow();
        stage.setFullScreen(!stage.isFullScreen());
    }


    private void clearFields() {
        titreField.clear();
        auteurField.clear();
        editeurField.clear();
        anneeField.clear();
        nombreExemplairesField.clear();
    }

    private void showAlert(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
