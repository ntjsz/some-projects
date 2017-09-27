import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static java.nio.channels.SelectionKey.OP_ACCEPT;

public class Server {

    List<Info> infoList = new LinkedList<>();

    public void server() throws Exception {
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.bind(new InetSocketAddress(9898));
        channel.configureBlocking(false);

        Selector selector = Selector.open();
        channel.register(selector, OP_ACCEPT);

        while (selector.select() > 0) {
            Info info = new Info();
            Set<SelectionKey> keys = selector.selectedKeys();
            info.keySize = keys.size();

            keys.stream().forEach(k -> {
                info.list.add(k.readyOps());
            });
            infoList.add(info);
        }

        channel.close();
    }

    public static void main(String[] args) {
        Server s = new Server();

        new Thread(() -> {
            try {
                s.server();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();


        while (true) {
            w();
            System.out.println(s.infoList);
        }
    }

    public static void w() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    class Info {
        int keySize;
        List<Integer> list = new LinkedList<>();

        @Override
        public String toString() {
            return "size: " + keySize + " , list: " + list.toString();
        }
    }
}
