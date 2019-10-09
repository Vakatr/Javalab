package beans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import myClass.ILocal;
import myClass.classSingleton;

@Named(value = "beanSFSB")
@SessionScoped
public class beanSFSB implements Serializable, IRemote {

    public beanSFSB() {
    }
    private String t1;
    private String text;
    private String message;
    @EJB
    classSingleton mySingleton;
    @EJB
    ILocal local;

    @PostConstruct
    public void kolcall() {
        mySingleton.setCall();
    }

    @Override
    public int getReturnCall() {
        return mySingleton.getCall();
    }

    @Override
    public String returnText() {
        setMessage(text);
        text = "";
        return "index";
    }

    @Override
    public String getT() {
        t1 = local.getTime();
        return "index";
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getT1() {
        return t1;
    }

    @Override
    public void setT1(String t1) {
        this.t1 = t1;
    }
}
