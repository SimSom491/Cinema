package hu.alkfejl.controller;

import hu.alkfejl.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import org.example.dao.FilmDao;
import org.example.dao.TeremDao;
import org.example.dao.VetitesDao;
import org.example.model.Film;
import org.example.model.Terem;
import org.example.model.Vetites;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class AddEditVetitesController implements Initializable {
    @FXML
    public Button go;

    private final VetitesDao dao = new VetitesDao();
    private final FilmDao fdao = new FilmDao();
    private final TeremDao tdao = new TeremDao();


    @FXML
    private ChoiceBox<Integer> oraa;
    @FXML
    private ChoiceBox<Terem> terem;
    @FXML
    private ChoiceBox<Film> film;
    @FXML
    private DatePicker data;


    private Vetites vetites;


    public void setTerem(Vetites t) {
        vetites = t;

        oraa.valueProperty().bindBidirectional(this.vetites.oraProperty().asObject());
        terem.valueProperty().bindBidirectional(this.vetites.teremProperty());
        //StringConverter<? extends Number> converter =  new IntegerStringConverter();
        //Bindings.bindBidirectional(film.valueProperty(),vetites.filmIdProperty(),(StringConverter<Number>) converter);
        //film.valueProperty().bindBidirectional(vetites.filmIdProperty(),new NumberStringConverter());
        film.valueProperty().bindBidirectional(this.vetites.filmProperty());

        data.valueProperty().bindBidirectional(vetites.idopontProperty());

    }

    public void cancel() {
        App.loadFXML("/fxml/Vetitesek_window.fxml");

    }

    public void save() {

        vetites = dao.hozzaad(vetites);
        App.loadFXML("/fxml/Vetitesek_window.fxml");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        film.getItems().setAll(fdao.listaz());
        terem.getItems().setAll(tdao.listaz());
        ArrayList<Integer> orak = new ArrayList<>();
        for (int i = 10; i <= 22; i++) {
            orak.add(i);
        }
        oraa.getItems().setAll(orak);
    }
}
