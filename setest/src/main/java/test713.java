import com.sun.deploy.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author ：songdalin
 * @date ：2022/7/13 上午 11:28
 * @description：
 * @modified By：
 * @version: 1.0
 */
public class test713 {

    public static void main(String[] args){
/*        String a = "sdfsdfsd";
        final String[] split = a.split(",");
        System.out.println(split.length);*/
       // System.out.println(test(""));




       // System.out.println(String.format("%.2f",  new BigDecimal(0.0) ));

        //System.out.println(LocalDate.parse("2022-01-03", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        try {
            int a = 1 / 0;
            System.out.println();
        }catch (Exception e){

        }


    }


/*    public static String test(String attachPath) {
        //获取附件名称
        if (attachPath.endsWith(";")) {
            attachPath = attachPath.substring(0, attachPath.length() - 2);
        }
        final String[] split = attachPath.split(";");
        StringBuilder sb = new StringBuilder();
        for (String s : split) {
            if (s == null ||s == "") {
                continue;
            }
            sb.append(s.substring(s.lastIndexOf("/") + 1)).append(";");
        }
        //删除最后一个；
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }*/
}
