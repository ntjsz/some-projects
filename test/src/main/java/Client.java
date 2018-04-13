import java.io.InputStream;
import java.net.SocketAddress;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.SocketChannel;


public class Client {


    public static void main(String[] args) {

        try {
            URL url = new URL("http://127.0.0.1:8080/zone_sd/js/sd.sql");
            URLConnection connection = url.openConnection();
            InputStream inputStream = connection.getInputStream();

            byte[] buffer = new byte[1024];

            int count;
            while ((count = inputStream.read(buffer)) > 0) {
                System.out.write(buffer, 0, count);
            }

            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
