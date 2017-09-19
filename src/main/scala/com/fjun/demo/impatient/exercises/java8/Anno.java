package com.fjun.demo.impatient.exercises.java8;

public class Anno extends Cat implements Tiger{
    public static void main(String[] args) {
        new Anno().say();
    }

    @Override
    public void say() {
        System.out.println("Anno");
    }
}
