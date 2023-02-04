package cn.ekit.common;

import cn.ekit.common.consts.SerialNoTypeEnum;
import org.apache.commons.lang3.StringUtils;

/**
 * @author cheng
 * @date 2023/2/4 19:01
 * description
 */
public class SerialNoPlusUtil {

    public static String genShortSerialNo(SerialNoTypeEnum serialNoTypeEnum) {
        String typeCode = serialNoTypeEnum.getCode();
        StringBuilder stringBuilder = new StringBuilder();
        // 业务编码
        stringBuilder.append(typeCode);
        // 机器IP

        return stringBuilder.toString();
    }

    private static String getIpHash() throws Exception {
        return String.format("02d", HashUtil.addHash(IpUtil.getLocalIp(), 97));

        //return StringUtils.leftPad("" + HashUtil.addHash(IpUtil.getLocalIp(), 97), 2, "0");
    }

}
