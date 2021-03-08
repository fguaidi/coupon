package co.com.meli.coupon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.meli.coupon.dto.CouponReqDto;
import co.com.meli.coupon.dto.CouponRsDto;
import co.com.meli.coupon.service.impl.CouponHandlerImpl;

@RestController
@RequestMapping("/coupon")
public class CouponController {

	@Autowired
	CouponHandlerImpl couponHandler;
	
	@PostMapping(value = "/")
	public ResponseEntity<CouponRsDto> calculate(@RequestBody CouponReqDto couponRequest) {

		CouponRsDto couponResponse = couponHandler.calculateCoupon(couponRequest);
		return couponResponse.getTotal() > 0 ? ResponseEntity.ok(couponResponse)
				: new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
}