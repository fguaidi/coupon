package co.com.meli.coupon.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coupon")
public class HealthController {

	@GetMapping(value = "/")
	public ResponseEntity<String> health() {
		 return new ResponseEntity<>(HttpStatus.OK);
	}
	
}