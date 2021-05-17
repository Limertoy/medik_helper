package DAO;

import obiekty.Pracownik;
import obiekty.Rola;

import java.util.List;

public interface RolaDAO {
    Rola save(Rola entity);
    void remove(Rola entity);
    Rola findById(int id);
    List<Rola> findAll();
}
