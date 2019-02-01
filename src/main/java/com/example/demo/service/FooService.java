package com.example.demo.service;

import com.example.demo.domain.Foo;
import com.example.demo.repository.FooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FooService {

    @Autowired
    private FooRepository fooRepository;

    public Foo createFoo(Foo foo){
        return fooRepository.save(foo);
    }

    public Foo getFoo(String key){
        return fooRepository.findById(key).get();
    }
}
