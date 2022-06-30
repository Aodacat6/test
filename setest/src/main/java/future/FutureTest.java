package future;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author ：songdalin
 * @date ：2022/6/29 下午 6:33
 * @description：
 * @modified By：
 * @version: 1.0
 */
public class FutureTest {

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        //远程调用获取商品信息，主线程暂时用不到，主线程还要处理金额信息
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            //正在执行远程调用
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "success";
        }).thenApplyAsync(f -> {
            //继续使用上一个线程的执行结果
            if (f.equals("success")) {
                return "yes, it is ok ";
            }
            return "no, error";
        });
        //second
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            //正在执行远程调用
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "success2";
        });

        //主线程业务操作
        Thread.sleep(200);
        //主线程做完了，看下future怎么样了
        try {
            //必须都完成，将所有结果合并
            final CompletableFuture<String> future3 = CompletableFuture.allOf(future, future2).thenApply(f -> {
                System.out.println(Thread.currentThread().getName() + "  " + f);
                return String.valueOf(f);
            });

            long endAll = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() + "  " + future3.get() + "  " + (endAll -start));
/*            final String s = future.get();
            long f1 = System.currentTimeMillis() - start;
            System.out.println(s + "  " + f1);
            final String s1 = future2.get();
            long f2 = System.currentTimeMillis() - start;
            System.out.println(s1 + "  " + f2);
            long end = System.currentTimeMillis();
            System.out.println("use : " + (end - start));*/
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }
}
