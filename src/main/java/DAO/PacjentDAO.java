package DAO;

import obiekty.Pacjent;

import java.util.List;

public interface PacjentDAO {
    Pacjent save(Pacjent entity);
    void remove(Pacjent entity);
    Pacjent findById(int id);
    List<Pacjent> findAll();
}


