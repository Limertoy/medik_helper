package DAO.impl;

import DAO.ReceptyDAO;
import obiekty.Recepty;
import obiekty.Rola;
import obiekty.Skierowanie;

import javax.persistence.EntityManager;
import java.util.List;

public class ReceptyDAOJpa implements ReceptyDAO {

    private EntityManager em;

    public ReceptyDAOJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public Recepty save(Recepty entity) {
        em.getTransaction().begin();
        if (entity.getId_recepty() == 0) {
            em.persist(entity);
        } else {
            entity = em.merge(entity);
        }
        em.getTransaction().commit();
        return entity;
    }

    @Override
    public void remove(Recepty entity) {
        entity = findById(entity.getId_recepty());
        em.getTransaction().begin();
        em.remove(entity);
        em.getTransaction().commit();
    }

    @Override
    public Recepty findById(int id) {
        return em.find(Recepty.class, id);
    }


    @Override
    public List<Recepty> findAll() {
        return em.createNativeQuery("SELECT * FROM recepty ", Recepty.class).getResultList();
    }

    @Override
    public List<Recepty> findNiewydanePacjenta(int id) {
        return em.createNativeQuery("SELECT * FROM recepty where wydane = 0 AND id_pacjenta = "+id+"", Recepty.class).getResultList();
    }

    public EntityManager getPersistenceContext() {
        return this.em;
    }

}
