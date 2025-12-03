package br.com.jh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "br.com.jh.external")
public class GatewayRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayRestApplication.class, args);
	}

}
