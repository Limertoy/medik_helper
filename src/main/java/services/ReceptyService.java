package services;

import DAO.DaoFactory;
import DAO.ReceptyDAO;

import obiekty.Recepty;


import java.util.List;

public class ReceptyService {
    private ReceptyDAO dao = DaoFactory.createReceptyDao();

    public Recepty saveOrUpdate(Recepty obj) {
        return dao.save(obj);
    }

    public void remove(Recepty obj) {
        dao.remove(obj);
    }

    public Recepty findById(int id) {
        return dao.findById(id);
    }

    public List<Recepty> findAll() {
        return dao.findAll();
    }

    public List<Recepty> findNiewydanePacjenta(int id) {
        return dao.findNiewydanePacjenta(id);
    }
}
