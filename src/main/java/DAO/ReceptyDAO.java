package DAO;

import obiekty.Recepty;


import java.util.List;

public interface ReceptyDAO {
    Recepty save(Recepty entity);
    void remove(Recepty entity);
    Recepty findById(int id);
    List<Recepty> findAll();
}
