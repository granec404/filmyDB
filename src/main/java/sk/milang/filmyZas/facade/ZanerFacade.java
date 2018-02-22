/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.milang.filmyZas.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sk.milang.filmyZas.model.Zaner;

/**
 *
 * @author fskgranam
 */
@Stateless
public class ZanerFacade extends AbstractFacade<Zaner> {
    @PersistenceContext(unitName = "FilmyPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ZanerFacade() {
        super(Zaner.class);
    }
}
