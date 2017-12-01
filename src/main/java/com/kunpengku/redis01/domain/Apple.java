package com.kunpengku.redis01.domain;

import java.io.Serializable;

/**
 * Description
 * <p>
 * </p>
 * DATE 17/11/30.
 *
 * @author fupeng.
 */
public class Apple implements Serializable {

    private static final long serialVersionUID = 1;

    String name;
    int age;
    Mac mac;

    public Apple(String name, int age, Mac mac) {
        this.name = name;
        this.age = age;
        this.mac = mac;
    }

    @Override
    public String toString() {
        return "Apple{" + "name='" + name + '\'' + ", age=" + age + ", mac=" + mac + '}';
    }
}
