package ru.jevo.animation.basic;

/**
 * Created by Alexander on 17.12.2018.
 */
public class test {
    private static final test ourInstance = new test();

    public static test getInstance() {
        return ourInstance;
    }

    private test() {
    }
}
