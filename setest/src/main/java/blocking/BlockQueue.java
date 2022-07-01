package blocking;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author ：songdalin
 * @date ：2022/7/1 上午 9:32
 * @description：
 * @modified By：
 * @version: 1.0
 */
public class BlockQueue {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> blockQueue = new ArrayBlockingQueue<>(10);

        final Thread thread = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                //不起作用
/*                System.out.println("当前线程："+Thread.currentThread().getName());
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("检查到线程中断，跳出循环");
                    break;
                }*/
                System.out.println("装入" + i);
                try {
                    blockQueue.put(String.valueOf(i));
                } catch (InterruptedException e) {
                    System.out.println("中断了" + e.toString());
                    //在这里跳出线程
                    break;
                }
            }
        });
        thread.start();

/*        Thread.sleep(1000);
        System.out.println("main中断前" + thread.isInterrupted());
        thread.interrupt();
        System.out.println("main中断后" + thread.isInterrupted());
        Thread.sleep(1000);
        System.out.println("main中断后1s" + thread.isInterrupted());*/

        while (true) {
            System.out.println("拿到了：" + blockQueue.take());
        }

    }
}
