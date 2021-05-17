package services;

import DAO.DaoFactory;

import DAO.SkierowanieDAO;

import obiekty.Skierowanie;

import java.util.List;

public class SkierowanieService {

    private SkierowanieDAO dao = DaoFactory.createSkierowanieDao();

    public Skierowanie saveOrUpdate(Skierowanie obj) {
        return dao.save(obj);
    }

    public void remove(Skierowanie obj) {
        dao.remove(obj);
    }

    public Skierowanie findById(int id) {
        return dao.findById(id);
    }

    public List<Skierowanie> findAll() {
        return dao.findAll();
    }

}
