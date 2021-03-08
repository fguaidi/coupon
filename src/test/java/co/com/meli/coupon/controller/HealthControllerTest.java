package co.com.meli.coupon.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class HealthControllerTest {

	@InjectMocks
	private HealthController healthController;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testHealth() {
		ResponseEntity<String> healthResponse = healthController.health();
		Assert.assertEquals(HttpStatus.OK, healthResponse.getStatusCode());
	}

}
