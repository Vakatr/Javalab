package myClass;

import java.util.Date;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

@Stateless
public class classSSB implements ILocal {

    //@Interceptors(classI.class)
    @Override
    public String getTime() {
        Date date = new Date();
        return date.toString();
    }
}
