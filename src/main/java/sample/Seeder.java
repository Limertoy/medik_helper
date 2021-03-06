package sample;

import com.github.javafaker.Faker;
import obiekty.*;
import services.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static java.util.concurrent.TimeUnit.DAYS;

public class Seeder {

    private WyposazenieService service;
    private SlotyService service2;
    private PracownikService service3;
    private RolaService service4;
    private Lista_ChorobService service5;
    private PacjentService service6;

    public void setService(WyposazenieService service) {
        this.service = service;
    }

    public void setService2(SlotyService service2) {
        this.service2 = service2;
    }

    public void setService3(PracownikService service3) {
        this.service3 = service3;
    }

    public void setService4(RolaService service4) {
        this.service4 = service4;
    }

    public void setService5(Lista_ChorobService service5) {
        this.service5 = service5;
    }

    public void setService6(PacjentService service6) {this.service6 = service6; }

    public void generateWyp() { //1
        this.setService(new WyposazenieService());

        for (int i = 1; i < 100; i++) {
            Faker faker = new Faker();
            String nazwa = faker.commerce().productName();

            final String[] proper_noun = {"Lek", "Sprzet"};
            Random random = new Random();
            int index = random.nextInt(proper_noun.length);
            String typ = proper_noun[index];
            Date data = faker.date().future(240, DAYS);
            int ilep = faker.number().numberBetween(1, 100);
            Wyposazenie wyp = new Wyposazenie(nazwa, typ, data, ilep);
            service.saveOrUpdate(wyp);
        }


    }

    public void generatePracownik() {  //2

        setService3(new PracownikService());
        setService4(new RolaService());

        for (int i = 1; i < 20; i++) {
            Faker faker = new Faker();
            String email = faker.internet().emailAddress();
            String password = faker.internet().password();
            String imie = faker.name().firstName();
            String nazwisko = faker.name().lastName();
            Rola rola = service4.findById(faker.number().numberBetween(1, 4));
            Pracownik pracownik = new Pracownik(email, password, rola, imie, nazwisko);
            service3.saveOrUpdate(pracownik);
        }


    }

    public void godzinySloty(String godzina) {
        this.setService2(new SlotyService());

        List<Pracownik> list = service3.znajdzLekarza();
        for (int i = 1; i < list.size(); i++) {

            Faker faker = new Faker();

            for (int j = 1; j < 30; j++) {

                Date data = faker.date().future(j, j-1, DAYS);
                String informacja = "";
                Pracownik pracownik = list.get(i);
                Sloty slot = new Sloty(data.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), godzina, informacja, pracownik);
                service2.saveOrUpdate(slot);
            }
        }

    }

    public void generateSloty() {  //3
        this.setService2(new SlotyService());

        List<Pracownik> list = service3.znajdzLekarza();



            godzinySloty("8:30");
            godzinySloty("9:00");
            godzinySloty("9:30");
            godzinySloty("10:00");
            godzinySloty("10:30");
            godzinySloty("11:00");
            godzinySloty("11:30");
            godzinySloty("12:00");
            godzinySloty("12:30");
            godzinySloty("13:00");

    }
    public void generateChoroby() {  //4
        this.setService5(new Lista_ChorobService());

        for (int i = 1; i < 100; i++) {

            Faker faker = new Faker();

            String kod = faker.demographic().demonym();
            String nazwa = faker.lorem().word();

            Lista_chorob newLista = new Lista_chorob(kod,nazwa);

            service5.saveOrUpdate(newLista);
        }
    }

    public void generatePacjent() { //6
        this.setService6(new PacjentService());
        final String[] proper_noun = {"m????czyzna", "kobieta"};
        final String[] woj = {"podlaskie", "warmi??sko", "mazurskie", "pomorskie", "zachodniopomorskie", "lubuskie",
                "wielkopolskie", "????dzkie", "kujawskopomorskie", "mazowieckie", "lubelskie", "??wi??tokrzyskie",
                "podkarpackie", "ma??opolskie", "??l??skie", "opolskie", "dolno??l??skie" };
        Random random = new Random();
        for (int i = 1; i < 30; i++){
            int index = random.nextInt(proper_noun.length);
            int index2 = random.nextInt(woj.length);

            Faker faker = new Faker();

            String nazwisko_pacjenta = faker.name().lastName();
            String imie_pacjenta = faker.name().firstName();
            String drugie_imie = faker.name().name();
            String pesel = faker.numerify("###########");
            String plec = proper_noun[index];
            LocalDate data_urodzenia =  faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            String wojewodztwo = woj[index2];
            String miejscowosc = faker.address().city();
            String adres = faker.address().streetAddress();
            String kod_pocztowy = faker.address().zipCode();
            String telefon = faker.numerify("###-###-###");
            String email = faker.internet().emailAddress();

            Pacjent newLista = new Pacjent(nazwisko_pacjenta, imie_pacjenta, drugie_imie, pesel, plec, data_urodzenia,
                    wojewodztwo, miejscowosc, adres, kod_pocztowy, telefon, email);

            service6.saveOrUpdate(newLista);
        }
    }
}
