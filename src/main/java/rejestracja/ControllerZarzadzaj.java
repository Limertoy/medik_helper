package rejestracja;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lekarz.ControllerKalendarz;
import obiekty.Pacjent;
import obiekty.Pracownik;
import obiekty.Sloty;
import org.hibernate.Session;
import org.hibernate.query.Query;
import sample.ControllerLogin;
import sample.HibernateUtil;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerZarzadzaj implements Initializable {
    @FXML
    public TableColumn godzinaTable;
    @FXML
    public TableView<Pracownik> pracownicy;
    public TableColumn infoTable;
    public TableView kalendarz;
    @FXML
    private TableView<Pacjent> pacjenci;
    @FXML
    public TableColumn imie_table, nazwisko_table,imieTable, nazwiskoTable, peselTable;
    @FXML
    public TextField szukaj, szukaj1;
    @FXML
    Button buttonLogin, exit_button, minimalize_button;
    @FXML
    DatePicker datePicker = new DatePicker(LocalDate.now());

    Session session = HibernateUtil.getSessionFactory().openSession();


    public void initialize(URL url, ResourceBundle resourceBundle) {
        Query<Pracownik> q = session.createQuery("from Pracownik");
        List<Pracownik> list = q.list();

        ObservableList<Pracownik> data1 = FXCollections.observableArrayList();

        int i = 0;
        for(Pracownik s : list){
            if(s.getRola().getId_rola()== 1){
            data1.add(i,s);
            i++;
            }
        }

        imie_table.setCellValueFactory(new PropertyValueFactory("imie_pracownika"));
        nazwisko_table.setCellValueFactory(new PropertyValueFactory("nazwisko_pracownika"));


        FilteredList<Pracownik> filteredData1 = new FilteredList<>(data1, b -> true);
            szukaj.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData1.setPredicate(pracownik -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lowerCaseFilter = newValue.toLowerCase();
                if (pracownik.getImie_pracownika().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (pracownik.getNazwisko_pracownika().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
            });
            SortedList<Pracownik> sortedData = new SortedList<>(filteredData1);
        sortedData.comparatorProperty().bind(pracownicy.comparatorProperty());
        pracownicy.setItems(sortedData);


        Query<Pacjent> q1 = session.createQuery("from Pacjent");
        List<Pacjent> list1 = q1.list();
        ObservableList<Pacjent> data2 = FXCollections.observableArrayList();
        int j = 0;
        for(Pacjent s : list1){
                data2.add(j, s);
                j++;
            }
        imieTable.setCellValueFactory(new PropertyValueFactory("imie_pacjenta"));
        nazwiskoTable.setCellValueFactory(new PropertyValueFactory("nazwisko_pacjenta"));
        peselTable.setCellValueFactory(new PropertyValueFactory("pesel"));

        FilteredList<Pacjent> filteredData2 = new FilteredList<>(data2, b -> true);
        szukaj1.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData2.setPredicate(pacjent -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (pacjent.getImie_pacjenta().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (pacjent.getNazwisko_pacjenta().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }else if (pacjent.getPesel().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<Pacjent> sortedData1 = new SortedList<>(filteredData2);
        sortedData1.comparatorProperty().bind(pacjenci.comparatorProperty());
        pacjenci.setItems(sortedData1);

    }


    public void exit(ActionEvent actionEvent) {
        Stage stage = (Stage) exit_button.getScene().getWindow();
        stage.close();
    }

    public void minimize(ActionEvent actionEvent) {
        Stage stage = (Stage) minimalize_button.getScene().getWindow();
        stage.setIconified(true);
    }


    //metoda na przycisk wyloguj ktora otwiera scene sample.fxml
    public void wyloguj(ActionEvent actionEvent) throws IOException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.close();
        URL url = Paths.get("./src/main/java/sample/sample.fxml").toUri().toURL();
        Parent loginParent = FXMLLoader.load(url);
        Scene loginScene = new Scene(loginParent);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(loginScene);
        window.show();
    }

    public void pacjenci(ActionEvent actionEvent) throws IOException {
        URL url = Paths.get("./src/main/java/rejestracja/rejestracjaPacjenci.fxml").toUri().toURL();
        Parent pacjenciParent = FXMLLoader.load(url);
        Scene pacjenciScene = new Scene(pacjenciParent);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(pacjenciScene);
        window.show();
    }


    public void rejestracja(ActionEvent actionEvent) throws IOException {
        URL url = Paths.get("./src/main/java/rejestracja/rejestracja.fxml").toUri().toURL();
        Parent rejestracjaParent = FXMLLoader.load(url);
        Scene rejestracjaScene = new Scene(rejestracjaParent);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(rejestracjaScene);
        window.show();
    }

    public void reloadDate(){
        LocalDate localDate = datePicker.getValue();
        Pracownik all = pracownicy.getSelectionModel().getSelectedItem();
        int lekarz = all.getId_pracownika();

        Query q = session.createQuery("from Sloty where data=:data")
                .setParameter("data", localDate);
        List<Sloty> list1 = q.list();

        ObservableList<Sloty> data1 = FXCollections.observableArrayList();

        int i = 0;
        for(Sloty s : list1){
            if(s.getPracownik().getId_pracownika() == lekarz) {
                data1.add(i, s);
                i++;
            }
        }
        godzinaTable.setCellValueFactory(new PropertyValueFactory("godzina"));
        infoTable.setCellValueFactory(new PropertyValueFactory("informacja"));
        kalendarz.getItems().setAll(data1);
    }


    public void dodaj(ActionEvent actionEvent) {
        Sloty godzina = (Sloty) kalendarz.getSelectionModel().getSelectedItem();
        Pacjent pacjent = pacjenci.getSelectionModel().getSelectedItem();
        Pracownik all = pracownicy.getSelectionModel().getSelectedItem();
        int lekarz = all.getId_pracownika();

        if(godzina!=null && pacjent!=null){
            session.beginTransaction();
            LocalDate localDate = datePicker.getValue();
            Query q = session.createQuery("from Sloty where data=:data and godzina=:godzina");
            q.setParameter("data",localDate);
            q.setParameter("godzina",godzina.getGodzina());
            List<Sloty> list = q.list();
            for (Sloty s : list){
                if(s.getPracownik().getId_pracownika()==lekarz){
                    s.setInformacja("wizyta");
                    s.setPacjent(pacjent);
                }
            }
            session.save(list.get(0));
            session.getTransaction().commit();
            reloadDate();
        }

    }

    public void selectLekarz(MouseEvent mouseEvent) {
        reloadDate();


    }

    public void selectPacjent(MouseEvent mouseEvent) {
    }

    public void zmianaDaty2(ActionEvent actionEvent) {
        Pracownik all = pracownicy.getSelectionModel().getSelectedItem();
        if(all!=null){
            reloadDate();
        }


    }
}

