package beans;

import javax.ejb.Remote;

@Remote
public interface IRemote {

    public int getReturnCall();

    public String returnText();

    public String getText();

    public void setText(String text);

    public String getMessage();

    public void setMessage(String message);

    public String getT();

    public String getT1();

    public void setT1(String t1);

}
