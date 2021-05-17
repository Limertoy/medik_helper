package DAO;

import obiekty.Pracownik;

import java.util.List;

public interface PracownikDAO {
    Pracownik save(Pracownik entity);
    void remove(Pracownik entity);
    Pracownik findById(int id);
    List<Pracownik> znajdzLekarza();
    List<Pracownik> findAll();
}
