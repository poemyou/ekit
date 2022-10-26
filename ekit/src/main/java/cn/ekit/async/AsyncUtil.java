package cn.ekit.async;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author cheng
 * @date 2022/6/15 23:34
 * description
 */
public class AsyncUtil {

    private static ThreadPoolExecutor executors = new ThreadPoolExecutor(3, 10, 10,
            TimeUnit.MINUTES, new LinkedBlockingQueue<>(1000), new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            return new Thread("xxx") {
                @Override
                public void run() {
                    r.run();
                }
            };
        }
    });

    public static void submit(Runnable r){
        executors.submit(r);
    }

    public static void shutdown(){
        executors.shutdown();
    }

    public static void safeSleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
