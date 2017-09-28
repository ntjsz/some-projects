import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static java.nio.channels.SelectionKey.*;

public class Client {
    public SocketChannel client() throws Exception {
        SocketChannel channel = SocketChannel.open();
        channel.connect(new InetSocketAddress("127.0.0.1", 9898));
        return channel;
    }


    public static void main(String[] args) {
        Client c = new Client();
        try {
            c.client();
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
