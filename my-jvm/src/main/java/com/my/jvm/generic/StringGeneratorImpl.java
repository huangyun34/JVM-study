package com.my.jvm.generic;

/**
 * 普通类去实现指定具体类型的泛型接口
 * @author huangyun
 */
public class StringGeneratorImpl implements Generator<String> {

    private String data;

    public StringGeneratorImpl(String data) {
        this.data = data;
    }

    @Override
    public String next() {
        return data;
    }

    public static void main(String[] args) {
        StringGeneratorImpl stringGenerator = new StringGeneratorImpl("哟哟哟");
        System.out.println(stringGenerator.next());
    }
}
