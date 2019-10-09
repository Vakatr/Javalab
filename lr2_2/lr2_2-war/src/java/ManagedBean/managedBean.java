package ManagedBean;

import entities.Owner;
import entitys.client;
import experiment.experimentBean;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;

@Stateless

@ManagedBean (name = "managedBean")
@SessionScoped
public class managedBean {

    public managedBean() {
    }
    
    private Owner owner = new Owner(); 
    private client c = new client(); 

    
    private experimentBean experiment_bean; 
    


    public String firstExperiment(){ 
    experiment_bean.success(owner, c); 
    return "index"; 
    } 

    public String secondExperiment(){ 
    experiment_bean.rollBackDataBaseFirst(getOwner(), getC()); 
    return "index"; 
    } 

    public String thirdExperiment(){ 
    experiment_bean.rollBackEJBException(getOwner(), getC()); 
    return "index"; 
    } 

    public String fourthExperiment(){ 
    experiment_bean.rollBackDBFirstNoTSecond(getOwner(), getC()); 
    return "index"; 
    } 

    public String fifthExperiment(){ 
    experiment_bean.ExceptionSecondNewTFirst(getOwner(), getC()); 
    return "index"; 
    } 

    /**
     * @return the owner
     */
    public Owner getOwner() {
        return owner;
    }

    /**
     * @param owner the owner to set
     */
    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    /**
     * @return the c
     */
    public client getC() {
        return c;
    }

    /**
     * @param c the c to set
     */
    public void setC(client c) {
        this.c = c;
    }
    
}
