package com.my.jvm.finaliy;

import java.io.IOException;

public class FDemo {

    public static void main(String[] args) {
        try {
            int i = 1;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {

            } catch (RuntimeException io) {
                io.printStackTrace();
            }
        }
    }
}
