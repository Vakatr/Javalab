package model;

import entitys.client;
import java.util.List;
import javax.ejb.Remote;

@Remote
public interface clientFacadeRemote {
    void create(client client);
    void edit(client client);
    void remove(client client);
    client find(Object id);
    List<client> findAll();
    List<client> findRange(int[] range);
    int count();
}
