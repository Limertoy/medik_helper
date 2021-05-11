package DAO;

import DAO.impl.PacjentDAOJpa;
import DAO.impl.WyposazenieDAOJpa;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DaoFactory {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("appJavaFXUnit");

    public static WyposazenieDAO createWyposazenieDao() {
        return new WyposazenieDAOJpa(emf.createEntityManager());
    }
    public static PacjentDAO createPacjentDao() { return new PacjentDAOJpa(emf.createEntityManager()); }
    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }

}
