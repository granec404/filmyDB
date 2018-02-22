/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.milang.filmyZas.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sk.milang.filmyZas.model.Herec;

/**
 *
 * @author fskgranam
 */
@Stateless
public class HerecFacade extends AbstractFacade<Herec> {
    @PersistenceContext(unitName = "FilmyPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HerecFacade() {
        super(Herec.class);
    }
}
