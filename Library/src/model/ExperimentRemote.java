package model;

import entitys.client;
import javax.ejb.Remote;

@Remote
public interface ExperimentRemote {    
    void createException (client c);
    void createNoT(client c);
    void createRollBack(client c);
    void createNewT(client c);    
}
