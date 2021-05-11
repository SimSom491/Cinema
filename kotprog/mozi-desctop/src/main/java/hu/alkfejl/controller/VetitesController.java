package hu.alkfejl.controller;

import hu.alkfejl.App;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import org.example.dao.FilmDao;
import org.example.dao.MoziDao;
import org.example.dao.VetitesDao;
import org.example.model.Film;
import org.example.model.Vetites;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class VetitesController implements Initializable {


    @FXML
    public TableColumn<Vetites, Integer> ora;
    MoziDao<Vetites> dao = new VetitesDao();
    MoziDao<Film> fdao = new FilmDao();
    @FXML
    private TableView<Vetites> teremTable;
    @FXML
    private TableColumn<Vetites, Integer> teremSzam;
    @FXML
    private TableColumn<Vetites, String> filmCim;
    @FXML
    private TableColumn<Vetites, String> idoPont;
    @FXML
    private TableColumn<Vetites, Void> muveletekCloumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refreshTable();

        teremSzam.setCellValueFactory(new PropertyValueFactory<>("terem"));
        ora.setCellValueFactory(new PropertyValueFactory<>("ora"));

        //StringProperty uj=new SimpleStringProperty(this,"cim")
        //uj=fdao.keres(new PropertyValueFactory<String,Integer>("filmId"))
        //filmCim.setCellFactory(fdao.keres(new PropertyValueFactory<>("filmId").getProperty().length()));
        //fdao.keres(teremTable.getItems().get(i).getFilmId());
        //filmCim.setCellValueFactory();

        idoPont.setCellValueFactory(new PropertyValueFactory<>("idopont"));
        //filmCim.setCellValueFactory(new PropertyValueFactory<>("filmId"));
        //filmCim.setCellFactory(fdao.keres(new PropertyValueFactory<>("filmId"))
        muveletekCloumn.setCellFactory(param -> new TableCell<>() {
            private final Button deleteBtn = new Button("Törlés");
            private final Button editBtn = new Button("Frissít");

            {
                deleteBtn.setOnAction(event -> {
                    Vetites t = getTableRow().getItem();
                    Alert al = new Alert(Alert.AlertType.CONFIRMATION, "Biztosan törli a vetítést?", ButtonType.YES, ButtonType.NO);
                    al.setHeaderText("Megerősítés");
                    al.showAndWait().ifPresent(buttonType -> {
                        if (buttonType.equals(ButtonType.YES)) {
                            dao.delete(t.getId());
                        }
                    });

                    refreshTable();
                });
                editBtn.setOnAction(event -> {
                    Vetites t = getTableRow().getItem();
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

    private void editRow(Vetites t) {
        FXMLLoader fxmlLoader = App.loadFXML("/fxml/add_edit_vetites.fxml");
        AddEditVetitesController controller = fxmlLoader.getController();
        controller.setTerem(t);
    }

    public void addRow() {
        FXMLLoader fxmlLoader = App.loadFXML("/fxml/add_edit_vetites.fxml");
        AddEditVetitesController controller = fxmlLoader.getController();
        Vetites vet = dao.listaz().get(0);
        vet.setIdopont(LocalDate.now().plusMonths(1));
        vet.setId(0);
        controller.setTerem(vet);
    }

    private void refreshTable() {
        teremTable.getItems().setAll(dao.listaz());

        for (int i = 0; i < dao.listaz().size(); i++) {
            //teremTable.getItems().get(i).setFilm(dao.listaz().get(i).getFilm());
            //   int finalI = i;
            filmCim.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Vetites, String>, ObservableValue<String>>() {
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Vetites, String> c) {
                    // p.getValue() returns the Contact instance for a particular TableView row
                    return fdao.keres(c.getValue().getFilm().getId()).cimProperty();
                }
            });
            //    filmCim.setCellFactory(fdao.keres(teremTable.getItems().get(finalI).getFilm().getId()).toString());
            //(c -> new SimpleStringProperty(fdao.keres(teremTable.getItems().get(finalI).getFilm().getId()).toString()));
        }

    }

    @FXML
    public void onExit() {
        Platform.exit();
    }

    public void goToMenu() {
        App.loadFXML("/fxml/main_window.fxml");
    }
}
