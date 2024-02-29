package com.mycom.nodbweb.controller;

import cn.hutool.core.bean.BeanUtil;
import lombok.Data;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：songdalin
 * @date ：2023/2/23 下午 6:02
 * @description：
 * @modified By：
 * @version: 1.0
 */
//@RestController
//@RequestMapping("/com2")
//@EnableAsync
@Component
public class Compon2 {

    @GetMapping("/dos")
   public void dos() {
       System.out.println("dos: " + Thread.currentThread().getName());
       syncdo();
   }

   //@Async
   public void syncdo() {
       System.out.println("syncdo: " + Thread.currentThread().getName());
   }

   public static void main(String[] args){
/*        List<List<String>> users = new ArrayList<>();
       List<String> people = new ArrayList<>();
       people.add("ddd");
       people.add("11");

       List<String> people1 = new ArrayList<>();
       people1.add("22");
       people1.add("222");


       users.add(people);*/
       //users.addAll(people1);
//       try {
//           AAA a = new AAA();
//           a.setName("111");
//           List<AAA> list = new ArrayList<>();
//           list.add(a);
//           System.out.println(list);
//           //a.setName("222");
//           AAA b = new AAA();
//           BeanUtil.copyProperties(a, b);
//           b.setName("222");
//           list.add(b);
//           System.out.println(list);
//           throw new RuntimeException("dddd");
//       }finally {
//           System.out.println("this is finally...");
//       }
       System.out.println("a".hashCode());

   }
}

@Data
class AAA {
    private String name;
}
