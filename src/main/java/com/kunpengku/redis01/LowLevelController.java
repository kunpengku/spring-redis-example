package com.kunpengku.redis01;

import com.kunpengku.redis01.domain.Apple;
import com.kunpengku.redis01.domain.Mac;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

/**
 * Description
 * <p>
 * </p>
 * DATE 17/11/30.
 *
 * @author fupeng.
 */
@RestController
public class LowLevelController {

    @Autowired
    @Qualifier("conn")
    RedisConnection connection;

    // key String ，value String \ get
    @RequestMapping("/test1")
    public String test1() throws UnsupportedEncodingException {
        String key = "name";
        byte[] ret = connection.get(key.getBytes("UTF8"));
        String s2 = new String(ret);
        System.out.println(s2);
        return s2;
    }

    // key String and  value String   \ set
    @RequestMapping("/test2")
    public String test2() throws UnsupportedEncodingException {
        String key = "name2";
        String value = "fupeng";
        connection.set(key.getBytes("UTF8"), value.getBytes("UTF8"));

        byte[] ret = connection.get(key.getBytes("UTF8"));
        String s3 = new String(ret);
        System.out.println(s3);

        return s3;
    }

    // key String and value Object   \ set
    @RequestMapping("/test3")
    public void test3() throws UnsupportedEncodingException {
        String key = "name3";
        Mac mac = new Mac("mac", 12);
        Apple apple = new Apple("mac", 90, mac);

        JdkSerializationRedisSerializer serializer = new JdkSerializationRedisSerializer();

        byte[] apple_byte = serializer.serialize(apple);
        connection.set(key.getBytes("UTF8"), apple_byte);

        System.out.println();
    }

    // key String and  value Object   | get
    @RequestMapping("/test4")
    public void test4() {
        String key = "name3";
        JdkSerializationRedisSerializer serializer = new JdkSerializationRedisSerializer();

        byte[] apple_byte = connection.get(key.getBytes());

        Apple apple = (Apple) serializer.deserialize(apple_byte);

        System.out.println(apple);
        return;
    }


}
