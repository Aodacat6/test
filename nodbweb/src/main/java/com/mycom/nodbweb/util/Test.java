package com.mycom.nodbweb.util;

/**
 * @author ：songdalin
 * @date ：2022/9/16 上午 11:18
 * @description：
 * @modified By：
 * @version: 1.0
 */
public class Test {

    public static void main(String[] args){
        System.out.println("in");
        AsyncUtil.asyncRun(() -> {
            System.out.println(Thread.currentThread().getName() + "====in====");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "====out====");
        });
        System.out.println("out");
    }
}
