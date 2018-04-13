import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

import static java.nio.channels.SelectionKey.*;

public class Server {

    Queue<Info> infoList = new ConcurrentLinkedQueue<>();

    public void server() throws Exception {
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.bind(new InetSocketAddress(9898));
        channel.configureBlocking(false);

        Selector selector = Selector.open();
        channel.register(selector, OP_ACCEPT);

        while (true) {
            if (selector.select() > 0) {
                Info info = new Info();
                Set<SelectionKey> keys = selector.selectedKeys();
                info.keySize = keys.size();

                Iterator<SelectionKey> iterator = keys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey k = iterator.next();
                    info.list.add(k.readyOps());
                    try {

                        SelectableChannel socketChannel = k.channel();
                        if(socketChannel instanceof ServerSocketChannel) {
                            SocketChannel c = ((ServerSocketChannel) socketChannel).accept();
                            c.configureBlocking(false);
                            c.register(selector, OP_READ | OP_CONNECT);
                        }

                        if(socketChannel instanceof SocketChannel) {
                            if(k.isReadable()) {
                                SocketChannel c = (SocketChannel) k.channel();
                                ByteBuffer b = ByteBuffer.allocate(1);
                                c.read(b);
                                System.out.println(b.toString());
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    iterator.remove();
                }
                infoList.offer(info);
            }
        }


        //channel.close();
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
