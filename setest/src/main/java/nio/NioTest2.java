package nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * @author ：songdalin
 * @date ：2022/7/1 下午 2:12
 * @description：
 * @modified By：
 * @version: 1.0
 */
public class NioTest2 {

    public static void main(String[] args) throws IOException {
        final FileInputStream fileInputStream = new FileInputStream("C:\\Users\\22023964\\Desktop\\新建文本文档.txt");
        final FileChannel channel = fileInputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(2048);
        byte[] bytes = new byte[2048];
        while (channel.read(byteBuffer) > 0) {
            //切换读模式.position = 0
            byteBuffer.flip();
            int i = 0;
            while (byteBuffer.hasRemaining()) {  //position < limit
                bytes[i] = byteBuffer.get();   //读一位。position + 1
                i++;
            }
            //如果剩余字节小于bytes数组长度，会报错
/*            byteBuffer.get(bytes);*/
            System.out.println(new String(bytes));
            //准备继续读操作，position = 0
            byteBuffer.clear();
            bytes = new byte[2048];
        }

/*        byteBuffer.put(str.getBytes(StandardCharsets.UTF_8));

        byteBuffer.flip();


        byte[] bytes = new byte[byteBuffer.limit()];
        byteBuffer.get(bytes);*/
        channel.close();


    }
}
