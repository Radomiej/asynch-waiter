package pl.radomiej.waiter;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

import static java.lang.Thread.sleep;


public class Waiter {
    private Executor executor = Executors.newSingleThreadExecutor();

    private List<WaiterMethod> inQueue = Collections.synchronizedList(new ArrayList<>(100));
    private AtomicLong currentTime = new AtomicLong();
    private AtomicLong currentDelta = new AtomicLong();

    public Waiter() {
        executor.execute(this::run);
    }

    private void run() {
        while (true) {
            long oldTime = currentTime.getAndSet(System.currentTimeMillis());
            long delta = currentTime.get() - oldTime;
            currentDelta.set(delta);

            List<WaiterMethod> tmpMethods = new ArrayList<>(inQueue);
            tmpMethods.forEach(w -> processWaiterMethod(w));

            sleepForMoment(1);
        }
    }

    @SneakyThrows
    private void sleepForMoment(int sleepTimeInMs) {
        sleep(sleepTimeInMs);
    }

    private void processWaiterMethod(WaiterMethod w) {
        w.setTimeToInvoke(w.getTimeToInvoke() - currentDelta.get());
        if (w.getTimeToInvoke() > 0) return;

        WaiterMethod next = w.getInvoke().apply(w);
        inQueue.remove(w);
        if (next != null) invoke(next);

    }

    public void invoke(WaiterMethod waiterMethod) {
        inQueue.add(waiterMethod);
    }


}
