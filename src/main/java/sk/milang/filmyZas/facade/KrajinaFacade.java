/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.milang.filmyZas.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sk.milang.filmyZas.model.Krajina;

/**
 *
 * @author fskgranam
 */
@Stateless
public class KrajinaFacade extends AbstractFacade<Krajina> {
    @PersistenceContext(unitName = "FilmyPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public KrajinaFacade() {
        super(Krajina.class);
    }
    
    public Krajina najdiKrajinuPodlaNazvu(String nazov) {
        List<Krajina> results = em.createNamedQuery("Krajina.najdiKrajinu").setParameter("nazov", nazov).getResultList();
        if (results.isEmpty()) {
            return null;
        } else {
            return results.get(0);
        }
    }
}
