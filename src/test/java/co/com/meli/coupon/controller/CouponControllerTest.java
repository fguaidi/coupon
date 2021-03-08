package co.com.meli.coupon.controller;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import co.com.meli.coupon.dto.CouponReqDto;
import co.com.meli.coupon.dto.CouponRsDto;
import co.com.meli.coupon.service.impl.CouponHandlerImpl;

public class CouponControllerTest {

	
	@Mock
	private CouponHandlerImpl couponHandler;

	@InjectMocks
	private CouponController couponController;
	
	private CouponReqDto couponRequest = new CouponReqDto();
	private CouponRsDto couponResponse = new CouponRsDto();
	private List<String> itemIds;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		itemIds = new ArrayList<>();
		itemIds.add("MCO453165628");
		itemIds.add("MCO561290822");
		itemIds.add("MCO594954126");
		itemIds.add("MCO602224685");
		itemIds.add("MCO599620447");
		
		couponRequest.setItemIds(itemIds);
		couponRequest.setAmount(50000f);
		
		couponResponse.setItemIds(itemIds);
		couponResponse.setTotal(50000f);
	}

	@Test
	public void testCalculate() {
		when(couponHandler.calculateCoupon(couponRequest)).thenReturn(couponResponse);
		ResponseEntity<CouponRsDto> healthResponse = couponController.calculate(couponRequest);
		Assert.assertEquals(HttpStatus.OK, healthResponse.getStatusCode());
	}
	
	@Test
	public void testCalculateBadRequest() {
		couponResponse.setTotal(0f);
		when(couponHandler.calculateCoupon(couponRequest)).thenReturn(couponResponse);
		ResponseEntity<CouponRsDto> healthResponse = couponController.calculate(couponRequest);
		Assert.assertEquals(HttpStatus.BAD_REQUEST, healthResponse.getStatusCode());
	}


}
