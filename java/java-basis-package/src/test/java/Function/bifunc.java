package Function;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2021/1/19 17:16
 * @Description
 */
public class bifunc {
    public static void main(String[] args) {

        //BiFunction
        BiFunction<Integer, Integer, Integer> biFunction1 = (x, y) -> x + y;
        Integer apply2 = biFunction1.apply(1, 2);
        System.out.println(apply2);

        BiConsumer<Integer,Integer> biConsumer = (x,y) -> System.out.println("Say Hello");
        biConsumer.accept(1, 1);

    }
}
