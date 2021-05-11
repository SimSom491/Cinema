package hu.alkfejl.controller;

import hu.alkfejl.App;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import org.example.dao.FoglalasDao;
import org.example.dao.MoziDao;
import org.example.dao.TeremDao;
import org.example.model.Foglalas;
import org.example.model.Terem;

import java.net.URL;
import java.util.ResourceBundle;

public class FoglalasokController implements Initializable {

    MoziDao<Foglalas> dao = new FoglalasDao();

    @FXML
    private TableView<Foglalas> foglalasTable;
    @FXML
    private TableColumn<Foglalas, String> foglalo;
    @FXML
    private TableColumn<Foglalas, String> vetites;
    @FXML
    private TableColumn<Foglalas, Integer> szekId;
    @FXML
    private TableColumn<Foglalas, Void> muveletekCloumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refreshTable();
        foglalo.setCellValueFactory(new PropertyValueFactory<>("foglaloNev"));
        vetites.setCellValueFactory(new PropertyValueFactory<>("vetitesId"));
        szekId.setCellValueFactory(new PropertyValueFactory<>("szekId"));
        muveletekCloumn.setCellFactory(param -> new TableCell<>() {
            private final Button editBtn = new Button("Frissít");


            {
                editBtn.setOnAction(event -> {
                    Foglalas t = getTableRow().getItem();
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
                    container.getChildren().addAll(editBtn);
                    container.setSpacing(12.0);
                    setGraphic(container);
                }
            }
        });
    }

    private void editRow(Foglalas t) {
        FXMLLoader fxmlLoader = App.loadFXML("/fxml/edit_foglalas.fxml");
        EditFoglalasController controller = fxmlLoader.getController();
        controller.setFoglalas(t);
    }

    private void refreshTable() {
        foglalasTable.getItems().setAll(dao.listaz());
    }

    @FXML
    public void onExit() {
        Platform.exit();
    }

    public void goToMenu() {
        App.loadFXML("/fxml/main_window.fxml");
    }
}
