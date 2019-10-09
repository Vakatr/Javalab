package model;

import entitys.client;
import java.io.Serializable;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class clientFacade extends AbstractFacade<client> implements clientFacadeRemote, Serializable {

    @PersistenceContext(unitName = "lr3_EJBModulePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public clientFacade() {
        super(client.class);
    }
    
}
