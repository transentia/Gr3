package gr3
class NetworkUtils {

  // NB: MUST have trailing slash

  static final URI = "http://${PrefsUtils.getServer()}:${PrefsUtils.getPort()}/Gr3Server/totalizer/"
  static {
    println "Connecting to URI: ${URI}"
  }

  static final hostAddress = NetworkUtils.getCurrentEnvironmentNetworkIp()

  /**
   * @return the current environment's IP address, taking into account the Internet connection to any of the available
   *         machine's Network interfaces. Examples of the outputs can be in octatos or in IPV6 format.
   *         <p/>
   *         fec0:0:0:9:213:e8ff:fef1:b717%4 siteLocal: true isLoopback: false isIPV6: true
   *         ============================================ 130.212.150.216 <<<<<<<<<<<------------- This is the one we
   *         want to grab so that we can. siteLocal: false address the DSP on the network. isLoopback: false isIPV6:
   *         false ==> lo ============================================ 0:0:0:0:0:0:0:1%1 siteLocal: false isLoopback:
   *         true isIPV6: true ============================================ 127.0.0.1 siteLocal: false isLoopback:
   *         true isIPV6: false
   */
  // http://www.jguru.com/faq/view.jsp?EID=15835

  public static String getCurrentEnvironmentNetworkIp() {
    Enumeration<NetworkInterface> netInterfaces = null;
    try {
      netInterfaces = NetworkInterface.getNetworkInterfaces();
    } catch (SocketException e) {
      e.printStackTrace(System.err);
      return "127.0.0.1";
    }

    while (netInterfaces.hasMoreElements()) {
      NetworkInterface ni = netInterfaces.nextElement();
      Enumeration<InetAddress> address = ni.getInetAddresses();
      while (address.hasMoreElements()) {
        InetAddress addr = address.nextElement();
        if (!addr.isLoopbackAddress() && addr.isSiteLocalAddress()
                && !(addr.getHostAddress().indexOf(":") > -1)) {
          return addr.getHostAddress();
        }
      }
    }
    try {
      return InetAddress.getLocalHost().getHostAddress();
    } catch (UnknownHostException e) {
      e.printStackTrace(System.err);
      return "127.0.0.1";
    }
  }
}
