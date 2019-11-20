package pl.radomiej.waiter;

import org.junit.Test;

import java.util.function.UnaryOperator;

public class GeneralTest {

    @Test
    public void asynchRequest() throws InterruptedException {
        UnaryOperator<WaiterMethod> print1 = wm -> print1(wm);

        Waiter waiter = new Waiter();
        waiter.invoke(Waiters.waitForSeconds(1, print1));

        Thread.sleep(5000);

    }

    private WaiterMethod print1(WaiterMethod wm) {
        System.out.println("PRINT 1");
        return Waiters.invokeNext(this::print2);
    }

    private WaiterMethod print2(WaiterMethod wm) {
        System.out.println("PRINT 2");
        return Waiters.waitForSeconds(2, this::print3);
    }

    private WaiterMethod print3(WaiterMethod w) {
        System.out.println("PRINT 3");
        return null;
    }


}
