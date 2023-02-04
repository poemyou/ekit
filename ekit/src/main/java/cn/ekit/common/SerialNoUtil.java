package cn.ekit.common;

import cn.ekit.async.AsyncUtil;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author cheng
 * @date 2022/10/26 20:42
 * description
 */
public class SerialNoUtil {

    private static final SecureRandom secureRandom = new SecureRandom();

    public static String getSerialNo(String prefix) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyMMdd");
        LocalDateTime now = LocalDateTime.now();
        String format = dtf.format(now);
        // 当前天过去的秒
        int second = now.getHour() * 3600 + now.getMinute() * 60 + now.getSecond();
        // 加个随机数
        int randomInt = secureRandom.nextInt(1000);
        String no = String.format("%s%s%05d%03d", prefix, format, second, randomInt);
        System.out.println(no);
        return no;
    }

    public static void main(String[] args) {

        for (int i = 0; i < 1000; ++i) {
            AsyncUtil.submit(() -> {
                getSerialNo("001");
            });
        }
        AsyncUtil.shutdown();
        //System.out.println(getSerialNo(SNT.N1));
    }


}
