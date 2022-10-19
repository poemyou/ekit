package cn.ekit.async;

/**
 * @author cheng
 * @date 2022/6/15 23:34
 * description
 */
public class AsyncUtil {

    public static void safeSleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
