package lib;

import java.util.function.BooleanSupplier;

public class Conditional {

    private BooleanSupplier condition = () -> true;

    public void condition(BooleanSupplier condition) {
        this.condition = condition;
    }

    public BooleanSupplier condition() {
        return condition;
    }
}
