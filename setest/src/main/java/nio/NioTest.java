package nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * @author ：songdalin
 * @date ：2022/6/28 上午 9:11
 * @description：
 * @modified By：
 * @version: 1.0
 */
public class NioTest {

    public static void main(String[] args) throws FileNotFoundException {
        String str = "222222222222222222222";
        ByteBuffer byteBuffer = ByteBuffer.allocate(str.length());
        System.out.println("========start=========");
        System.out.println("pos:  " + byteBuffer.position());
        System.out.println("limit:  " + byteBuffer.limit());
        System.out.println("cap:  " + byteBuffer.capacity());
        System.out.println("mark:  " + byteBuffer.mark());

        byteBuffer.put(str.getBytes(StandardCharsets.UTF_8));
        System.out.println("========put=========");
        System.out.println("pos:  " + byteBuffer.position());
        System.out.println("limit:  " + byteBuffer.limit());
        System.out.println("cap:  " + byteBuffer.capacity());
        System.out.println("mark:  " + byteBuffer.mark());

        byteBuffer.flip();

        System.out.println("========flip=========");
        System.out.println("pos:  " + byteBuffer.position());
        System.out.println("limit:  " + byteBuffer.limit());
        System.out.println("cap:  " + byteBuffer.capacity());
        System.out.println("mark:  " + byteBuffer.mark());

        byte[] bytes = new byte[byteBuffer.limit()];
        byteBuffer.get(bytes);
        System.out.println("========get=========");
        System.out.println("pos:  " + byteBuffer.position());
        System.out.println("limit:  " + byteBuffer.limit());
        System.out.println("cap:  " + byteBuffer.capacity());
        System.out.println("mark:  " + byteBuffer.mark());
        System.out.println(new String(bytes));

        /**
         *
         */
        //byteBuffer.remaining();

        /**
         *
         */
        //byteBuffer.reset();

        /**
         * rewind以后，可以将pos置为0，重新读取
         */
        byteBuffer.rewind();

        System.out.println("========rewind=========");
        System.out.println("pos:  " + byteBuffer.position());
        System.out.println("limit:  " + byteBuffer.limit());
        System.out.println("cap:  " + byteBuffer.capacity());
        System.out.println("mark:  " + byteBuffer.mark());


        byte[] bytes1 = new byte[byteBuffer.capacity()];
        byteBuffer.get(bytes1);
        System.out.println("========get2=========");
        System.out.println("pos2:  " + byteBuffer.position());
        System.out.println("limit2:  " + byteBuffer.limit());
        System.out.println("cap2:  " + byteBuffer.capacity());
        System.out.println("mark2:  " + byteBuffer.mark());
        System.out.println("get2: " + new String(bytes));



    }
}
