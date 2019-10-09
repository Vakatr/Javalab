package model;

import entities.Owner;
import javax.annotation.Resource;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Stateless
public class ExperimentLocalFacade extends AbstractFacade<Owner> implements ExperimentLocal {
    
    public ExperimentLocalFacade() {
        super(Owner.class);
    }
    
    @Resource 
    SessionContext sessionContext; 

 
    @PersistenceContext(unitName = "lr2_2-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em; 
    }

    @Override
    public void createException(Owner owner) throws EJBException {
        em.persist(owner); 
        throw new EJBException();
    }

    @Override
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public void createNoT(Owner owner) {
        em.persist(owner);
    }

    @Override
    public void createRollBack(Owner owner) {
       em.persist(owner);
       sessionContext.setRollbackOnly();
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW) 
    public void createNewT(Owner owner) {
        em.persist(owner);
    }
    
}
