package controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.*;
import javax.ejb.EJB;
import entities.*;
import model.CarLocal;
import model.OwnerLocal;

@Named(value = "carController")
@SessionScoped
public class CarController implements Serializable {

    @EJB
    private OwnerLocal ownerFacade;

    @EJB
    private CarLocal carFacade;
    private Car c = new Car();
    private Owner o = new Owner();
    
    
    public Car getC() {
        return c;
    }

    public void setC(Car c) {
        this.c = c;
    }
    
    
    public CarController() {
    }
     public List<Car> findAll(){
        return this.carFacade.findAll();
    }
    
    public String addCar(){
        this.o = ownerFacade.find(o.getId());
        this.c.setOwner(o);
        this.carFacade.create(this.c);
        this.c = new Car();
        return "index";
    }
    
    public void deleteCar(Car c){
        this.carFacade.remove(c);
    }
    
    public String editCar(Car c){
        this.c = c;
        return "editCar";
    }
    
    public String editCar(){
        this.carFacade.edit(this.c);
        this.c = new Car();
        return "index";
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
   
}
