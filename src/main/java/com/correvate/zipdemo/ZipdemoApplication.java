package com.correvate.zipdemo;

import com.correvate.zipdemo.service.ZipService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class ZipdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZipdemoApplication.class, args);
	}

}
