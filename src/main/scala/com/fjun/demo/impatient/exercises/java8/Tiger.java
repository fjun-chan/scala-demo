package com.fjun.demo.impatient.exercises.java8;

public interface Tiger {
    default void say() {
        System.out.println("嗷嗷嗷");
    }
}
