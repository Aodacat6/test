package com.mycom.nodbweb.util;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author ：songdalin
 * @date ：2022/9/16 上午 11:14
 * @description：   异步任务处理
 * @modified By：
 * @version: 1.0
 */
public class AsyncUtil {

    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10 ,
            1000, TimeUnit.SECONDS, new LinkedBlockingDeque<>());


    public static void asyncRun(Runnable task) {
        threadPoolExecutor.execute(task);
    }

}
