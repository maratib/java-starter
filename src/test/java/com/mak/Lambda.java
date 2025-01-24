package com.mak;

import org.junit.jupiter.api.Test;

@FunctionalInterface
interface HelloLambda {
    void run(String msg);

    default void show() {
        System.out.println("This is from default method");
    }

    static int add(int a, int b) {
        return doAddition(a, b);
    }

    static int doAddition(int a, int b) {
        return a + b;
    }
}


public class Lambda {

    @Test
    void testLambda() {

        HelloLambda hello = msg -> System.out.println(msg);
        hello.run("Hello Lambda 1");
        hello.show();
        int result = HelloLambda.add(10, 10);
        System.out.println(result);


    }
}