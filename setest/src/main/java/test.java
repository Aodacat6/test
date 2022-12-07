import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author ：songdalin
 * @date ：2022/6/23 下午 4:08
 * @description：
 * @modified By：
 * @version: 1.0
 */
public class test {


    public static void main(String[] args){
/*
        JSONUtil
        Student1 student1 = new Student1();
        student1.setName("ssss");
        System.out.println((Object) student1.hashCode());

        Student1 student2 = new Student1();
        student2.setName("ssss");
        System.out.println((Object) student2.hashCode());*/

//        Integer a = 1;
//        Integer b = 1;
//        Integer c = new Integer(1);
//
//        System.out.println(Objects.equals(a, c));
//
//        System.out.println(a == c);

 /*       String str = "sdff";
        final String[] split = str.split(",");
        System.out.println(split);*/
        //System.out.println(null instanceof String);

/*        BigDecimal a = new BigDecimal("0");
        BigDecimal b = new BigDecimal("0.0");

        System.out.println(a.compareTo(b));*/
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

    /*    int a = 1;
        switch (a) {
            case 2:
                System.out.println("2222");
        }*/

/*        ThreadLocal<Long> threadLocal = new ThreadLocal<>();

 *//*       threadLocal.set(111L);

        threadLocal.remove();

        threadLocal.remove();*//*

        System.out.println(threadLocal.get());*/

//        List<Object> list = new ArrayList<>();
//        list.toArray(new String[0]);

/*        Student1 student1 = new Student1();
        student1.setName("dd");
        Student1.Room room = new Student1.Room();
        room.setId(1);
        student1.setRooms(Arrays.asList(room));
        Student1 student2 = new Student1();
        student2.setName("dd");
        Student1.Room room2 = new Student1.Room();
        room.setId(12);
        student2.setRooms(Arrays.asList(room2));
        //for (int i = 0; i < 10; i++) {
            System.out.println(student1.hashCode());
            System.out.println(student2.hashCode());*/

        //}
       // NumberUtils

    }
}

@Data
class Student1 {
    private String name;
    private List<Room> rooms;

    @Setter
    @ToString
    public static class Room{
        private Integer id;

    }
}

