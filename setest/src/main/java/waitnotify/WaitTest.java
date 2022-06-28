package waitnotify;

/**
 * @author ：songdalin
 * @date ：2022/6/28 上午 9:15
 * @description：
 * @modified By：
 * @version: 1.0
 */
public class WaitTest {

    /**
     *
     * 线程间通信
     *
     */

    private int size = 0;

    public synchronized void add() {
        size++;
        notify();
    }

    public synchronized int get() {
        if (size < 1) {
            try {
                System.out.println(Thread.currentThread().getName() + "等待。。。。");
                wait();
            } catch (InterruptedException e) {
            }
        }
        System.out.println("ok。。。。");
        size--;
        return size;
    }

    public static void main(String[] args){
        WaitTest waitTest = new WaitTest();
        new Thread(() -> {
            System.out.println(waitTest.get());
        }).start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        waitTest.add();

    }
}
