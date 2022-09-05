package future;

import java.util.List;
import java.util.concurrent.*;

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
        long start1 = System.currentTimeMillis();
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
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
        long start2 = System.currentTimeMillis();
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
        //Thread.sleep(200);
        //主线程做完了，看下future怎么样了
        try {
            //必须都完成，将所有结果合并
/*            final CompletableFuture<String> future3 = CompletableFuture.allOf(future, future2).thenApply(f -> {
                System.out.println(Thread.currentThread().getName() + "  " + f);
                return String.valueOf(f);
            });*/

            CompletableFuture.allOf(future, future2).join();
            System.out.println((System.currentTimeMillis() - start1) + "    " + future.get());
            System.out.println((System.currentTimeMillis() - start2) + "    " + future2.get());
/*            System.out.println(join);
            long endAll = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName()  + "  " + (endAll -start));*/
/*            final String s = future.get();
            long f1 = System.currentTimeMillis() - start;
            System.out.println(s + "  " + f1);
            final String s1 = future2.get();
            long f2 = System.currentTimeMillis() - start;
            System.out.println(s1 + "  " + f2);
            long end = System.currentTimeMillis();
            System.out.println("use : " + (end - start));*/
            System.out.println("total use : " + (System.currentTimeMillis() - start));

            long start3 = System.currentTimeMillis();
            FutureTask<String> future3 = new FutureTask(() -> {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "success";
            });
            new Thread(future3).start();
            future3.get();
            FutureTask<String> future4 = new FutureTask(() -> {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "success2";
            });
            new Thread(future4).start();
            future4.get();
            System.out.println("total2 use: " + (System.currentTimeMillis() - start3));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
