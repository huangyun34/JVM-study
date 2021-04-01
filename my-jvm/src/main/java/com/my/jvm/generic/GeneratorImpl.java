package com.my.jvm.generic;

/**
 * 泛型实现类实现泛型接口
 * @author huangyun
 */
public class GeneratorImpl<T> implements Generator<T> {

    private T data;

    @Override
    public T next() {
        return data;
    }

    public GeneratorImpl(T data) {
        this.data = data;
    }

    public static void main(String[] args) {
        GeneratorImpl<String> generator = new GeneratorImpl<>("你好");
        System.out.println(generator.next());
    }
}
