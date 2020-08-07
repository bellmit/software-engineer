package pools;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/7/30 18:08
 * @Description
 */
public class CompletableFutureDemo {
    public static void main(String[] args) throws Exception, InterruptedException {

        CompletableFuture.supplyAsync(
                () -> {
                    System.out.println("Success");
                   // int a = 10 /0;
                    return 666;
                }
        ).whenComplete((t, u) -> {
                            System.out.println("ttttttttttt\t" + t);
                            System.out.println("uuuuuuuuuuu\t" + u);
                        })
         .exceptionally(fun -> {
                            System.out.println(fun.getMessage());
                            return 8888;
                        }).get();

    }
}
