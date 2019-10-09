package model;

import entitys.client;
import java.io.Serializable;
import javax.annotation.Resource;
import javax.ejb.EJBException;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Stateless 
public class ExperimentRemoteFacade extends AbstractFacade<client> implements ExperimentRemote, Serializable{

    public ExperimentRemoteFacade() {
        super(client.class);
    }
    
    @Resource 
    SessionContext sessionContext; 

    
    @PersistenceContext(unitName = "lr3_EJBModulePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em; 
    }

    @Override
    public void createException(client c) throws EJBException { 
        em.persist(c); 
        throw new EJBException(); 
    }

    @Override
    @Transactional(Transactional.TxType.NOT_SUPPORTED) 
    public void createNoT(client c) {
        em.persist(c); 
    }

    @Override
    public void createRollBack(client c) {
        em.persist(c); 
        sessionContext.setRollbackOnly(); 
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW) 
    public void createNewT(client c) {
        em.persist(c); 
    }
    
}
