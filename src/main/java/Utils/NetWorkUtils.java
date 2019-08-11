package Utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * 网络工具类
 */
public class NetWorkUtils {

    /**
     * 获得本机IP地址
     * @return
     */
    public static String getHostIp() {
        try {
            for (Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces(); interfaces.hasMoreElements(); ) {
                NetworkInterface networkInterface = interfaces.nextElement();
                if (networkInterface.isLoopback() || networkInterface.isVirtual() || !networkInterface.isUp()) {
                    continue;
                }
                Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();

                while (addresses.hasMoreElements()) {
                    InetAddress address = addresses.nextElement();
                    byte[] addressBytes = address.getAddress();
                    if (addressBytes != null && addressBytes.length == 4) {
                        return address.getHostAddress();
                    }
                }
            }
        } catch (SocketException ex) {

        }
        return null;
    }
}
