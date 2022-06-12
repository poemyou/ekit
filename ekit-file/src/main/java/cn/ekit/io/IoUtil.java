package cn.ekit.io;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author cheng
 * @date 2022/5/21 15:48
 * description
 */
public class IoUtil {

    public static void closeQuietly(Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
