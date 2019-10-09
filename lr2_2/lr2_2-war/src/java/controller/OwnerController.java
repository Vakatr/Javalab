package controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.*;
import javax.ejb.EJB;
import entities.*;
import entitys.client;
import experiment.experimentBean;
import model.OwnerLocal;


@Named(value = "ownerController")
@SessionScoped
public class OwnerController implements Serializable {

    @EJB
    private OwnerLocal ownerFacade;
    private Owner owner = new Owner();
    
    private client c = new client(); 

    @EJB
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
//    public Owner getOwner() {
//        return owner;
//    }
//
//    /**
//     * @param owner the owner to set
//     */
//    public void setOwner(Owner owner) {
//        this.owner = owner;
//    }

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

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }    
    
    public OwnerController() {
    }
    
//    public List<Owner> findAll(){
//        return this.ownerFacade.findAll();
//    }
    
    public List<Owner> findAlll(){
        return this.ownerFacade.findAlll();
    }
    public String addOwner(){
        this.ownerFacade.create(this.owner);
        this.owner = new Owner();
        return "index";
    }
    
    public void deleteOwner(Owner owner){
        this.ownerFacade.remove(owner);
    }
    
    public String editOwner(Owner owner){
        this.owner = owner;
        return "editOwner";
    }
    
    public String editOwner(){
        this.ownerFacade.edit(this.owner);
        this.owner = new Owner();
        return "index";
    }
}
