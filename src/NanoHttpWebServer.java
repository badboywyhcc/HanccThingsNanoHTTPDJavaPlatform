import java.io.IOException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

public class NanoHttpWebServer extends NanoHTTPD {
    public NanoHttpWebServer(int port) throws IOException{
        super(port);
        System.out.println("\n在PC浏览器或者其他终端访问 http://localhost:"+port+"   or   "+NanoHttpWebServer.getCurrentDeviceIP()+":"+port+"\n");
    }
    public NanoHttpWebServer(String hostname, int port) throws IOException {
        super(hostname, port);
        System.out.println("\n在PC浏览器或者其他终端访问 http://localhost:"+port+"   or   "+NanoHttpWebServer.getCurrentDeviceIP()+":"+port+"\n");
    }
    @Override
    public NanoHTTPD.Response serve(NanoHTTPD.IHTTPSession session) {
        String msg = "<html><body><h1>Hello server</h1>\n";
        Map<String, String> parms = session.getParms();
        if (parms.get("username") == null) {
            msg += "<form action='?' method='get'>\n  <p>Your name: <input type='text' name='username'></p>\n" + "</form>\n";
        } else {
            msg += "<p>Hello, " + parms.get("username") + "!</p>";
        }
        return newFixedLengthResponse (msg + "</body></html>\n");
    }

    // 获取本地IP地址
    public static String getCurrentDeviceIP(){
        InetAddress addr = null;
        try {
            addr = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String ip=addr .getHostAddress().toString(); //获取本机ip
        return ip;
    }
}
