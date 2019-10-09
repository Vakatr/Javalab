package entities;

import entities.Owner;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-27T15:23:38")
@StaticMetamodel(Box.class)
public class Box_ { 

    public static volatile SingularAttribute<Box, String> Nomerbox;
    public static volatile SingularAttribute<Box, Integer> id;
    public static volatile CollectionAttribute<Box, Owner> ownerCollection;

}