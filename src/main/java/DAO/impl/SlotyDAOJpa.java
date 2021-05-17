package DAO.impl;

import DAO.SlotyDAO;
import obiekty.Sloty;

import javax.persistence.EntityManager;
import java.util.List;

public class SlotyDAOJpa implements SlotyDAO 
{
    private EntityManager em;

    public SlotyDAOJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public Sloty save(Sloty entity) {
        em.getTransaction().begin();
        if (entity.getId_slota() == 1) {
            em.persist(entity);
        } else {
            entity = em.merge(entity);
        }
        em.getTransaction().commit();
        return entity;
    }

    @Override
    public void remove(Sloty entity) {
        entity = findById(entity.getId_slota());
        em.getTransaction().begin();
        em.remove(entity);
        em.getTransaction().commit();
    }

    @Override
    public Sloty findById(int id) {
        return em.find(Sloty.class, id);
    }


    @Override
    public List<Sloty> findAll() {
        return em.createNativeQuery("SELECT * FROM sloty ", Sloty.class).getResultList();
    }

    public EntityManager getPersistenceContext() {
        return this.em;
    }

}
