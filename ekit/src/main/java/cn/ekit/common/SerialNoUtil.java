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

    enum SNT {
        /**
         * 测试
         */
        N1("001");

        private final String prefix;

        SNT(String prefix) {
            this.prefix = prefix;
        }
    }

    public static String getSerialNo(SNT snt) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyMMdd");
        LocalDateTime now = LocalDateTime.now();
        String format = dtf.format(now);
        // 当前天过去的秒
        int second = now.getHour() * 3600 + now.getMinute() * 60 + now.getSecond();
        // 加个随机数
        int randomInt = secureRandom.nextInt(1000);
        String no = String.format("%s%s%05d%03d", snt.prefix, format, second, randomInt);
        System.out.println(no);
        return no;
    }

    public static void main(String[] args) {

        for (int i = 0; i < 1000; ++i) {
            AsyncUtil.submit(() -> {
                getSerialNo(SNT.N1);
            });
        }
        AsyncUtil.shutdown();
        //System.out.println(getSerialNo(SNT.N1));
    }


}
