import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：songdalin
 * @date ：2022/6/23 下午 4:08
 * @description：
 * @modified By：
 * @version: 1.0
 */
public class test {


    public static void main(String[] args){
/*        ThreadLocal<Long> threadLocal = new ThreadLocal<>();

 *//*       threadLocal.set(111L);

        threadLocal.remove();

        threadLocal.remove();*//*

        System.out.println(threadLocal.get());*/

//        List<Object> list = new ArrayList<>();
//        list.toArray(new String[0]);

/*        Student1 student1 = new Student1();
        student1.setName("dd");
        Student1 student2 = new Student1();
        student2.setName("dd");
        for (int i = 0; i < 10; i++) {
            System.out.println(student1.hashCode());
            System.out.println(student2.hashCode());

        }*/
        int a = 1;
        System.out.println();

    }
}

@Data
class Student1 {
    private String name;
}