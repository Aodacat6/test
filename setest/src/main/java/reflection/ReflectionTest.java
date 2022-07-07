package reflection;

import java.lang.reflect.Field;

/**
 * @author ：songdalin
 * @date ：2022/7/7 上午 9:47
 * @description：
 * @modified By：
 * @version: 1.0
 */
public class ReflectionTest {

    public static void main(String[] args){
        Stu stu = new Stu();
        stu.setName("damiao");

        try {
            final Field field = stu.getClass().getDeclaredField("name");
            field.setAccessible(true);
            final Object o = field.get(stu);
            System.out.println(o);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
