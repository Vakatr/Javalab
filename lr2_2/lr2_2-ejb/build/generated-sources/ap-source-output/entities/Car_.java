package entities;

import entities.Owner;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-27T15:23:38")
@StaticMetamodel(Car.class)
public class Car_ { 

    public static volatile SingularAttribute<Car, Owner> owner;
    public static volatile SingularAttribute<Car, String> Brand;
    public static volatile SingularAttribute<Car, String> Color;
    public static volatile SingularAttribute<Car, String> State_number;
    public static volatile SingularAttribute<Car, Integer> id;
    public static volatile SingularAttribute<Car, Date> Release_year;
    public static volatile SingularAttribute<Car, String> Power;

}