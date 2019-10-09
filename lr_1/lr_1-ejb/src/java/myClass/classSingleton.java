package myClass;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;

@Singleton
@LocalBean
public class classSingleton {

    private int call;

    public int getCall() {
        return call;
    }

    public void setCall() {
        call++;
    }

}
