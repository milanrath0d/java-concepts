package org.milan.misc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Get MAC Address of Current System
 *
 * @author Milan Rathod
 */
public class MacAddress {

    /**
     * Logger
     */
    private static final Logger LOG = LoggerFactory.getLogger(MacAddress.class);

    public String getMacAddress() {
        InetAddress ip;
        StringBuilder sb = new StringBuilder();
        try {
            ip = InetAddress.getLocalHost();

            LOG.debug("Current IP Address {} ", ip.getHostAddress());

            NetworkInterface network = NetworkInterface.getByInetAddress(ip);

            byte[] mac = network.getHardwareAddress();

            for (int i = 0; i < mac.length; i++) {

                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));

            }

            LOG.debug("Current IP Address {} ", ip.getHostAddress());

            return sb.toString();

        } catch (UnknownHostException | SocketException e) {
            LOG.error("Failed to get MAC Address of current machine {}", e.getMessage());
        }
        return null;
    }
}
