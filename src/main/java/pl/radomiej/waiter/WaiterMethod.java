package pl.radomiej.waiter;


import lombok.Data;

import java.util.function.UnaryOperator;

@Data
public class WaiterMethod {
    private long timeToInvoke;
    private UnaryOperator<WaiterMethod> invoke;
}
