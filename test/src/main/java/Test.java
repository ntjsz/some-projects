import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.channels.SocketChannel;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.IntConsumer;

public class Test {

    public static void main(String[] args) throws IOException {
        Thread c = Thread.currentThread();
        Thread t = new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            c.interrupt();
        });
        t.start();

        t1();
    }

    public static void t1() throws IOException {
        try(Socket channel = new Socket(InetAddress.getLocalHost(), 1234)) {
            Scanner in = new Scanner(channel.getInputStream());
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("1");
                if(in.hasNextLine()) {
                    System.out.println("2");
                    in.nextLine();
                }
            }
        } finally {
            System.out.println("3");
        }
    }

    public static void t2() throws IOException {
        try(SocketChannel channel = SocketChannel.open(new InetSocketAddress(InetAddress.getLocalHost(), 1234))) {
            Scanner in = new Scanner(channel);
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("1");
                if(in.hasNextLine()) {
                    System.out.println("2");
                    in.nextLine();
                }
            }
        } finally {
            System.out.println("3");
        }
    }
}
