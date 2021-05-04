package lekarz;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import obiekty.Pacjent;
import obiekty.Sloty;
import org.hibernate.Session;
import sample.ControllerLogin;
import sample.HibernateUtil;

import org.hibernate.query.Query;


public class ControllerKalendarz extends ControllerLogin implements Initializable {
    public TableColumn godzinaTable, infoTable;
    public TableView kalendarz;
    public ChoiceBox choiseGodzina;
    public Text textGodzina;
    @FXML
    Button buttonLogin, exit_button, minimalize_button, edytujButton, pracaDzienButton, pracaDzienButton1, edytujButton1;
    @FXML
    DatePicker datePicker = new DatePicker(LocalDate.now());

    Session session = HibernateUtil.getSessionFactory().openSession();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void exit(ActionEvent actionEvent) {
        Stage stage = (Stage) exit_button.getScene().getWindow();
        stage.close();
    }

    public void minimize(ActionEvent actionEvent) {
        Stage stage = (Stage) minimalize_button.getScene().getWindow();
        stage.setIconified(true);
    }

    public void reloadDate(){
        LocalDate localDate = datePicker.getValue();
        Query q = session.createQuery("from Sloty where data=:data")
                .setParameter("data", localDate);
        List<Sloty> list1 = q.list();

        ObservableList<Sloty> data1 = FXCollections.observableArrayList();

        int i = 0;
        for(Sloty s : list1){
            if(s.getPracownik().getId_pracownika() == id_sesji) {
                data1.add(i, s);
                i++;
            }
        }
        godzinaTable.setCellValueFactory(new PropertyValueFactory("godzina"));
        infoTable.setCellValueFactory(new PropertyValueFactory("informacja"));
        kalendarz.getItems().setAll(data1);
    }

    public void selectOne(Event event) {
        Sloty all;
        all = (Sloty) kalendarz.getSelectionModel().getSelectedItem();
        String info = all.getInformacja();
        if(all.getPacjent() != null){
            System.out.println(1);
            edytujButton.setVisible(false);
            edytujButton1.setVisible(false);
        } else if(info.equals("x")) {
            edytujButton.setVisible(false);
            edytujButton1.setVisible(true);
        } else {
            edytujButton.setVisible(true);
            edytujButton1.setVisible(false);
        }
    }

    public void pracaGodzina(ActionEvent actionEvent) {
        session.beginTransaction();
        Sloty all;
        all = (Sloty) kalendarz.getSelectionModel().getSelectedItem();
        String godzina = all.getGodzina();
        LocalDate localDate = datePicker.getValue();
        Query q = session.createQuery("from Sloty where data=:data and godzina=:godzina and pracownik.id_pracownika=:pracownik");
        q.setParameter("pracownik", id_sesji);
        q.setParameter("data", localDate);
        q.setParameter("godzina", godzina);
        List<Sloty> list = q.list();
        for (Sloty s : list){
            s.setInformacja("x");
        }
        session.save(list.get(0));
        session.getTransaction().commit();
        reloadDate();
        edytujButton.setVisible(false);
        edytujButton1.setVisible(true);
    }

    public void pracaGodzina1(ActionEvent actionEvent) {
        session.beginTransaction();
        Sloty all;
        all = (Sloty) kalendarz.getSelectionModel().getSelectedItem();
        String godzina = all.getGodzina();
        LocalDate localDate = datePicker.getValue();
        Query q = session.createQuery("from Sloty where data=:data and godzina=:godzina and pracownik.id_pracownika=:pracownik");
        q.setParameter("pracownik", id_sesji);
        q.setParameter("data", localDate);
        q.setParameter("godzina", godzina);
        List<Sloty> list = q.list();
        for (Sloty s : list){
            s.setInformacja(" ");
        }
        session.save(list.get(0));
        session.getTransaction().commit();
        reloadDate();
        edytujButton.setVisible(true);
        edytujButton1.setVisible(false);
    }

    public void pracaDzien(ActionEvent actionEvent) {
        session.beginTransaction();
        LocalDate localDate = datePicker.getValue();
        Query q = session.createQuery("from Sloty where data=:data and pracownik.id_pracownika=:pracownik");
        q.setParameter("pracownik", id_sesji);
        q.setParameter("data", localDate);
        List<Sloty> list = q.list();
        for (Sloty s : list){
            if(s.getPacjent() == null) {
                s.setInformacja("x");
            }
        }
        session.save(list.get(0));
        session.getTransaction().commit();
        reloadDate();
    }

    public void pracaDzien1(ActionEvent actionEvent) {
        session.beginTransaction();
        LocalDate localDate = datePicker.getValue();
        Query q = session.createQuery("from Sloty where data=:data and pracownik.id_pracownika=:pracownik");
        q.setParameter("pracownik", id_sesji);
        q.setParameter("data", localDate);
        List<Sloty> list = q.list();
        for (Sloty s : list){
            if(s.getPacjent() == null)
                s.setInformacja(" ");
        }
        session.save(list.get(0));
        session.getTransaction().commit();
        reloadDate();
    }

    public void zmianaDaty(ActionEvent actionEvent) {
        pracaDzienButton.setVisible(true);
        pracaDzienButton1.setVisible(true);
        reloadDate();
    }



    //PRZYCISKI PRZEŁĄCZENIA NA INNY EKRAN

    public void przejdz(ActionEvent actionEvent, String s1) throws IOException {
        URL url = Paths.get(s1).toUri().toURL();
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
        przejdz(actionEvent,"./src/main/java/sample/sample.fxml");
    }

    public void wizyty(ActionEvent actionEvent) throws IOException { przejdz(actionEvent, "./src/main/java/lekarz/wizyty.fxml"); }

    public void pacjenci(ActionEvent actionEvent) throws IOException { przejdz(actionEvent,"./src/main/java/lekarz/pacjenci.fxml"); }

    public void kartaPacjenta(ActionEvent actionEvent) throws IOException { przejdz(actionEvent,"./src/main/java/lekarz/kartaPacjenta.fxml"); }
}
