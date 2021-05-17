package DAO;

import obiekty.Sloty;

import java.util.List;

public interface SlotyDAO {
    Sloty save(Sloty entity);
    void remove(Sloty entity);
    Sloty findById(int id);
    List<Sloty> findAll();
}
