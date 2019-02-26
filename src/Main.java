import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main {
    // github上 nanohttp也有比较详细的案例介绍，https://github.com/NanoHttpd/nanohttpd
    // 单独运行main方法,即可在pc浏览器或者其他终端访问
    public static void main(String[] args) {

        new Main().HttpDataServerDemo();
//        new Main().HttpWebDemo();
    }



    // 1.数据服务器demo
    private void HttpDataServerDemo(){
        System.out.println("开始模拟数据服务器");
        try {
            NanoHttpDataServer server = new NanoHttpDataServer(8080);
            server.start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
        } catch (IOException e) {
            System.out.println("Couldn't start data server:\n" + e);
        }
    }
    // 2.web服务器demo
    private void HttpWebDemo(){
        System.out.println("开始模拟web服务器");
        try {
            NanoHttpWebServer server = new NanoHttpWebServer(8080);
            server.start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
        } catch (IOException e) {
            System.err.println("Couldn't start web server:\n" + e);
        }
    }
}
