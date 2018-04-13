import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DownLoad {

    static String url_prefix = "http://10.255.254.239/img_cache/photo/";
    static String fileName = "D://a.txt";
    static String oldFile = "D://old_ids.txt";
    static String dirName = "E://imgs/";

    static List<String> ids;
    static String targetId = "113264";
    static String targetIdAndHash;

    public static void main(String[] args) {
        try {
            Step0();
            Step1();
            boolean isExist = Step2();
            System.out.println("Exist? " + isExist);
            if(isExist) {
                Step3();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // download web page
    public static void Step0() throws Exception {
        download(url_prefix, fileName);
    }

    public static void download(String src, String dst) throws Exception{
        URL url = new URL(src);
        URLConnection connection = url.openConnection();
        InputStream inputStream = connection.getInputStream();
        //InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");

        File file = new File(dst);
        file.createNewFile();
        file.setWritable(true);
        FileOutputStream outputStream = new FileOutputStream(file);
        //OutputStreamWriter writer = new OutputStreamWriter(outputStream);

        byte[] buffer = new byte[1024];
        //char[] buffer = new char[1024];
        //StringBuilder sb = new StringBuilder();

        int count;
        while ((count = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, count);
        }

        inputStream.close();
        outputStream.close();
    }

    // extract ids
    public static void Step1() throws Exception {
        ids = new LinkedList<>();

        String s = fileContent();
        Pattern pattern = Pattern.compile(">(\\d+_\\d+)</a>");
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            String id = matcher.group(1);
            ids.add(id);
        }
    }

    private static String fileContent() throws Exception{
        Path path = FileSystems.getDefault().getPath(fileName);
        FileChannel channel = FileChannel.open(path, StandardOpenOption.READ);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        StringBuilder sb = new StringBuilder();
        Charset utf8 = Charset.defaultCharset();

        while (channel.read(buffer) > 0){
            buffer.flip();
            CharBuffer charBuffer= utf8.decode(buffer);
            sb.append(charBuffer);
            buffer.clear();
        }

        channel.close();

        return sb.toString();
    }


    // find target id
    public static boolean Step2() {
        for(String id : ids) {
            if(id.contains(targetId)) {
                targetIdAndHash = id;
                return true;
            }
        }
        return false;
    }


    // download target image
    public static void Step3() throws Exception{
        download(url_prefix + targetIdAndHash, dirName + targetIdAndHash + ".jpg");
    }


    // download images
    public static void downloadImages(List<String> ids) throws Exception{
        File dir = new File(dirName);
        dir.setWritable(true);
        dir.mkdirs();

        System.out.println("total: " + ids.size());
        int cout = 0;
        for(String s : ids) {
            if(s.contains(targetId)){
                System.out.println("got it!");
            }

            if(cout % 100 == 1)
                System.out.println("now: " + cout);

            download(url_prefix + s, dirName + s + ".jpg");

            cout++;
        }
    }
}
