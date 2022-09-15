import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author ：songdalin
 * @date ：2022/9/5 上午 10:41
 * @description：
 * @modified By：
 * @version: 1.0
 */
public class diguitest {

    public static void main(String[] args){
        Di d1 = new Di(1,2,3);
        Di d7 = new Di(null,2,3);
        Di d2 = new Di(3,1,5);
        Di d4 = new Di(3,2,5);
        Di d3 = new Di(4,2,1);
        Di d5 = new Di(4,2,4);
        Di d6 = new Di(4,0,null);
        Di d8 = new Di(4,1,null);
        Di d9 = new Di(3,1,null);




        List<Di> list = new ArrayList<>();
        list.add(d1);
        list.add(d2);
        list.add(d3);
        list.add(d4);
        list.add(d5);
        list.add(d6);
        list.add(d7);
        list.add(d8);
        list.add(d9);

        int i = 0;

        //1
        list =  list.stream().sorted(Comparator.comparing(Di::getC, comparator).thenComparing(Di::getB, comparator).thenComparing(Di::getA, comparator))
                .collect(Collectors.toList());
        //list = list.stream().sorted(Comparator.nullsLast(Comparator.comparing(Di::getA)).thenComparing(Comparator.nullsLast(Comparator.comparing(Di::getB))).thenComparing(Comparator.nullsLast(Comparator.comparing(Di::getC)))).collect(Collectors.toList());
        for (Di di : list) {

        }
        //digui(list, i);
        list.forEach(System.out::println);
    }

    static Comparator<Integer> comparator = (o1, o2) -> {
        final Integer x =  o1;
        final Integer y =  o2;

        if (x == null && y != null) {
            return 1;
        }
        if (x != null && y == null) {
            return -1;
        }
        if (x == null && y == null) {
            return 0;
        }
        return Integer.compare(x, y);
    };
    


    private static void digui(List<Di> dis, int i) {
        if (i > 2) {
            return;
        }
        digui(dis, i);


    }



}

@AllArgsConstructor
@Data
class Di {

    private Integer a;

    private Integer b;

    private Integer c;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Di)) return false;
        Di di = (Di) o;
        return Objects.equals(a, di.a) && Objects.equals(b, di.b) && Objects.equals(c, di.c);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c);
    }
}
