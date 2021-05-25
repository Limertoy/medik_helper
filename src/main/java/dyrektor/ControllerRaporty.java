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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import obiekty.Pdf;
import obiekty.Pracownik;
import org.hibernate.Session;
import org.hibernate.query.Query;
import sample.HibernateUtil;
import raporty.LekarzyRaporty;
import services.PdfService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;

import static sample.ControllerLogin.id_sesji;

public class ControllerRaporty implements Initializable {
    public TableView raportyTable;
    public TableColumn nazwaColumn, dataColumn;
    @FXML
    Button buttonLogin, exit_button, minimalize_button;

    LekarzyRaporty reporty = new LekarzyRaporty();
    private PdfService service;

    Session session = HibernateUtil.getSessionFactory().openSession();
    public void setService(PdfService service){this.service = service;}
    
    public void exit(ActionEvent actionEvent) {
        Stage stage = (Stage) exit_button.getScene().getWindow();
        stage.close();
    }

    public void minimize(ActionEvent actionEvent) {
        Stage stage = (Stage) exit_button.getScene().getWindow();
        stage.setIconified(true);
    }
    
    public void przejdz(ActionEvent actionEvent, String s1) throws IOException {
        URL url = Paths.get(s1).toUri().toURL();
        Parent parent = FXMLLoader.load(url);
        Scene scene = new Scene(parent);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }


    public void wyloguj(ActionEvent actionEvent) throws IOException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.close();
        przejdz(actionEvent,"./src/main/java/sample/sample.fxml");
    }

    public void zamowienie(ActionEvent actionEvent) throws IOException {
        przejdz(actionEvent,"./src/main/java/dyrektor/dyrektorZamawianie.fxml");
    }

    public void personel(ActionEvent actionEvent) throws IOException {
        przejdz(actionEvent,"./src/main/java/dyrektor/pracownicy.fxml");
    }

    public void wyposazenie(ActionEvent actionEvent) throws IOException {
        przejdz(actionEvent,"./src/main/java/dyrektor/dyrektorZarzadzanie.fxml");
    }

    public void generatePraca(ActionEvent actionEvent) throws IOException {
        Query q = session.createQuery("from Pracownik where id=:pracownik");
        q.setParameter("pracownik", id_sesji);
        List<Pracownik> list = q.list();
        reporty.createPdf(list.get(0).getImie_pracownika() + " " + list.get(0).getNazwisko_pracownika());

        przejdz(actionEvent, "./src/main/java/dyrektor/raporty.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.setService(new PdfService());
        ObservableList<Pdf> list = FXCollections.observableArrayList(service.findAll());
        nazwaColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        dataColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        raportyTable.setItems(list);
    }
}
