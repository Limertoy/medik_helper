package DAO;

import obiekty.Wyposazenie;

import java.util.List;

public interface WyposazenieDAO {
    Wyposazenie save(Wyposazenie entity);
    void remove(Wyposazenie entity);
    Wyposazenie findById(int id);
    List<Wyposazenie> findAll();
}
