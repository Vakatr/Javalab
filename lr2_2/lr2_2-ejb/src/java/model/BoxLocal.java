package model;

import entities.Box;
import java.util.List;
import javax.ejb.Local;

@Local
public interface BoxLocal {
    
void create(Box box);
void edit(Box box);
void remove(Box box); 
Box find(Object id); 
List<Box> findAll(); 
List<Box> findRange(int[] range);
int count();
    
}
