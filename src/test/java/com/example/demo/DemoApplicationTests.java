package com.example.demo;

import com.example.demo.domain.Foo;
import com.example.demo.service.FooService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
 public class DemoApplicationTests {

	@Autowired
	private FooService fooService;

	@Test
	public void contextLoads() {

		String id = "Louis";
		fooService.createFoo(new Foo(id, "developer"));

		assertThat(fooService.getFoo(id))
				.extracting("id").isEqualTo(id);
	}

}

