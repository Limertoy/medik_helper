package DAO;

import obiekty.Lista_chorob;

import java.util.List;

public interface Lista_chorobDAO {
    Lista_chorob save(Lista_chorob entity);
    void remove(Lista_chorob entity);
    Lista_chorob findById(int id);
    List<Lista_chorob> findAll();
}
