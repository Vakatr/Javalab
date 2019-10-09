package model;

import entities.Box;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class BoxFacade extends AbstractFacade<Box> implements BoxLocal {

    @PersistenceContext(unitName = "lr2_2-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BoxFacade() {
        super(Box.class);
    }
    
}
