package hu.alkfejl.controller;

import hu.alkfejl.App;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import org.example.dao.FilmDao;
import org.example.model.Film;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class FilmekController implements Initializable {

    FilmDao dao = new FilmDao();
    private List<Film> osszes;
    @FXML
    private TableView<Film> filmTable;
    @FXML
    private TableColumn<Film, String> cim;
    @FXML
    private TableColumn<Film, Integer> hossz;
    @FXML
    private TableColumn<Film, Integer> kor;
    @FXML
    private TableColumn<Film, String> rendez;
    @FXML
    private TableColumn<Film, String> szereplo;
    @FXML
    private TableColumn<Film, Void> muveletek;

    @FXML
    public void cimKeres(){
        List<Film> filtered =osszes.stream().filter(film -> film.getCim().contains(cimkeres.getText())&&film.getSzereplok().contains(szereplokeres.getText())).collect(Collectors.toList());
        filmTable.getItems().setAll(filtered);
    }

    @FXML
    private TextField cimkeres;
    @FXML
    private TextField szereplokeres;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refreshTable();
        cim.setCellValueFactory(new PropertyValueFactory<>("cim"));
        hossz.setCellValueFactory(new PropertyValueFactory<>("hossz"));
        kor.setCellValueFactory(new PropertyValueFactory<>("korhatar"));
        rendez.setCellValueFactory(new PropertyValueFactory<>("rendezo"));
        szereplo.setCellValueFactory(new PropertyValueFactory<>("szereplok"));

        muveletek.setCellFactory(param -> new TableCell<>() {
            private final Button deleteBtn = new Button("Törlés");
            private final Button editBtn = new Button("Frissít");

            {
                deleteBtn.setOnAction(event -> {
                    Film t = getTableRow().getItem();
                    Alert al = new Alert(Alert.AlertType.CONFIRMATION, "Biztosan törli a filmet?", ButtonType.YES, ButtonType.NO);
                    al.setHeaderText("Megerősítés");
                    al.showAndWait().ifPresent(buttonType -> {
                        if (buttonType.equals(ButtonType.YES)) {
                            dao.delete(t.getId());
                        }
                    });

                    refreshTable();
                });
                editBtn.setOnAction(event -> {
                    Film t = getTableRow().getItem();
                    editRow(t);
                    refreshTable();
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    HBox container = new HBox();
                    container.getChildren().addAll(editBtn, deleteBtn);
                    container.setSpacing(12.0);
                    setGraphic(container);
                }
            }
        });
    }

    @FXML
    public void onExit() {
        Platform.exit();
    }

    private void editRow(Film t) {
        FXMLLoader fxmlLoader = App.loadFXML("/fxml/update_film_window.fxml");
        AddEditFilmController controller = fxmlLoader.getController();
        controller.setFilm(t);
    }

    public void goToMenu() {
        App.loadFXML("/fxml/main_window.fxml");
    }

    public void addRow() {
        FXMLLoader fxmlLoader = App.loadFXML("/fxml/update_film_window.fxml");
        AddEditFilmController controller = fxmlLoader.getController();
        controller.setFilm(new Film());
    }

    private void refreshTable() {
        osszes= dao.listaz();
        filmTable.getItems().setAll(osszes);
    }
}
