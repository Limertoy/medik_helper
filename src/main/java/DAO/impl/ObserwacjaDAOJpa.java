package DAO.impl;

import DAO.ObserwacjaDAO;
import obiekty.Obserwacja;
import obiekty.Rola;

import javax.persistence.EntityManager;
import java.util.List;

public class ObserwacjaDAOJpa implements ObserwacjaDAO {

    private EntityManager em;

    public ObserwacjaDAOJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public Obserwacja save(Obserwacja entity) {
        em.getTransaction().begin();
        if (entity.getId_obserwacji() == 1) {
            em.persist(entity);
        } else {
            entity = em.merge(entity);
        }
        em.getTransaction().commit();
        return entity;
    }

    @Override
    public void remove(Obserwacja entity) {
        entity = findById(entity.getId_obserwacji());
        em.getTransaction().begin();
        em.remove(entity);
        em.getTransaction().commit();
    }

    @Override
    public Obserwacja findById(int id) {
        return em.find(Obserwacja.class, id);
    }


    @Override
    public List<Obserwacja> findAll() {
        return em.createNativeQuery("SELECT * FROM obserwacje ", Rola.class).getResultList();
    }

    public EntityManager getPersistenceContext() {
        return this.em;
    }
}
