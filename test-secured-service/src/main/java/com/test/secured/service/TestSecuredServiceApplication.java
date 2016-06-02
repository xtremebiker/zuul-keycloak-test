package com.test.secured.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
@EnableDiscoveryClient
public class TestSecuredServiceApplication {

	@RequestMapping("/")
	@ResponseBody
	String home() {
		return "Hello World!";
	}
	
	@RequestMapping("/path")
	@ResponseBody
	String path() {
		return "Hello World from path!";
	}


	public static void main(String[] args) throws Exception {
		SpringApplication.run(TestSecuredServiceApplication.class, args);
	}
}
