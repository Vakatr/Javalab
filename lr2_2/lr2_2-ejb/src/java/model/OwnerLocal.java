package model;

import entities.Owner;
import java.util.List;
import javax.ejb.Local;

@Local
public interface OwnerLocal {
    public List<Owner> findAlll();
    void create(Owner owner);
    void edit(Owner owner);
    void remove(Owner car); 
    Owner find(Object id);  
    List<Owner> findRange(int[] range);
    int count();
}
