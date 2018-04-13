import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;

public class Walk {
    public static void main(String[] args) throws Exception{
        test0();
    }

    public static void test1() throws Exception{
        File f = new File("D:\\work\\sd\\code\\server\\branches\\1.3.0.0\\20171214\\world\\bin");
        URI uri = f.toURI();
        Paths.get(uri);
    }

    public static void test0() throws Exception{
        long start = System.nanoTime();
        String path = "D:\\work\\sd\\code\\server\\branches\\1.3.0.0\\20171214\\world\\bin";
        File dir = new File(path);
        Path rootPath = Paths.get(dir.toURI());
        Set<Path> result = new HashSet<>();
        FileVisitorM visitorM = new FileVisitorM(rootPath, result);
        Files.walkFileTree(rootPath, visitorM);

        long end = System.nanoTime();
        System.out.println((end - start) / 1000_000_000);
        System.out.println();


        start = System.nanoTime();
        result.parallelStream().forEach(p->{
            try {
                read(p);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        end = System.nanoTime();
        System.out.println((end - start) / 1000_000_000);

        AtomicInteger i = new AtomicInteger(0);
        System.out.println(i);
        System.out.println(i.getAndIncrement());
    }


    public static void read(Path path) throws Exception{
        FileChannel channel = FileChannel.open(path, StandardOpenOption.READ);
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while (channel.read(buffer) > 0){
            buffer.flip();
            buffer.clear();
        }

        channel.close();
    }


    public static class FileVisitorM extends SimpleFileVisitor<Path> {
        private Set<Path> classes;
        private Path root;

        public FileVisitorM(Path root, Set<Path> container) {
            this.root = root;
            this.classes = container;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            String fName = file.toString();
            if(fName.endsWith(".class")) {
                classes.add(file);
            }
            return super.visitFile(file, attrs);
        }
    }
}
