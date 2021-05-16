package services;

import DAO.DaoFactory;
import DAO.Lista_chorobDAO;
import obiekty.Lista_chorob;


import java.util.List;

public class Lista_ChorobService {
    private Lista_chorobDAO dao = DaoFactory.createLista_chorobDao();

    public Lista_chorob saveOrUpdate(Lista_chorob obj) {
        return dao.save(obj);
    }

    public void remove(Lista_chorob obj) {
        dao.remove(obj);
    }

    public Lista_chorob findById(int id) {
        return dao.findById(id);
    }

    public List<Lista_chorob> findAll() {
        return dao.findAll();
    }

}
