package blocking;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ：songdalin
 * @date ：2022/7/1 上午 10:05
 * @description：
 * @modified By：
 * @version: 1.0
 */
public class RenteenLockInterrupt {

    public static void main(String[] args){

        ReentrantLock lock = new ReentrantLock();

        final Thread thread = new Thread(() -> {
            try {

                lock.lockInterruptibly();
                try {
                    System.out.println("get锁。。。。。");
                    Thread.sleep(5000);
                }finally {
                    lock.unlock();
                    System.out.println("释放锁....");
                }



            } catch (InterruptedException e) {
                System.out.println("中断了。。。");
                e.printStackTrace();
            }
        });
        thread.start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {

        }
        System.out.println("子线程中断...");
        thread.interrupt();


    }

}
