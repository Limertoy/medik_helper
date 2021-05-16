package dyrektor;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import obiekty.Wyposazenie;
import org.hibernate.Session;
import sample.HibernateUtil;
import services.WyposazenieService;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class ControllerDyrektorZamawianie implements Initializable {
    @FXML
    private TextField how_many;
    @FXML
    Button buttonLogin, exit_button, minimalize_button;
    @FXML
    private TableView wyposazenia;
    @FXML
    private TableColumn nazwaWyposazenie, iloscWyposazenie;
    @FXML
    private ChoiceBox selectList;

    private WyposazenieService service;

    private ObservableList<Wyposazenie> obsList;



    public void exit(ActionEvent actionEvent) {
        Stage stage = (Stage) exit_button.getScene().getWindow();
        stage.close();
    }

    public void minimize(ActionEvent actionEvent) {
        Stage stage = (Stage) exit_button.getScene().getWindow();
        stage.setIconified(true);
    }

    public void wyloguj(ActionEvent actionEvent) throws IOException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.close();
        URL url = Paths.get("./src/main/java/sample/sample.fxml").toUri().toURL();
        Parent loginParent = FXMLLoader.load(url);
        Scene loginScene = new Scene(loginParent);

        //this line gets stage info
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(loginScene);
        window.show();
    }


    public void personel(ActionEvent actionEvent) throws IOException {
        URL url = Paths.get("./src/main/java/dyrektor/pracownicy.fxml").toUri().toURL();
        Parent kalendarzParent = FXMLLoader.load(url);
        Scene kalendarzScene = new Scene(kalendarzParent);

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(kalendarzScene);
        window.show();
    }

    public void wydruki(ActionEvent actionEvent) throws IOException {
        URL url = Paths.get("./src/main/java/dyrektor/raporty.fxml").toUri().toURL();
        Parent pacjenciParent = FXMLLoader.load(url);
        Scene pacjenciScene = new Scene(pacjenciParent);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(pacjenciScene);
        window.show();
    }

    public void wyposazenie(ActionEvent actionEvent) throws IOException {
        URL url = Paths.get("./src/main/java/dyrektor/dyrektorZarzadzanie.fxml").toUri().toURL();
        Parent pacjenciParent = FXMLLoader.load(url);
        Scene pacjenciScene = new Scene(pacjenciParent);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(pacjenciScene);
        window.show();
    }

    public void setService(WyposazenieService service) {
        this.service = service;
    }


    public void initializeTableColumnCell() {

        this.setService(new WyposazenieService());
        obsList = FXCollections.observableArrayList(service.findAll());
        wyposazenia.setItems(obsList);

        nazwaWyposazenie.setCellValueFactory(new PropertyValueFactory<>("nazwa_wyposazenia"));
        iloscWyposazenie.setCellValueFactory(new PropertyValueFactory<>("ilosc_pozostalych"));

        selectList.setItems(obsList);

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        initializeTableColumnCell();

    }

    public void zamow(ActionEvent actionEvent) {

       int ile = Integer.parseInt(how_many.getText());
       Wyposazenie obiekt = (Wyposazenie) selectList.getValue();
       obiekt.setIlosc_pozostalych(ile+obiekt.getIlosc_pozostalych());
       service.saveOrUpdate(obiekt);

    }
}
