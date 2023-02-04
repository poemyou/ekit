package cn.ekit.common;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Locale;

/**
 * @author cheng
 * @date 2023/2/4 19:12
 * description
 */
public class IpUtil {

    public static String getLocalIp() throws Exception {
        if (isWindowsOs()) {
            return InetAddress.getLocalHost().getHostAddress();
        } else {
            return getLinuxLocalIp();
        }
    }

    private static String getLinuxLocalIp() throws Exception {
        // 获取本机上所有网络接口，不是物理网卡，是虚拟网卡
        final Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        while (networkInterfaces.hasMoreElements()) {
            // 网络接口，网卡,en0是无线网卡
            NetworkInterface networkInterface = networkInterfaces.nextElement();
            final String name = networkInterface.getName();
            System.out.println(name);
            final Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
            if (!name.contains("docker") && !name.contains("lo")) {
                while (inetAddresses.hasMoreElements()) {
                    final InetAddress inetAddress = inetAddresses.nextElement();
                    //System.out.println(inetAddress.getHostAddress());
                    if (!inetAddress.isLoopbackAddress()) {
                        final String hostAddress = inetAddress.getHostAddress();
                        if (!hostAddress.contains("::") && !hostAddress.contains("0:0:") &&
                                !hostAddress.contains("fe80")) {
                            return hostAddress;
                        }
                    }
                }
            }
        }
        return "";
    }

    public static boolean isWindowsOs() {
        final String osName = System.getProperty("os.name");
        return osName.toLowerCase().contains("windows");
    }

    public static void main(String[] args) throws Exception {
        System.out.println(getLocalIp());
    }
}
