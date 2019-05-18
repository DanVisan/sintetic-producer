package ro.vis.sinteticproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SinteticProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SinteticProducerApplication.class, args);
	}

}
