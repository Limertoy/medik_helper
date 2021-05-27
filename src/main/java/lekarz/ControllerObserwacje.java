package lekarz;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.hibernate.Session;
import sample.HibernateUtil;
import services.ObserwacjaService;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class ControllerObserwacje extends ControllerPacjenci implements Initializable {
    public TableView obserwacjeTable;
    public ObserwacjaService obserwacjeService;
    public TableColumn tekstTable, chorobaTable;

    public void przejdz(ActionEvent actionEvent, String s1) throws IOException {
        URL url = getClass().getClassLoader().getResource(s1);
        Parent parent = FXMLLoader.load(url);
        Scene scene = new Scene(parent);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    public void wyloguj(ActionEvent actionEvent) throws IOException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.close();
        przejdz(actionEvent,"sample.fxml");
    }

    @Override
    public void wizyty(ActionEvent actionEvent) throws IOException { super.wizyty(actionEvent); }

    public void pacjenci(ActionEvent actionEvent) throws IOException { przejdz(actionEvent, "lekarz/pacjenci.fxml"); }

     public void kartaPacjenta(ActionEvent actionEvent) throws IOException {przejdz(actionEvent, "lekarz/kartaPacjenta.fxml");}

    @Override
    public void kalendarz(ActionEvent actionEvent) throws IOException {
        super.kalendarz(actionEvent);
    }

    public void setObserwacjeService(ObserwacjaService obserwacjeService) { this.obserwacjeService = obserwacjeService; }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.setObserwacjeService(new ObserwacjaService());
        ObservableList obsList = FXCollections.observableArrayList(obserwacjeService.findAllPacjent(id_zmien));
        obserwacjeTable.setItems(obsList);
        tekstTable.setCellValueFactory(new PropertyValueFactory<>("tekst"));
        chorobaTable.setCellValueFactory(new PropertyValueFactory<>("choroba"));
    }
}
