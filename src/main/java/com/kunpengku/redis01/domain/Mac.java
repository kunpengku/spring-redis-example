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
public class Mac implements Serializable{

    private static final long serialVersionUID = 1;

    String name;
    int age;

    public Mac(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
