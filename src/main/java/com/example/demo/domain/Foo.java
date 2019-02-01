package com.example.demo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("Foo")
public class Foo implements Serializable {


        @Id
        String name;
        String tag;

    public Foo(String name, String tag) {
         this.name = name;
        this.tag = tag;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public String getTag() {
        return tag;
    }
}
