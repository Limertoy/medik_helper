package services;

import DAO.DaoFactory;
import DAO.RolaDAO;
import obiekty.Rola;

import java.util.List;

public class RolaService {

    private RolaDAO dao = DaoFactory.createRolaDao();

    public Rola saveOrUpdate(Rola obj) {
        return dao.save(obj);
    }

    public void remove(Rola obj) {
        dao.remove(obj);
    }

    public Rola findById(int id) {
        return dao.findById(id);
    }

    public List<Rola> findAll() {
        return dao.findAll();
    }
}
