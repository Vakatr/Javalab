package experiment;

import entities.Owner;
import entitys.client;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.transaction.Transactional;
import model.ExperimentLocal;
import model.ExperimentRemote;
import model.OwnerLocal;
import model.clientFacadeRemote;

@Stateless 
@LocalBean 
//@Named(value = "experimentBean")
//@SessionScoped
public class experimentBean implements Serializable {

    @EJB
    private ExperimentLocal experimentLocalFacade;

    @EJB
    private clientFacadeRemote clientFacade;

    @EJB
    private ExperimentRemote experimentRemoteFacade;
    
    @EJB
    private OwnerLocal ownerFacade;
    
 
    /**
     * Creates a new instance of experimentBean
     */
    public experimentBean() {
    }
    
    //закончить транзакцию подтверждением и убедиться, что обновления зафиксированы
    @Transactional 
    public void success(Owner owner,client c) { 
        ownerFacade.create(owner); 
        clientFacade.create(c); 
    } 
    //откат для первой бд и добавление во 2 бд 
    @Transactional 
    public void rollBackDataBaseFirst(Owner owner,client c) { 
        experimentLocalFacade.createRollBack(owner); 
        clientFacade.create(c); 
    } 
    //ejbexception для второй бд, добавление для 1 БД
    @Transactional 
    public void rollBackEJBException(Owner owner,client c) { 
        ownerFacade.create(owner); 
        experimentRemoteFacade.createException(c); 
    } 
    //откат для 1 бд, вне контекста транзакции во 2  бд
    @Transactional 
    public void rollBackDBFirstNoTSecond(Owner owner,client c) { 
        experimentRemoteFacade.createNoT(c); 
        experimentLocalFacade.createRollBack(owner); 
    } 
    //в контексте новой транзакции для 1 бд, exception для 2
    @Transactional 
    public void ExceptionSecondNewTFirst(Owner owner,client c) { 
        experimentLocalFacade.createNewT(owner); 
        experimentRemoteFacade.createException(c); 
    } 

}
