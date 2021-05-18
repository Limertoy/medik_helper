package DAO.impl;

import DAO.SkierowanieDAO;
import obiekty.Rola;
import obiekty.Skierowanie;

import javax.persistence.EntityManager;
import java.util.List;

public class SkierowanieDAOJpa implements SkierowanieDAO {

    private final EntityManager em;

    public SkierowanieDAOJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public Skierowanie save(Skierowanie entity) {
        em.getTransaction().begin();
        if (entity.getId_skierowania() == 1) {
            em.persist(entity);
        } else {
            entity = em.merge(entity);
        }
        em.getTransaction().commit();
        return entity;
    }

    @Override
    public void remove(Skierowanie entity) {
        entity = findById(entity.getId_skierowania());
        em.getTransaction().begin();
        em.remove(entity);
        em.getTransaction().commit();
    }

    @Override
    public Skierowanie findById(int id) {
        return em.find(Skierowanie.class, id);
    }


    @Override
    public List<Skierowanie> findAll() {
        return em.createNativeQuery("SELECT * FROM skierowanie ", Skierowanie.class).getResultList();
    }

    public EntityManager getPersistenceContext() {
        return this.em;
    }

}
