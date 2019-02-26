import com.google.gson.Gson;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;

public class NanoHttpDataServer extends NanoHTTPD {
    public NanoHttpDataServer(int port) {
        super(port);
        System.out.println("\n在PC浏览器或者其他终端访问 http://localhost:"+port+"   or   "+NanoHttpWebServer.getCurrentDeviceIP()+":"+port+"\n");
    }
    public NanoHttpDataServer(String hostname, int port) {
        super(hostname, port);
        System.out.println("\n在PC浏览器或者其他终端访问 to http://localhost:"+port+"   or   "+NanoHttpWebServer.getCurrentDeviceIP()+":"+port+"\n");
    }

    /**
     * Override this to customize the server.
     * <p/>
     * <p/>
     * (By default, this returns a 404 "Not Found" plain text error response.)
     *
     * @param session The HTTP session
     * @return HTTP response, see class Response for details
     */
    @Override
    public Response serve(IHTTPSession session) {
        Response response;
        if (session.getMethod().name().equalsIgnoreCase("get")){
            System.out.println(session.getUri());

            try {
                session.parseBody(new HashMap<String, String>());//获取请求参数之前需要调一下这个方法
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ResponseException e) {
                e.printStackTrace();
            }

//            String responseresult = "{\"code\":0,\"data\":[{\"xiaohua\":{\"height\":175,\"weight\":\"55\",\"age\":\"18\",\"sex\":\"F\",\"course\":[\"english\",\"Chinese\",\"mathematics\"],\"sport\":[\"football\",\"basketball\"]}},{\"xiaoming\":{\"height\":\"180\",\"weight\":\"70\",\"age\":\"18\",\"sex\":\"M\",\"course\":[\"english\",\"Chinese\",{\"mathematics\":[\"daishu\",\"jihe\"]}]}}]}";
            String responseresult = simulateJsonData();
            response = new Response(Response.Status.OK, "text/plain; charset=UTF-8", responseresult);
        }else{
            response = new Response(Response.Status.OK, "text/plain; charset=UTF-8", "{\"code\":1,\"data\":\"not found\" }");
        }

        //response = new Response(Response.Status.OK, "text/plain; charset=UTF-8", "{\"code\":1,\"data\":\"not found\" }");
        return response;
    }

    // 模拟json数据
    private String simulateJsonData(){
        ArrayList courseList = new ArrayList();
        courseList.add("english");
        courseList.add("Chinese");
        courseList.add("mathematics");

        Person Rango = new Person("Rango", 175, 70,18, true,courseList);
        Person Beans = new Person("Beans", 155, 45,17, false,courseList);

        ArrayList studentList = new ArrayList();
        studentList.add(Rango);
        studentList.add(Beans);

        HashMap<String,Object> statusMap = new HashMap<String,Object>();
        statusMap.put("code", 0);
        statusMap.put("data", studentList);

        Gson gson = new Gson();
        String jsonstr = gson.toJson(statusMap);

//        System.out.println("🎾trans:   "+jsonstr);
        return jsonstr;
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
