import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

public class Client {
    public SocketChannel client() throws Exception {
        SocketChannel channel = SocketChannel.open();
        channel.connect(new InetSocketAddress("127.0.0.1", 9898));
        return channel;
    }


    public static void main(String[] args) {
        Client c = new Client();
        try {
            SocketChannel channel = c.client();
            System.out.println("local: " + channel.getLocalAddress() + ", remote: " + channel.getRemoteAddress());
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
