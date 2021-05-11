package hu.alkfejl.controller;

import hu.alkfejl.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;
import org.example.dao.FilmDao;
import org.example.model.Film;

import java.net.URL;
import java.util.ResourceBundle;


public class AddEditFilmController implements Initializable {
    @FXML
    public Button go;
    private final FilmDao dao = new FilmDao();

    @FXML
    private TextField hossz;
    @FXML
    private TextField korhatar;
    @FXML
    private TextField rendezo;
    @FXML
    private TextField szereplok;
    @FXML
    private TextField leiras;
    @FXML
    private TextField kep;
    @FXML
    private TextField klip;
    @FXML
    private TextField cim;
    private Film film;


    public void setFilm(Film f) {
        film = f;

        hossz.textProperty().bindBidirectional(film.hosszProperty(), new NumberStringConverter());
        korhatar.textProperty().bindBidirectional(film.korhatarProperty(), new NumberStringConverter());
        rendezo.textProperty().bindBidirectional(film.rendezoProperty());
        szereplok.textProperty().bindBidirectional(film.szereplokProperty());
        leiras.textProperty().bindBidirectional(film.leirasProperty());
        kep.textProperty().bindBidirectional(film.kepProperty());
        klip.textProperty().bindBidirectional(film.klipProperty());
        cim.textProperty().bindBidirectional(film.cimProperty());
    }

    public void cancel() {
        App.loadFXML("/fxml/filmek_window.fxml");

    }

    public void save() {
        film = dao.hozzaad(film);
        App.loadFXML("/fxml/filmek_window.fxml");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        go.disableProperty().bind(cim.textProperty().isEmpty().or(korhatar.textProperty().isEmpty()).or(hossz.textProperty().isEmpty()).or(rendezo.textProperty().isEmpty()).or(szereplok.textProperty().isEmpty()));
    }
}
