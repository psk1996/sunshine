package cn.tedu.store;

import javax.servlet.MultipartConfigElement;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
@MapperScan("cn.tedu.store.mapper")
public class StoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoreApplication.class, args);
	}
	
	@Bean
	@SuppressWarnings("deprecation")
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory=new MultipartConfigFactory();
		factory.setMaxRequestSize(50*1024*1024);
		factory.setMaxFileSize(50*1024*1024);
		return factory.createMultipartConfig();
	}

}

