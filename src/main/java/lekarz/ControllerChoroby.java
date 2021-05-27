package lekarz;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import obiekty.Lista_chorob;
import org.hibernate.Session;
import sample.HibernateUtil;
import services.Lista_ChorobService;
import services.WyposazenieService;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerChoroby implements Initializable {
    @FXML
    Button buttonLogin, exit_button, minimalize_button;
    @FXML
    TableView table;
    @FXML
    TableColumn kodChoroby, nazwaChoroby;

    private ObservableList<Lista_chorob> obsList;
    private Lista_ChorobService service;



    public void exit(ActionEvent actionEvent) {
        Stage stage = (Stage) exit_button.getScene().getWindow();
        stage.close();
    }

    public void minimize(ActionEvent actionEvent) {
        Stage stage = (Stage) exit_button.getScene().getWindow();
        stage.setIconified(true);
    }

    //PRZYCISKI PRZEŁĄCZENIA NA INNY EKRAN

    public void przejdz(ActionEvent actionEvent, String s1) throws IOException {
        URL url = getClass().getClassLoader().getResource(s1);
        Parent parent = FXMLLoader.load(url);
        Scene scene = new Scene(parent);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    //metoda na przycisk wyloguj ktora otwiera scene sample.fxml
    public void wyloguj(ActionEvent actionEvent) throws IOException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.close();
        przejdz(actionEvent,"sample.fxml");
    }

    public void wizyty(ActionEvent actionEvent) throws IOException { przejdz(actionEvent, "lekarz/wizyty.fxml"); }

    public void pacjenci(ActionEvent actionEvent) throws IOException { przejdz(actionEvent,"lekarz/pacjenci.fxml"); }

    public void kartaPacjenta(ActionEvent actionEvent) throws IOException { przejdz(actionEvent,"lekarz/kartaPacjenta.fxml"); }

    public void kalendarz(ActionEvent actionEvent) throws IOException { przejdz(actionEvent, "lekarz/kalendarz.fxml");}

    public void setService(Lista_ChorobService service) {
        this.service = service;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setService(service);

        this.setService(new Lista_ChorobService());
        obsList = FXCollections.observableArrayList(service.findAll());
        table.setItems(obsList);

        kodChoroby.setCellValueFactory(new PropertyValueFactory<>("kod_choroby"));
        nazwaChoroby.setCellValueFactory(new PropertyValueFactory<>("nazwa_choroby"));

    }
}
