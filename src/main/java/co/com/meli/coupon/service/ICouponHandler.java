package co.com.meli.coupon.service;

import co.com.meli.coupon.dto.CouponReqDto;
import co.com.meli.coupon.dto.CouponRsDto;

public interface ICouponHandler {
	
	CouponRsDto calculateCoupon(CouponReqDto couponRequest);
	
}
