package com.runtime.java.lambda;


import com.sun.xml.internal.ws.api.client.WSPortInfo;
import jdk.internal.cmm.SystemResourcePressureImpl;
import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @date 2020/8/4 9:47
 * @Description
 */
public class MainJob {
    public static void main(String[] args) {
        Instant now = Instant.now();

        //Consumer consumer = t -> System.out.println("你大爷 \t" + t);
        //consumer.accept("dasdffadsfasdfasdfadsf");

        //Consumer
        //Supplier
        //Function


        //Supplier supplier1 = () -> "发大水发生的";
        //System.out.println(supplier1.get());


        //Function function = (t) -> t;
        //System.out.println(function.apply("张三"));;

        //Predicate predicate = (t) -> t.equals("");
        //(String)t.equals("张")
        //Predicate predicate1 = (t) -> t.equals("张三");


//        Consumer consumer = System.out::println;
//        consumer.accept("asdf");
//
//        test1 teste = System.out::println;
//        teste.acc("去玩儿");


        //Comparator<Integer> com = Integer::compareTo;
        //System.out.println(com.compare(3, 2));


        BiPredicate<String, Object> function = String::equals;
        System.out.println(function.test("A", "A"));


        List list = new ArrayList();


        System.out.println(Instant.now().plusMillis(TimeUnit.HOURS.toMillis(8)).toEpochMilli());
        System.out.println(System.currentTimeMillis());


        System.out.println(LocalDateTime.now());

        //pattern

    }

}


class a {
    String name;
    int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

interface test1 {
    void acc(Object object);
}