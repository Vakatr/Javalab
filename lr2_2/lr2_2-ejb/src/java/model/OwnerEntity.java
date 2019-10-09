package model;

import entities.Owner;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PrePersist;

public class OwnerEntity {

@PrePersist
public void onPrePersist(Owner owner) {
System.out.println("Owner adding " + owner);
}

@PostPersist
public void onPostPersist(Owner owner) {
System.out.println("Owner '" + owner + "' added with id = " + owner.getId());
}

@PostRemove
public void onPostRemove(Owner owner) {
System.out.println("Owner '" + owner + "' deleted");
}
}
