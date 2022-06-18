package cn.ekit.file;


import cn.ekit.io.IoUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author cheng
 * @date 2022/5/21 15:18
 * description
 */
public class FileUtil {

    /**
     * 读取文件内容
     *
     * @param file 文件全路径
     * @return 文件内容
     */
    public static String readFile(String file) throws IOException {
        if (StringUtils.isBlank(file)) {
            return null;
        }
        FileInputStream fileInputStream;
        fileInputStream = new FileInputStream(file);
        ByteArrayOutputStream out;
        out = new ByteArrayOutputStream(10240);
        byte[] buff = new byte[2048];
        int len = 0;
        while ((len = fileInputStream.read(buff, 0, buff.length)) != -1) {
            out.write(buff, 0, len);
        }
        out.flush();
        String ret = new String(out.toByteArray());
        IoUtil.closeQuietly(out);
        IoUtil.closeQuietly(fileInputStream);
        return ret;
    }
}
