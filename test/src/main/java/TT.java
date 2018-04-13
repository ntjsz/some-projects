import java.io.*;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by hht on 2017.11.1.
 */
public class TT {
    static ByteBuffer response;
    static String re = "HTTP/1.1 200 OK\nContent-Length: 13\n\nhht-response\n";

    public static void main(String[] args) {
        try {
            testUrls();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testUrls() throws Exception{
        File file = new File("D://c.txt");
        file.setReadable(true);
        FileReader reader = new FileReader(file);
        BufferedReader reader1 = new BufferedReader(reader);


        List<String> list = new ArrayList<>();
        String s;
        while ((s = reader1.readLine()) != null) {
            String url = s + "/img_cache";
            boolean isExist = testConnect(url);
            if(isExist) {
                list.add(url);
            }
            System.out.println(url + "    " + isExist);
        }



        System.out.println(list);
    }


    public static void testIPs() {
        String src = "http://10.255.254.240";
        String dst = "D://b.txt";

        List<String> list = new ArrayList<>();

        int net2 = 255;
        int net3 = 255;
        for(int m = net2; m > -1; m--) {
            for(int n = net3; n > -1; n--) {
                String url = "http://10.255." + m + "." + n;
                boolean isExist = testConnect(url);
                if(isExist) {
                    list.add(url);
                }
                System.out.println(url + "    " + isExist);
            }
        }

        System.out.println(list);
    }


    private static boolean testConnect(String s) {
        try {
            URL url = new URL(s);
            URLConnection connection = url.openConnection();
            connection.setConnectTimeout(500);
            try {
                connection.connect();
            } catch (SocketTimeoutException e) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public static void print(Func fulei, Serializable a) {

        fulei.a(new String());
    }

}
