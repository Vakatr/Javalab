package model;

import entities.Owner;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class OwnerFacade extends AbstractFacade<Owner> implements OwnerLocal{

    @PersistenceContext(unitName = "lr2_2-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OwnerFacade() {
        super(Owner.class);
    }
    @Override
     public List<Owner> findAlll(){        
        return em.createNamedQuery("Owner.findAlll").getResultList();
    }
    
}
