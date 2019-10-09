package controller;

import entities.Box;
import entities.Car;
import entities.Owner;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import model.BoxLocal;
import model.CarLocal;
import model.OwnerLocal;

@Named(value = "boxController")
@SessionScoped
public class BoxController implements Serializable {


    @EJB
    private BoxLocal boxFacade;
    private Box b = new Box();
    private Owner o = new Owner();
    
    public BoxController() {    
    }
    public List<Box> findAll(){
    return this.boxFacade.findAll();
    }

    /**
     * @return the b
     */
    public Box getB() {
        return b;
    }

    /**
     * @param b the b to set
     */
    public void setB(Box b) {
        this.b = b;
    }

    /**
     * @return the o
     */
    public Owner getO() {
        return o;
    }

    /**
     * @param o the o to set
     */
    public void setO(Owner o) {
        this.o = o;
    }
    
    public String addBox(){
        this.boxFacade.create(this.b);
        this.b = new Box();
        return "index";
    }
    
    public void deleteBox(Box b){
        this.boxFacade.remove(b);
    }
    
    public String editBox(Box b){
        this.b = b;
        return "editBox";
    }
    
    public String editBox(){
        this.boxFacade.edit(this.b);
        this.b = new Box();
        return "index";
    }
    
}
