package myClass;

import javax.annotation.PostConstruct;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class classI {

    @AroundInvoke  //
    public Object LogAction(InvocationContext context) throws Exception {
        System.out.println("object - " + context.getTarget().getClass());
        System.out.println("method - " + context.getMethod());
        return context.proceed();
    }

    /**
     *
     * @param context
     */
    @PostConstruct
    public void logCreate(InvocationContext context) {
        try {
            System.out.println("create - " + context.getTarget().getClass());
        } catch (Exception e) {
            System.out.println("Ошибка" + e);
        }
    }
}
