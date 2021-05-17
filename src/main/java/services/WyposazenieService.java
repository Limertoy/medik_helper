package services;

import DAO.DaoFactory;
import DAO.WyposazenieDAO;
import obiekty.Wyposazenie;

import java.util.List;

public class WyposazenieService {
    private WyposazenieDAO dao = DaoFactory.createWyposazenieDao();

    public Wyposazenie saveOrUpdate(Wyposazenie obj) {
        return dao.save(obj);
    }

    public void remove(Wyposazenie obj) {
        dao.remove(obj);
    }

    public Wyposazenie findById(int id) {
        return dao.findById(id);
    }

    public List<Wyposazenie> findAll() {
        return dao.findAll();
    }

    public List<Wyposazenie> findLeki() {
        return dao.findLeki();
    }
}
