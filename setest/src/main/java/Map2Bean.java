import lombok.Data;

import java.io.PipedReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author ：songdalin
 * @date ：2022/6/20 上午 10:17
 * @description：
 * @modified By：
 * @version: 1.0
 */
public class Map2Bean {

    public static void main(String[] args) throws InvocationTargetException, InstantiationException, IllegalAccessException {
       // System.out.println("ddd");
        Map<String, Object> map = new HashMap<>();
        map.put("name", "小明");
        map.put("age", 82);
        map.put("sum", 23.1);
        Student o = map2Bean(map, Student.class);
        System.out.println(o);
    }

    public static<T> T map2Bean(Map<String, Object> map, Class<T> tClass) throws InstantiationException, IllegalAccessException, InvocationTargetException {
        final T t = tClass.newInstance();
        final Method[] methods = tClass.getDeclaredMethods();
        final Map<String, Method> setMethodNameMap = Arrays.stream(methods).filter(f -> f.getName().contains("set"))
                .collect(Collectors.toMap(Method::getName, Function.identity()));
        final Field[] declaredFields = tClass.getDeclaredFields();
        //final Set<String> fieldNames = Arrays.stream(declaredFields).map(f -> f.getName()).collect(Collectors.toSet());
/*        if (Collections.isEmpty(xx)) {

        }*/
        for (Field field : declaredFields) {
            String fieldName = field.getName();
            String shouldSetMethodName = "set" + firstNameDaxie(fieldName);
            if (!setMethodNameMap.keySet().contains(shouldSetMethodName)) {
                continue;
            }
            final Method method = setMethodNameMap.get(shouldSetMethodName);
            final Object value = map.get(fieldName);
            if (value == null) {
                continue;
            }
            setBeanValue(field, method, t, value);
        }
        return t;
    }

    private static <T> void setBeanValue(Field field, Method method, T t, Object value) {
        try {
            //特殊类型单独处理
            if (Objects.equals(field.getType(), BigDecimal.class)) {
                method.invoke(t, new BigDecimal(value.toString()));
                return;
            }
            method.invoke(t, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    public static String firstNameDaxie(String srr) {
        final char[] chars = srr.toCharArray();
        chars[0]-=32;
        return String.valueOf(chars);
    }
}

@Data
class Student{
    private String name;
    private Integer age;
    private BigDecimal sum;
}
