/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.milang.filmyZas.facade;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 *
 * @author fskgranam
 */

/**
 * Abstract Facade defining main operations over each persistent entity. This
 * includes CRUD - create, remove, update and delete operations. Furthermore, it
 * includes findAll, findByRange and count operation.
 *
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    /**
     * Creates given entity as a record in database.
     *
     * @param entity
     */
    public void create(T entity) {
        EntityManager em = getEntityManager();
        EntityTransaction entityTransaction = em.getTransaction();
        entityTransaction.begin();
        em.persist(entity);
        entityTransaction.commit();
        em.close();

    }

    /**
     * Updates values of given entity in database.
     *
     * @param entity
     */
    public void edit(T entity) {
        EntityManager em = getEntityManager();
        EntityTransaction entityTransaction = em.getTransaction();
        entityTransaction.begin();
        em.merge(entity);
        em.flush();
        entityTransaction.commit();
        em.close();
    }

    /**
     * Removes given entity from database.
     *
     * @param entity
     */
    public void remove(T entity) {
        EntityManager em = getEntityManager();
        EntityTransaction entityTransaction = em.getTransaction();
        entityTransaction.begin();
        em.remove(em.merge(entity));
        entityTransaction.commit();
        em.close();
    }

    /**
     * Finds entity with given id in database and returns it.
     *
     * @param entity
     */
    public T find(Object id) {
        EntityManager em = getEntityManager();
        T result = em.find(entityClass, id);
        em.close();
        return result;
    }

    /**
     * Returns all records persisted in database as a List.
     *
     * @param list of all records
     */
    public List<T> findAll() {
        EntityManager em = getEntityManager();
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        List<T> result = em.createQuery(cq).getResultList();
        em.close();
        return result;
    }

    /**
     * Returns a range of records persisted in database as a List.
     *
     * @return list of records
     */
    public List<T> findRange(int[] range) {
        EntityManager em = getEntityManager();
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        List<T> result = q.getResultList();
        em.close();
        return result;
    }

    /**
     * Returns number of records persisted in database.
     *
     * @return number of records
     */
    public int count() {
        EntityManager em = getEntityManager();
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(em.getCriteriaBuilder().count(rt));
        javax.persistence.Query q = em.createQuery(cq);
        int result = ((Long) q.getSingleResult()).intValue();
        em.close();
        return result;
    }
}

