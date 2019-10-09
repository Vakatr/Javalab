package entities;

import entities.Box;
import entities.Car;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-27T15:23:38")
@StaticMetamodel(Owner.class)
public class Owner_ { 

    public static volatile CollectionAttribute<Owner, Box> boxCollection;
    public static volatile SingularAttribute<Owner, String> Address;
    public static volatile SingularAttribute<Owner, String> Phone;
    public static volatile CollectionAttribute<Owner, Car> carCollection;
    public static volatile SingularAttribute<Owner, String> Driving_license_owner;
    public static volatile SingularAttribute<Owner, Integer> id;
    public static volatile SingularAttribute<Owner, String> Patronymic;
    public static volatile SingularAttribute<Owner, String> Surname;
    public static volatile SingularAttribute<Owner, String> Name;

}