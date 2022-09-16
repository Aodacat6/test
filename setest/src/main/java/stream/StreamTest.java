package stream;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：songdalin
 * @date ：2022/9/15 下午 1:54
 * @description：
 * @modified By：
 * @version: 1.0
 */
//@Slf4j
public class StreamTest {

    public static void main(String[] args){
        List<Integer> list = new ArrayList<>();
        List<String> list1 = new ArrayList<>();
/*        list.add("dssf");
        list.add("dssf1");
        list.add("dssf2");
        list.add("dssf3");
        list.add("dssf4");
        list.add("dssf5");
        list.add("dssf6");
        list.add("dssf7");*/
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                list.add(i);
            }
        });
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                list.add(i);
            }
        });
/*        list.parallelStream().forEach(f -> {
            String name = Thread.currentThread().getName();
            System.out.println(f + ":    " + name);
        });*/
        thread.start();
        thread1.start();
    }
}
