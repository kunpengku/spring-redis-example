package com.kunpengku.redis01;

import com.kunpengku.redis01.domain.Apple;
import com.kunpengku.redis01.domain.Mac;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description
 * <p>
 * </p>
 * DATE 17/11/30.
 *
 * @author fupeng.
 */
@RestController
@RequestMapping("/template")
public class HighLevelController {

    @Autowired
    @Qualifier("forObject")
    RedisTemplate redisTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;
    // StringRedisTemplate 这个是 自动 实例化的，对于key和value都是 string 的情况，可以直接用.


    // key String and  value String \ get
    @RequestMapping("/test1")
    public String test1(){
        String key = "name1";
        String ret  =  stringRedisTemplate.opsForValue().get(key);
        System.out.println(ret);
        return ret;
    }

    // key String and  value String \ set
    @RequestMapping("/test2")
    public void test2(){
        String key = "name2";
        stringRedisTemplate.opsForValue().set(key, "fupeng");

        String ret  =  stringRedisTemplate.opsForValue().get("name2");
        System.out.println(ret);
    }

    // key String and  value Object  \ set
    @RequestMapping("/test3")
    public void test3(){
        String key = "name3";
        Mac mac = new Mac("mac",12);
        Apple apple = new Apple("mac", 90, mac);

        ValueOperations<String, Object> valueOperations = redisTemplate
                .opsForValue();
        valueOperations.set(key, apple);

        System.out.println();
    }

    // key String and  value Object  \ get
    @RequestMapping("/test4")
    public void test4(){
        String key = "name3";

        ValueOperations<String, Object> valueOperations = redisTemplate
                .opsForValue();
        Apple apple = (Apple) valueOperations.get(key);

        System.out.println(apple);
    }

    @Autowired
    @Qualifier("forObject_byte")
    RedisTemplate redisTemplate_byte;

    // key String and  value Object  \ get
    @RequestMapping("/test5")
    public void test5(){
        String key = "name4";

        Mac mac = new Mac("mac",88);
        Apple apple = new Apple("mac", 66, mac);

        JdkSerializationRedisSerializer redisSerializer =
                new JdkSerializationRedisSerializer();

        byte [] data = redisSerializer.serialize(apple);

        ValueOperations<String, Object> valueOperations = redisTemplate_byte
                .opsForValue();

        valueOperations.set(key, data);
        System.out.println();
    }


    // key String and  value Object  \ get
    @RequestMapping("/test6")
    public void test6(){
        String key = "name4";

        ValueOperations<String, Object> valueOperations = redisTemplate_byte
                .opsForValue();

        Object o = valueOperations.get(key);
        byte [] apple_byte = (byte[]) o;

        JdkSerializationRedisSerializer redisSerializer =
                new JdkSerializationRedisSerializer();

        Apple apple = (Apple) redisSerializer.deserialize(apple_byte);

        System.out.println(apple);
    }
}
