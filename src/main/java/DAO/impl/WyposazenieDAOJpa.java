package DAO.impl;

import DAO.WyposazenieDAO;
import obiekty.Wyposazenie;

import javax.persistence.EntityManager;
import java.util.List;

public class WyposazenieDAOJpa implements WyposazenieDAO {

    private EntityManager em;

    public WyposazenieDAOJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public Wyposazenie save(Wyposazenie entity) {
        em.getTransaction().begin();
        if (entity.getId_wyposazenia() == 0) {
            em.persist(entity);
        } else {
            entity = em.merge(entity);
        }
        em.getTransaction().commit();
        return entity;
    }

    @Override
    public void remove(Wyposazenie entity) {
        entity = findById(entity.getId_wyposazenia());
        em.getTransaction().begin();
        em.remove(entity);
        em.getTransaction().commit();
    }

    @Override
    public Wyposazenie findById(int id) {
        return em.find(Wyposazenie.class, id);
    }


    @Override
    public List<Wyposazenie> findAll() {
        return em.createNativeQuery("SELECT * FROM wyposazenie ", Wyposazenie.class).getResultList();
    }

    @Override
    public List<Wyposazenie> findLeki() {
        return em.createNativeQuery("SELECT * FROM wyposazenie WHERE typ_wyposazenia = 'lek'", Wyposazenie.class).getResultList();
    }

    public EntityManager getPersistenceContext() {
        return this.em;
    }

}
