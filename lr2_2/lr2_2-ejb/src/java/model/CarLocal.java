package model;

import entities.Car;
import java.util.List;
import javax.ejb.Local;

@Local
public interface CarLocal {

void create(Car car);
void edit(Car car);
void remove(Car car); 
Car find(Object id); 
List<Car> findAll(); 
List<Car> findRange(int[] range);
int count();
    
}
