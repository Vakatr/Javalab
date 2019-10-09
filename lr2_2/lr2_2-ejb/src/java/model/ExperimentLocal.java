package model;

import entities.Owner;
import javax.ejb.Local;

@Local
public interface ExperimentLocal {
    void createException (Owner owner);
    void createNoT(Owner owner);
    void createRollBack(Owner owner);
    void createNewT(Owner owner);    
}
