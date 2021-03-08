package co.com.meli.coupon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MeliCouponServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeliCouponServiceApplication.class, args);
	}

}
