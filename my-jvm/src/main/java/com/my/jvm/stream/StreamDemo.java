package com.my.jvm.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StreamDemo {

    /**
     * 找出"张"开头的最大长度
     * @param args
     */
    public static void main(String[] args) {
        //传统手艺
        List<String> names = Arrays.asList("张1","张2","张22","张233","张2444","黄25555","张3","张5");
        List<Integer> list = new ArrayList<>();
        for (String name : names) {
            if (name.startsWith("张")) {
                list.add(name.length());
            }
        }
        System.out.println(Collections.max(list));

        //stream方式
        int length = names.stream()
                .filter(name -> name.startsWith("张"))
                .mapToInt(String::length)
                .max()
                .getAsInt();
        System.out.println(length);
    }

}
