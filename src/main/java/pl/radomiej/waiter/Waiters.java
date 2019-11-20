package pl.radomiej.waiter;

import java.util.function.UnaryOperator;

public class Waiters {
    public static WaiterMethod waitForSeconds(int seconds, UnaryOperator<WaiterMethod> waiterMethodFunction) {
        WaiterMethod waiterMethod = new WaiterMethod();
        waiterMethod.setTimeToInvoke(seconds * 1000);
        waiterMethod.setInvoke(waiterMethodFunction);
        return waiterMethod;
    }

    public static WaiterMethod invokeNext(UnaryOperator<WaiterMethod> waiterMethodFunction) {
        WaiterMethod waiterMethod = new WaiterMethod();
        waiterMethod.setTimeToInvoke(0);
        waiterMethod.setInvoke(waiterMethodFunction);
        return waiterMethod;
    }

}
