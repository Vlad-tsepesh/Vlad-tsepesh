package exercises.standart.functional.interfaces.ex3;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.*;

public class FunctionUtils {

    public static Supplier<Integer> getInfiniteRange() {
        AtomicInteger i = new AtomicInteger(0);
        return i::getAndIncrement;
//        return new Supplier<Integer>() {
//            int i = 0;
//            @Override
//            public Integer get() {
//                return i++;
//            }
//        };
    }

    public static void main(String[] args) {
        Supplier<Integer> sup = getInfiniteRange();
        for(int i = 0; i < 5; i++) {
            System.out.print(sup.get() + " ");
        }

        System.out.println();

        Supplier<Integer> sup1 = getInfiniteRange();
        Supplier<Integer> sup2 = getInfiniteRange();

        for(int i = 0; i < 5; i++) {
            System.out.print(sup1.get() + " " + sup2.get() + " ");
        }
    }
}